package com.elec.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.ElecResult;
import com.elec.commoms.pojo.TbItemChild;
import com.elec.commons.utils.CookieUtils;
import com.elec.commons.utils.HttpClientUtil;
import com.elec.commons.utils.IDUtils;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbItemDubboService;
import com.elec.dubbo.service.TbOrderDubboService;
import com.elec.order.pojo.MyOrderParam;
import com.elec.order.service.TbOrderService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbItem;
import com.muzi.muxin.pojo.TbOrder;
import com.muzi.muxin.pojo.TbOrderItem;
import com.muzi.muxin.pojo.TbOrderShipping;


@Service
public class TbOrderServiceImpl implements TbOrderService {
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${cart.key}")
	private String cartKey;
	@Value("${passport.url}")
	private String passprtUrl;
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbOrderDubboService tbOrderDubboService;
	@Override
	public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passprtUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(result, ElecResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		
		List<TbItemChild> listNew = new ArrayList<>();
		for (TbItemChild child : list) {
			for (Long id : ids) {
				if((long)child.getId()==(long)id){
					//判断购买量是否大于等于库存
					TbItem tbItem = tbItemDubboServiceImpl.selById(id);
					if(tbItem.getNum()>=child.getNum()){
						child.setEnough(true);
					}else{
						child.setEnough(false);
					}
					listNew.add(child);
				}
				
			}
		}
		return listNew;
	}
	@Override
	public ElecResult create(MyOrderParam param,HttpServletRequest request) {
		//订单表数据
		TbOrder order = new TbOrder();
		order.setPayment(param.getPayment());
		order.setPaymentType(param.getPaymentType());
		long id = IDUtils.genItemId();
		order.setOrderId(id+"");
		Date date =new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passprtUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(result, ElecResult.class);
		Map user= (LinkedHashMap)er.getData();
		order.setUserId(Long.parseLong(user.get("id").toString()));
		order.setBuyerNick(user.get("username").toString());
		order.setBuyerRate(0);
		//订单-商品表
		for (TbOrderItem  item : param.getOrderItems()) {
			item.setId(IDUtils.genItemId()+"");
			item.setOrderId(id+"");
		}
		//收货人信息
		TbOrderShipping shipping = param.getOrderShipping();
		shipping.setOrderId(id+"");
		shipping.setCreated(date);
		shipping.setUpdated(date);
		
		ElecResult erResult = new ElecResult();
		try {
			int index = tbOrderDubboService.insOrder(order, param.getOrderItems(), shipping);
			if(index>0){
				erResult.setStatus(200);
				//删除购买的商品
				String json = jedisDaoImpl.get(cartKey+user.get("username"));
				List<TbItemChild> listCart = JsonUtils.jsonToList(json, TbItemChild.class);
				List<TbItemChild> listNew = new ArrayList<>();
				for (TbItemChild child : listCart) {
					for (TbOrderItem item : param.getOrderItems()) {
						System.out.println("1"+child.getId().longValue());
						System.out.println("2"+Long.parseLong(item.getItemId()));
						
						if(child.getId().longValue()==Long.parseLong(item.getItemId())){
							listNew.add(child);
						}
					}
				}
				for (TbItemChild mynew : listNew) {
					listCart.remove(mynew);
				}
				jedisDaoImpl.set(cartKey+user.get("username"), JsonUtils.objectToJson(listCart));
				//删除
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return erResult;
	}

}

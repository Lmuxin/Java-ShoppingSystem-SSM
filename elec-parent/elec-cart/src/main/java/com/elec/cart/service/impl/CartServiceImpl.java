package com.elec.cart.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.cart.service.CartService;
import com.elec.commoms.pojo.ElecResult;
import com.elec.commoms.pojo.TbItemChild;
import com.elec.commons.utils.CookieUtils;
import com.elec.commons.utils.HttpClientUtil;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbItemDubboService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbItem;

@Service
public class CartServiceImpl implements CartService{
	@Resource
	private JedisDao jedisDaoImpl;
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Value("{passport.url}")
	private String passportUrl;
	@Value("{cart.key}")
	private String cartKey;
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addCart(long id, int num,HttpServletRequest req) {
		//集合存放所有购物车的商品
		List<TbItemChild> list = new ArrayList<>();	
		//redis中的key
		String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(jsonUser, ElecResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		//如果redis存在该key
		if(jedisDaoImpl.exists(key)) {
			String json =jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")) {
				list = JsonUtils.jsonToList(json, TbItemChild.class);
				for(TbItemChild child : list) {
					if((long)child.getId()==id) {
						child.setNum(child.getNum()+num);
						//重新添加到redis
						jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
						return ;
					}
				}
				
			}
		}
		//redise 中的key对应的value是null或者不存在
		TbItem item = tbItemDubboServiceImpl.selById(id);
		TbItemChild child =new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImage().split(","));
		child.setNum(num);
		child.setPrice(item.getPrice());
		list.add(child);
		
		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		
	}



	@Override
	public List<TbItemChild> showCart(HttpServletRequest req) {
		//redis中的key
		String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(jsonUser, ElecResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		
		String json = jedisDaoImpl.get(key);
		
		return  JsonUtils.jsonToList(json, TbItemChild.class);
	}



	@Override
	public ElecResult update(long id, int num,HttpServletRequest req) {

		//redis中的key
		String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(jsonUser, ElecResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json,  TbItemChild.class);
		for(TbItemChild tbItemChild : list) {
			if((long)tbItemChild.getId()==id) {
				tbItemChild.setNum(num);
			}
		}
		String result = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		ElecResult  elecResult=new ElecResult();
		if(result.equals("OK")) {
			elecResult.setStatus(200);
		}
		return elecResult;
	}



	@Override
	public ElecResult delete(long id, HttpServletRequest req) {
		//redis中的key
		String token = CookieUtils.getCookieValue(req, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		ElecResult er = JsonUtils.jsonToPojo(jsonUser, ElecResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json,  TbItemChild.class);
		TbItemChild child = null;
		for(TbItemChild tbItemChild : list) {
			if((long)tbItemChild.getId()==id) {
				 child = tbItemChild;
			}
		}
		list.remove(child);
		String result = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		ElecResult  elecResult=new ElecResult();
		if(result.equals("OK")) {
			elecResult.setStatus(200);
		}
		return elecResult;
	}

}

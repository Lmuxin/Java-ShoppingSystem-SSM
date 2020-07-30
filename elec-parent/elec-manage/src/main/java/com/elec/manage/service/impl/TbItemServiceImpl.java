package com.elec.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commons.utils.HttpClientUtil;
import com.elec.commons.utils.IDUtils;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbItemDescDubboService;
import com.elec.dubbo.service.TbItemDubboService;
import com.elec.manage.service.TbItemService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbItem;
import com.muzi.muxin.pojo.TbItemDesc;
import com.muzi.muxin.pojo.TbItemParamItem;

@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	
	@Value("${search.url}")
	private String url;
	
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("{redis.item.key}")
	private String itemKey;
	
	
	
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemDubboServiceImpl.show(page, rows);
	}
	@Override
	public int update(String ids, byte status) {
		int index = 0;
		TbItem tbItem = new TbItem();
		String [] idsStr = ids.split(",");
		for(String id : idsStr) {
			tbItem.setId(Long.parseLong(id));
			tbItem.setStatus(status);
			index += tbItemDubboServiceImpl.upItemStatus(tbItem);
			if(status==2||status==3) {
				jedisDaoImpl.del(itemKey+ids);
			}
		}
		if(index==idsStr.length) {
			return 1;
		}
		
		
		return 0;
		
	}
	@Override
	public int save(TbItem tbItem, String desc,String itemParam) throws Exception {
		
		final TbItem tbItemFinal = tbItem;
		final String descFinal = desc;
		//不考虑回滚
		/*long id = IDUtils.genItemId();
		tbItem.setId(id);
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItem.setStatus((byte)1);
		int index = TbItemDubboServiceImpl.insTbItem(tbItem);
		if(index>0) {
			TbItemDesc tbItemDesc= new TbItemDesc();
			tbItemDesc.setItemId(id);
			tbItemDesc.setItemDesc(desc);
			tbItemDesc.setCreated(date);
			tbItemDesc.setUpdated(date);
			index += tbItemDescDubboServiceImpl.insDesc(tbItemDesc);
		}
		
		if(index == 2) {
			return 1;
		}*/
		
		//调用dubbo中考虑事物回滚功能的方法
		
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItem.setStatus((byte)1);
		
		TbItemDesc tbItemDesc= new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		TbItemParamItem tbItemParamItem =new TbItemParamItem();
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setUpdated(date);
		tbItemParamItem.setItemId(id);
		tbItemParamItem.setParamData(itemParam);
		int index = 0;
			index = tbItemDubboServiceImpl.insTbItemDesc(tbItem, tbItemDesc,tbItemParamItem);
			new Thread() {
				public void run() {
					Map<String,Object> map = new HashMap<>();
					map.put("item", tbItemFinal);
					map.put("desc", descFinal);
					//使用java代码调用其他项目的控制器
					HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
				}
				
			}.start();
		return index;
	}

}

package com.elec.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.dubbo.service.TbItemDescDubboService;
import com.elec.item.service.TbItemDescService;
import com.elec.redis.dao.JedisDao;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	
	@Value("{redis.desc.key}")
	private String descKey;
	
	
	
	@Override
	public String showDesc(long itemId) {
		String key = descKey;
		if(jedisDaoImpl.exists(key)) {
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")) {
				return json;
			}
		}
		String result =  tbItemDescDubboServiceImpl.selByItemId(itemId).getItemDesc();
		jedisDaoImpl.set(key, result);
		return result;
		
	}

}

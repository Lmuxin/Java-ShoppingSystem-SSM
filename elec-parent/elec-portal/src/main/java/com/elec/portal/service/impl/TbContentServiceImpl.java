package com.elec.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbContentDubboService;
import com.elec.portal.service.TbContentService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbContent;

@Service
public class TbContentServiceImpl implements TbContentService {

	@Reference
	private TbContentDubboService TbContentDubboServiceImpl;
	
	@Resource
	private JedisDao jedisDaoImpl;
	
	@Value("${redis.bigpic.key}")
	private String key;
	
	@Override
	public String showBigPic() {
		//先判断redis是否存在
		if(jedisDaoImpl.exists(key)) {
		  String value = jedisDaoImpl.get(key);
		  if(value!=null&&!value.equals("")) {
			  return value;
		  }
		}
		//redis不存在，从数据库取出来再放进redis
		//redis里面存在，从redis取
		List<TbContent> list = TbContentDubboServiceImpl.selByCount(6, true);
		List<Map<String,Object>> listResult =new ArrayList<>();
		for(TbContent tbContent:list) {
			Map<String,Object>  map =new HashMap<>();
			map.put("srcB", tbContent.getPic2());
			map.put("height", 240);
			map.put("alt", "图片加载失败");
			map.put("width", 670);
			map.put("src", tbContent.getPic());
			map.put("widthB", 550);
			map.put("href", tbContent.getUrl());
			map.put("heightB", 240);
			listResult.add(map);
		}
		String listJson = JsonUtils.objectToJson(listResult);
		//数据存入redis
		jedisDaoImpl.set(key, listJson);
		return listJson;
	}

}

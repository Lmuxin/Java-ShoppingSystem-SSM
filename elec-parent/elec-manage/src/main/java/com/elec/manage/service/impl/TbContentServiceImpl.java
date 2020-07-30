package com.elec.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbContentDubboService;
import com.elec.manage.service.TbContentService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbContent;

@Service
public class TbContentServiceImpl implements TbContentService {
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String key;
	
	
	@Override
	public EasyUIDataGrid showContent(long categoryId, int page, int rows) {

		return tbContentDubboServiceImpl.selContentByPage(categoryId, page, rows);
	}
	
	
	@Override
	public int save(TbContent tbContent) {
		Date date =new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		int index = tbContentDubboServiceImpl.insContent(tbContent);
		//判断redis中是否有缓存数据
		if(jedisDaoImpl.exists(key)) {
			String value = jedisDaoImpl.get(key);
			if(value!=null&&!value.equals("")) {
				List<HashMap> list = JsonUtils.jsonToList(value, HashMap.class);
				HashMap<String,Object>  map =new HashMap<>();
				map.put("srcB", tbContent.getPic2());
				map.put("height", 240);
				map.put("alt", "图片加载失败");
				map.put("width", 670);
				map.put("src", tbContent.getPic());
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("heightB", 240);
				if(list.size()==6) {
					list.remove(5);
				}
				list.add(0,map);
				jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
			}
		}
		
		return index;
	}

}

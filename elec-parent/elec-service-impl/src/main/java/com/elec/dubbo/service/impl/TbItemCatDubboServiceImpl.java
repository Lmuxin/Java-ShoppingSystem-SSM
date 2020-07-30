package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbItemCatDubboService;
import com.muzi.muxin.mapper.TbItemCatMapper;
import com.muzi.muxin.pojo.TbItemCat;
import com.muzi.muxin.pojo.TbItemCatExample;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
	
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> show(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}
	@Override
	public TbItemCat selById(long id) {
		
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}

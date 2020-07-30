package com.elec.dubbo.service.impl;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbItemDescDubboService;
import com.muzi.muxin.mapper.TbItemDescMapper;
import com.muzi.muxin.pojo.TbItemDesc;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService{

	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public int insDesc(TbItemDesc tbItemDesc) {
		return tbItemDescMapper.insert(tbItemDesc);
	}
	@Override
	public TbItemDesc selByItemId(long itemId) {
		return tbItemDescMapper.selectByPrimaryKey(itemId);
	}

}

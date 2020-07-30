package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbItemParamItemDubboService;
import com.muzi.muxin.mapper.TbItemParamItemMapper;
import com.muzi.muxin.pojo.TbItemParamItem;
import com.muzi.muxin.pojo.TbItemParamItemExample;

public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService{
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public int insParamItem(TbItemParamItem tbItemParamItem) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public TbItemParamItem selByItemid(long itemId) {
		TbItemParamItemExample example =new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}

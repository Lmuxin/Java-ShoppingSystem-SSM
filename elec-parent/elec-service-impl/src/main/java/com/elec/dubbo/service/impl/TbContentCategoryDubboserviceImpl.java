package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbContentCategoryDubboService;
import com.muzi.muxin.mapper.TbContentCategoryMapper;
import com.muzi.muxin.pojo.TbContentCategory;
import com.muzi.muxin.pojo.TbContentCategoryExample;

public class TbContentCategoryDubboserviceImpl implements TbContentCategoryDubboService{
	@Resource
	private  TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> selByPid(long id) {
		TbContentCategoryExample example =new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		
		return tbContentCategoryMapper.selectByExample(example);
	}
	@Override
	public int insTbContenCategory(TbContentCategory cate) {
		
		return tbContentCategoryMapper.insertSelective(cate);
	}
	@Override
	public int updIsParentById(TbContentCategory cate) {
		
		return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
	}
	@Override
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
	

}

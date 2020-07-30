package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.dubbo.service.TbContentDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muzi.muxin.mapper.TbContentMapper;
import com.muzi.muxin.pojo.TbContent;
import com.muzi.muxin.pojo.TbContentExample;

public class TbContentDubboServiceImpl implements TbContentDubboService{

	@Resource
	private TbContentMapper tbContentMapper;
	@Override
	public EasyUIDataGrid selContentByPage(long cateforyId, int page, int rows) {
		PageHelper.startPage(page,rows);
		TbContentExample example =new TbContentExample();
		if(cateforyId!=0) {
			example.createCriteria().andCategoryIdEqualTo(cateforyId);
		}
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pi =new PageInfo<>(list);
		EasyUIDataGrid easyUIDataGrid =new EasyUIDataGrid();
		easyUIDataGrid.setRows(pi.getList());
		easyUIDataGrid.setTotal(pi.getTotal());
		return easyUIDataGrid;
	}
	@Override
	public int insContent(TbContent tbContent) {

		return tbContentMapper.insertSelective(tbContent);
	}
	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example =new TbContentExample();
		if(isSort) {
			example.setOrderByClause("updated desc");
		}
		if(count!=0) {
			PageHelper.startPage(1,count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi =new PageInfo<>(list);
			return pi.getList();
		}else {
			
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	
	}

}

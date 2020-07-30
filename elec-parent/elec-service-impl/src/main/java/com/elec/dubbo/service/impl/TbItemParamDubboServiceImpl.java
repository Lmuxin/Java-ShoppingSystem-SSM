package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.dubbo.service.TbItemParamDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muzi.muxin.mapper.TbItemParamMapper;
import com.muzi.muxin.pojo.TbItemParam;
import com.muzi.muxin.pojo.TbItemParamExample;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	@Override
	public EasyUIDataGrid showData(int page, int rows) {
		
		//设置分页条件
		PageHelper.startPage(page,rows);
		
		//设置查询的sql语句
		//XXXXXExample()  设置了什么，相当于在sql中where从句中添加条件
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//根据程序员自己编写的sql语句结合分页插件产生的最终结果，封装到pageinfo
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		
		//设置方法返回结果
		EasyUIDataGrid dataGrid =new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}
	@Override
	public int delByIds(String ids) throws Exception {
		int index = 0;
		String[] idStr = ids.split(",");
		for(String id : idStr) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index == idStr.length) {
			return 1;
		}else {
			throw new Exception("删除失败，可能数据已经不存在");
		}
	}
	@Override
	public TbItemParam selByCatId(long catId) {
		TbItemParamExample example =new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public int insParam(TbItemParam tbItemParam) {
		return tbItemParamMapper.insertSelective(tbItemParam);
	}

}

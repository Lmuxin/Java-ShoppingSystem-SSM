package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.dubbo.service.TbItemDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muzi.muxin.mapper.TbItemDescMapper;
import com.muzi.muxin.mapper.TbItemMapper;
import com.muzi.muxin.mapper.TbItemParamItemMapper;
import com.muzi.muxin.pojo.TbItem;
import com.muzi.muxin.pojo.TbItemDesc;
import com.muzi.muxin.pojo.TbItemExample;
import com.muzi.muxin.pojo.TbItemParamItem;

public class TbItemDubboServiceImpl implements TbItemDubboService {
	
	@Resource
	private TbItemMapper tbItemMapper;
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public EasyUIDataGrid show(int page, int rows) {
		
		//分页
		//设置分页代码
		PageHelper.startPage(page,rows);
		
		//查询全部
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());

		
		PageInfo<TbItem> pi =  new PageInfo<>(list);
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		
		return dataGrid;
		
	}

	//商品删除 上架下架
	@Override
	public int upItemStatus(TbItem tbItem) {
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	/**新增商品
	 * 
	 */
	@Override
	public int insTbItem(TbItem tbItem) {
		
		return tbItemMapper.insert(tbItem);
	}

	@Override
	public int insTbItemDesc(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws Exception {
		int index = 0;
		try {
			index = tbItemMapper.insertSelective(tbItem);
			index+=tbItemDescMapper.insertSelective(tbItemDesc);
			index+=tbItemParamItemMapper.insertSelective(tbItemParamItem);
		} catch (Exception e) {
		}
		if(index==3) {
			return 1;
		}else {
			throw new Exception("新增失败,事物回滚，数据还原");
		}
	}

	@Override
	public List<TbItem> selAllByStatus(byte status) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}

	@Override
	public TbItem selById(long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}

}

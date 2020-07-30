package com.elec.dubbo.service;

import java.util.List;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.muzi.muxin.pojo.TbItem;
import com.muzi.muxin.pojo.TbItemDesc;
import com.muzi.muxin.pojo.TbItemParamItem;

public interface TbItemDubboService {
	/**
	 * 商品分页查询
	 * @param page  当前页
	 * @param rows	每页条数
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	
	/**
	 * 根据id修改状态 
	 * @param id
	 * @param status
	 * @return
	 */
	int upItemStatus(TbItem tbItem);
	
	/**
	 * 新增商品
	 * @param tbItem
	 * @return
	 */
	int insTbItem(TbItem tbItem);
	
	/**
	 * 新增商品和商品描述  事物回滚
	 * @param tbItem
	 * @param tbItemDesc
	 * @return
	 */
	int insTbItemDesc(TbItem tbItem ,TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem)throws Exception;
	
	
	/**
	 * 通过状态查询全部可用数据
	 * @return
	 */
	List<TbItem> selAllByStatus(byte status);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TbItem selById(long id);
	
	
	

}

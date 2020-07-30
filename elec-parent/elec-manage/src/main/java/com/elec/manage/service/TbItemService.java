package com.elec.manage.service;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.muzi.muxin.pojo.TbItem;

public interface TbItemService {
	/**
	 * 显示所有商品
	 * @param page
	 * @param row
	 * @return
	 */
	public EasyUIDataGrid  show(int page,int rows);
	
	//批量修改  上架下架删除
	int update(String ids,byte status);
	
	/**
	 * 商品新增
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	int save(TbItem tbItem,String desc,String itemParam)throws Exception;
	

}

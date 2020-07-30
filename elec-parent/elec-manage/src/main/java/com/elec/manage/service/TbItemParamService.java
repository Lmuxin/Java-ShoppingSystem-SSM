package com.elec.manage.service;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commoms.pojo.ElecResult;
import com.muzi.muxin.pojo.TbItemParam;

public interface TbItemParamService {
	
	/**
	 * 分页显示商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page,int rows);
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delete(String ids)throws Exception;
	
	/**
	 * 根据类目id查询模板信息
	 * @return
	 */
	ElecResult showParam(long catId);
	
	/**
	 * 新增模板信息
	 * @param tbItemParam
	 * @return
	 */
	ElecResult save(TbItemParam tbItemParam);

}

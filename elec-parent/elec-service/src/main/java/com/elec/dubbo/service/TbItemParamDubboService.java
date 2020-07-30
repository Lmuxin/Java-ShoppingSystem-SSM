package com.elec.dubbo.service;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.muzi.muxin.pojo.TbItemParam;

public interface TbItemParamDubboService {
	
	/**
	 * 分页查询数据，结果包含当前页面数据和总条数
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showData(int page,int rows);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delByIds(String ids)throws Exception;
	
	/**
	 * 根据类目id查询参数模板
	 * @param catId
	 * @return
	 */
	TbItemParam selByCatId(long catId);
	
	/**
	 * 新增param
	 * @param tbItemParam
	 * @return
	 */
	int insParam(TbItemParam tbItemParam);

}

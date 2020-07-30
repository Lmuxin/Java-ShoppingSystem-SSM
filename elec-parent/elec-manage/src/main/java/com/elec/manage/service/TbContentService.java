package com.elec.manage.service;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.muzi.muxin.pojo.TbContent;

public interface TbContentService {
	
	/**
	 * 分页显示内容信息
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showContent(long categoryId,int page,int rows);
	
	/**
	 * 新增
	 * @param tbContent
	 * @return
	 */
	int save(TbContent tbContent);

}

package com.elec.dubbo.service;

import java.util.List;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.muzi.muxin.pojo.TbContent;

public interface TbContentDubboService {
	/**
	 * 分页查询
	 * @param cateforyId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selContentByPage(long cateforyId,int page,int rows);
	
	/**
	 * 新增
	 * @param tbContent
	 * @return
	 */
	int  insContent(TbContent tbContent);
	
	/**
	 * 查询出最近 的n个
	 * @param count
	 * @return
	 */
	List<TbContent> selByCount(int count,boolean isSort);

}

package com.elec.dubbo.service;

import java.util.List;

import com.muzi.muxin.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	 * 根据父id查询所有子类目
	 * @param id
	 * @return
	 */
	List<TbContentCategory> selByPid(long id);
	
	/**
	 * 新增
	 * @param cate
	 * @return
	 */
	int insTbContenCategory(TbContentCategory cate);
	
	/**
	 * 修改isparent通过id
	 * @param id
	 * @param isParent
	 * @return
	 */
	int updIsParentById(TbContentCategory cate);
	
	/**
	 * 根据id查询内容目录详细信息
	 * @param id
	 * @return
	 */
	TbContentCategory selById(long id);
	

}

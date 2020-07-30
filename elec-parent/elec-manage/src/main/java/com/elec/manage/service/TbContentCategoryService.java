package com.elec.manage.service;

import java.util.List;

import com.elec.commoms.pojo.EasyUiTree;
import com.elec.commoms.pojo.ElecResult;
import com.muzi.muxin.pojo.TbContentCategory;

public interface TbContentCategoryService {
	
	/**
	 * 查询所有类目并转换为easyui  tree的属性要求
	 * @return
	 */
	List<EasyUiTree> showCatagory(long id);
	
	/**
	 * 类目新增
	 * @param cate
	 * @return
	 */
	
	ElecResult creat(TbContentCategory cate);
	/**
	 * 类目重命名
	 * @param cate
	 * @return
	 */
	ElecResult update(TbContentCategory cate);
	
	/**
	 * 类目删除
	 * @param id
	 * @return
	 */
	
	ElecResult delete(TbContentCategory cate);
	
	

}

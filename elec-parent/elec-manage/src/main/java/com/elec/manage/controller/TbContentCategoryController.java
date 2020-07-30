package com.elec.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.EasyUiTree;
import com.elec.commoms.pojo.ElecResult;
import com.elec.manage.service.TbContentCategoryService;
import com.muzi.muxin.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {
	@Resource
	private TbContentCategoryService tbContentCategoryServiceImpl;
	
	/**
	 * 查询类目
	 * @param id
	 * @return
	 */
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUiTree> showCategory(@RequestParam(defaultValue="0") long id){
		
		return tbContentCategoryServiceImpl.showCatagory(id);
	}
	
	
	/**
	 * 新增内容类目
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public ElecResult create(TbContentCategory cate){
		
		return tbContentCategoryServiceImpl.creat(cate);
	}
	

	
	/**
	 * 类目重命名
	 * @param cate
	 * @return
	 */
	
	@RequestMapping("content/category/update")
	@ResponseBody
	public ElecResult update(TbContentCategory cate){
		
		return tbContentCategoryServiceImpl.update(cate);
	}
	
	
	/**
	 * 类目删除
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/delete")
	@ResponseBody
	public ElecResult delete(TbContentCategory cate){
		return tbContentCategoryServiceImpl.delete(cate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

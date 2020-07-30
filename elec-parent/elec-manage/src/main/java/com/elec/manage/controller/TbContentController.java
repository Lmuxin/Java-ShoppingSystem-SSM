package com.elec.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commoms.pojo.ElecResult;
import com.elec.manage.service.TbContentService;
import com.muzi.muxin.pojo.TbContent;

@Controller
public class TbContentController {
	
	@Resource
	private TbContentService tbContentServiceImpl;
	
	/**
	 * 显示内容信息
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showContent(long categoryId,int page,int rows) {
		return tbContentServiceImpl.showContent(categoryId, page, rows);
	}

	
	@RequestMapping("content/save")
	@ResponseBody
	public ElecResult save(TbContent tbContent) {
		ElecResult er =new ElecResult();
		int index =  tbContentServiceImpl.save(tbContent);
		if(index>0) {
			er.setStatus(200);
		}
		return er;
	}

}

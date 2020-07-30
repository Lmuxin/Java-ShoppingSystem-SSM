package com.elec.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commoms.pojo.ElecResult;
import com.elec.manage.service.TbItemParamService;
import com.muzi.muxin.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	
	@Resource
	private TbItemParamService tbItemParamServiceImpl;
	
	/**
	 * 规格参数分页显示
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page,int rows) {
		return tbItemParamServiceImpl.showPage(page, rows);
		
	}
	
	/**
	 * 批量删除规格参数
	 * @param ids
	 * @return
	 */
	
	@RequestMapping("item/param/delete")
	@ResponseBody
	public ElecResult delete(String ids) {
		ElecResult elecResult =new ElecResult();
		try {
			int index = tbItemParamServiceImpl.delete(ids);
			if(index==1) {
				elecResult.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			elecResult.setData(e.getMessage());
		}
		return elecResult;
	}
	
	/**
	 * 点击商品类目按钮显示添加分组按钮
	 * 可判断类目是否已经添加到模板
	 *
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public ElecResult show(@PathVariable long catId) {
		return tbItemParamServiceImpl.showParam(catId);
	}
	
	/**
	 * 商品类目新增
	 * @param tbItemParam
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public  ElecResult save(TbItemParam tbItemParam,@PathVariable long catId) {
		tbItemParam.setItemCatId(catId);
		return tbItemParamServiceImpl.save(tbItemParam);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

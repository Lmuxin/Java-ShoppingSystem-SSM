package com.elec.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commoms.pojo.ElecResult;
import com.elec.manage.service.TbItemService;
import com.muzi.muxin.pojo.TbItem;

@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;
	
	
	/**
	 * 分页查询所有商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page,int rows) {
		
		return tbItemServiceImpl.show(page, rows);
	}
	
	
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit() {
		
		return "item-edit";
	}
	
	/**
	 * 商品删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public ElecResult delete(String ids) {
		ElecResult er = new ElecResult();
		int index = tbItemServiceImpl.update(ids, (byte)3);
		if(index==1) {
			er.setStatus(200);
		}
		return er;
	}
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public ElecResult reshelf(String ids) {
		ElecResult er = new ElecResult();
		int index = tbItemServiceImpl.update(ids, (byte)1);
		if(index==1) {
			er.setStatus(200);
		}
		return er;
	}
	
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public ElecResult instock(String ids) {
		ElecResult er = new ElecResult();
		int index = tbItemServiceImpl.update(ids, (byte)2);
		if(index==1) {
			er.setStatus(200);
		}
		return er;
	}
	
	/**
	 * 商品新增
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping("item/save")
	@ResponseBody
	public ElecResult insert(TbItem tbItem,String desc,String itemParam ) {
		ElecResult elecResult = new ElecResult();
		int index;
		try {
			index = tbItemServiceImpl.save(tbItem, desc, itemParam);
			if(index ==1) {
				elecResult.setStatus(200);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			elecResult.setData(e.getMessage());
		}
		
		return elecResult;
		
	}
	
	

}

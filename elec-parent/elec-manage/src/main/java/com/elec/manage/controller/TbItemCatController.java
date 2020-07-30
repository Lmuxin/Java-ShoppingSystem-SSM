package com.elec.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.EasyUiTree;
import com.elec.manage.service.TbItemCatService;

@Controller
public class TbItemCatController {
	@Resource
	private TbItemCatService TbItemCatServiceImpl;
	
	/**
	 * 显示商品类目
	 * @param id
	 * @return
	 */
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showCat(@RequestParam(defaultValue="0")long id){
		return TbItemCatServiceImpl.show(id);
		
	}
}

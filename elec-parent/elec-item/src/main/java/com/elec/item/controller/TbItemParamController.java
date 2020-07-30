package com.elec.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.item.service.TbItemParamItemService;

@Controller
public class TbItemParamController {
	@Resource
	private TbItemParamItemService tbItemParamItemServiceImpl;
	
	/**
	 * 显示规格参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="item/param/{id}.html",produces="text/html;charset=utf-8")
	@ResponseBody
	public String param(@PathVariable long id) {
		
		return tbItemParamItemServiceImpl.showParam(id);
	}

}

package com.elec.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.dubbo.service.TbItemCatDubboService;
import com.elec.item.pojo.PortalMenu;
import com.elec.item.pojo.PortalMenuNode;
import com.elec.item.service.TbItemCatService;
import com.muzi.muxin.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	@Reference
	 private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public PortalMenu showCatMenu() {
		//查询所有一级菜单
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		PortalMenu pm = new PortalMenu();
		pm.setData(selAllMenu(list));
		return pm;
		
	}
	
	/**
	 * 最终返回结果所有查询到的结果
	 * @return
	 */
	public List<Object> selAllMenu(List<TbItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for(TbItemCat tbItemCat : list) {
			if(tbItemCat.getIsParent()) {
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
				listNode.add(pmd);
			}else {
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return listNode;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.elec.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.EasyUiTree;
import com.elec.commoms.pojo.ElecResult;
import com.elec.commons.utils.IDUtils;
import com.elec.dubbo.service.TbContentCategoryDubboService;
import com.elec.manage.service.TbContentCategoryService;
import com.muzi.muxin.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{
	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
	@Override
	public List<EasyUiTree> showCatagory(long id) {
		List<EasyUiTree> listTree = new ArrayList<>();
		List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(id);
		for(TbContentCategory cat: list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		return listTree;
	}
	@Override
	public ElecResult creat(TbContentCategory cate) {
		ElecResult elecResult = new ElecResult();
		//判断当前节点名称是否已经存在
		List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(cate.getParentId());
		for(TbContentCategory child: children) {
			if(child.getName().equals(cate.getName())) {
				elecResult.setData("名称已经存在");
				return elecResult;
			}
		}
		Date date =new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setStatus(1);
		cate.setSortOrder(1);
		cate.setIsParent(false);
		long id = IDUtils.genItemId();
		cate.setId(id);
		int index =  tbContentCategoryDubboServiceImpl.insTbContenCategory(cate);
		if(index>0) {
			TbContentCategory patent = new TbContentCategory();
			patent.setId(cate.getParentId());
			patent.setIsParent(true);
			tbContentCategoryDubboServiceImpl.updIsParentById(patent);
		}
		elecResult.setStatus(200);
		Map<String,Long> map =new HashMap<>();
		map.put("id", id);
		elecResult.setData(map);
		return elecResult;
	}
	@Override
	public ElecResult update(TbContentCategory cate) {
		ElecResult elecResult = new ElecResult();
		//查询当前节点信息
		TbContentCategory cateSelect = tbContentCategoryDubboServiceImpl.selById(cate.getId());
		//查询当前节点的父节点的所有字节点信息
		List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(cateSelect.getParentId());
		for(TbContentCategory child: children) {
			if(child.getName().equals(cate.getName())) {
				elecResult.setData("名称已经存在");
				return elecResult;
			}
		}
		int index = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
		if(index>0) {
			
			elecResult.setStatus(200);
		}
		return elecResult;
	}
	@Override
	public ElecResult delete(TbContentCategory cate) {
		ElecResult elecResult = new ElecResult();
		cate.setStatus(0);
		int index = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
		if(index>0) {
			TbContentCategory curr =  tbContentCategoryDubboServiceImpl.selById(cate.getId());
			List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(curr.getParentId());
			if(list==null||list.size()==0) {
				TbContentCategory parent =new TbContentCategory();
				parent.setId(curr.getParentId());
				parent.setIsParent(false);
				int result = tbContentCategoryDubboServiceImpl.updIsParentById(parent);
				if(result>0) {
					elecResult.setStatus(200);
				}
			}else {
				elecResult.setStatus(200);
			}
			
		}
		return elecResult;
	}
	
	
	
	
	
	
	

}

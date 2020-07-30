package com.elec.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.EasyUIDataGrid;
import com.elec.commoms.pojo.ElecResult;
import com.elec.dubbo.service.TbItemCatDubboService;
import com.elec.dubbo.service.TbItemParamDubboService;
import com.elec.manage.pojo.TbItemParamChild;
import com.elec.manage.service.TbItemParamService;
import com.muzi.muxin.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements  TbItemParamService{
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid datagrid = tbItemParamDubboServiceImpl.showData(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) datagrid.getRows();
		List<TbItemParamChild> listChild = new ArrayList<>();
		for(TbItemParam tbItemParam : list) {
			TbItemParamChild child = new TbItemParamChild();
			child.setCreated(tbItemParam.getCreated());
			child.setId(tbItemParam.getId());
			child.setItemCatId(tbItemParam.getItemCatId());
			child.setParamData(tbItemParam.getParamData());
			child.setUpdated(tbItemParam.getUpdated());
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(child.getItemCatId()).getName());
			listChild.add(child);
		}
		datagrid.setRows(listChild);
		return datagrid;
	}

	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}

	@Override
	public ElecResult showParam(long catId) {
		ElecResult elecResult = new ElecResult();
		TbItemParam tbItemParam = tbItemParamDubboServiceImpl.selByCatId(catId);
		if(tbItemParam!=null) {
			elecResult.setStatus(200);
			elecResult.setData(tbItemParam);
		}
		return elecResult;
	}

	@Override
	public ElecResult save(TbItemParam tbItemParam) {
		ElecResult elecResult =new ElecResult();
		Date date =new Date();
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		int index= tbItemParamDubboServiceImpl.insParam(tbItemParam);
		if(index>0) {
			elecResult.setStatus(200);
			
		}
		
		return elecResult;
		
		
		
	}

}

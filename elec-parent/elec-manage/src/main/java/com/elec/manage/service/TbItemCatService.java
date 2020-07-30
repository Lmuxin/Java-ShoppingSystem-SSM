package com.elec.manage.service;

import java.util.List;

import com.elec.commoms.pojo.EasyUiTree;

public interface TbItemCatService {
	
	/**
	 * 根据父菜单id显示所有子菜单
	 * @param pid
	 * @return
	 */
	List<EasyUiTree> show(long pid);

}

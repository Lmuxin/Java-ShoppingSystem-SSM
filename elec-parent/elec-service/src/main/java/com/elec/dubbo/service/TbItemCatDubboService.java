package com.elec.dubbo.service;

import java.util.List;

import com.muzi.muxin.pojo.TbItemCat;

public interface TbItemCatDubboService {
	/**
	 * 根据父类目查询子类目
	 * @param pid
	 * @return
	 */
	List<TbItemCat> show(long pid);
	
	/**
	 * 根据类目id插叙
	 * @param id
	 * @return
	 */
	TbItemCat selById(long id);
	
}

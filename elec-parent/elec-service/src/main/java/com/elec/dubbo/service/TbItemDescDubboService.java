package com.elec.dubbo.service;

import com.muzi.muxin.pojo.TbItemDesc;

public interface TbItemDescDubboService {
	/**
	 * 新增商品描述
	 * @param tbItemDesc
	 * @return
	 */
	int insDesc(TbItemDesc tbItemDesc);
	
	/**
	 * 根据主键查询商品详情
	 * @param itemid
	 * @return
	 */
	TbItemDesc selByItemId(long itemId);

}

package com.elec.dubbo.service;

import com.muzi.muxin.pojo.TbItemParamItem;

public interface TbItemParamItemDubboService {
	
	/**
	 * 新增商品的具体规格信息
	 * @param tbItemParamItem
	 * @return
	 */
	int insParamItem(TbItemParamItem tbItemParamItem);
	
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return
	 */
	TbItemParamItem selByItemid(long itemId);
	
	

}

package com.elec.dubbo.service;

import java.util.List;

import com.muzi.muxin.pojo.TbOrder;
import com.muzi.muxin.pojo.TbOrderItem;
import com.muzi.muxin.pojo.TbOrderShipping;



public interface TbOrderDubboService {
	/**
	 * 创建订单
	 * @param order
	 * @param list
	 * @param shipping
	 * @return
	 */
	int insOrder(TbOrder order,List<TbOrderItem> list,TbOrderShipping shipping) throws Exception;
}

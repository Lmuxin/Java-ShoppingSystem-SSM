package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbOrderDubboService;
import com.muzi.muxin.mapper.TbOrderItemMapper;
import com.muzi.muxin.mapper.TbOrderMapper;
import com.muzi.muxin.mapper.TbOrderShippingMapper;
import com.muzi.muxin.pojo.TbOrder;
import com.muzi.muxin.pojo.TbOrderItem;
import com.muzi.muxin.pojo.TbOrderShipping;



public class TbOrderDubboServiceImpl implements TbOrderDubboService{
	@Resource
	private TbOrderMapper tbOrderMapper;
	@Resource
	private TbOrderItemMapper tbOrderItemMapper;
	@Resource
	private TbOrderShippingMapper tbOrderShippingMapper;
	
	
	
	@Override
	public int insOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping shipping) throws Exception {
		int index = tbOrderMapper.insertSelective(order);
		for (TbOrderItem tbOrderItem : list) {
			index+=tbOrderItemMapper.insertSelective(tbOrderItem);
		}
		index+=tbOrderShippingMapper.insertSelective(shipping);
		if(index==2+list.size()){
			return 1;
		}else{
			throw new Exception("创建订单失败");
		}
	}

}

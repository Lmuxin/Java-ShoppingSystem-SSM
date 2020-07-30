package com.elec.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.elec.commoms.pojo.ElecResult;
import com.elec.commoms.pojo.TbItemChild;
import com.elec.order.pojo.MyOrderParam;


public interface TbOrderService {
	/**
	 * 确认订单信息包含的商品
	 * @param id
	 * @return
	 */
	List<TbItemChild> showOrderCart(List<Long> id,HttpServletRequest request);
	/**
	 * 创建订单
	 * @param param
	 * @return
	 */
	ElecResult create(MyOrderParam param,HttpServletRequest request);
}

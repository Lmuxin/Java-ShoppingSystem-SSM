package com.elec.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.elec.commoms.pojo.ElecResult;
import com.elec.commoms.pojo.TbItemChild;

public interface CartService {
	
	/**
	 * 加入购物车
	 * @param id
	 * @param num
	 */
	void addCart(long id,int num,HttpServletRequest req);
	
	
	/**
	 * 显示购物车
	 * 
	 */
	
	List<TbItemChild> showCart(HttpServletRequest req);	
	
	/**
	 * 根据id修改数量
	 * @param id
	 * @param num
	 * @return
	 */
	ElecResult update(long id,int num,HttpServletRequest req);
	
	/**
	 * 删除购物车商品
	 * @param id
	 * @param num
	 * @param req
	 * @return
	 */
	ElecResult delete(long id,HttpServletRequest req);
	
	

}

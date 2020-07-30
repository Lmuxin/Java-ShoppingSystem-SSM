package com.elec.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elec.commoms.pojo.ElecResult;
import com.elec.order.pojo.MyOrderParam;
import com.elec.order.service.TbOrderService;

@Controller
public class OrderController {
	@Resource
	private TbOrderService tbOrderServiceImpl;
	/**
	 * 显示订单确认效果
	 * @param model
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam("id") List<Long> ids,HttpServletRequest request){
		model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request));
		return "order-cart";
	}
	
	
	
	/**
	 * 创建订单
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("order/create.html")
	public String createOrder(MyOrderParam param,HttpServletRequest request){
		ElecResult er = tbOrderServiceImpl.create(param, request);
		if(er.getStatus()==200){
			return "my-orders";
		}else{
			request.setAttribute("message", "订单创建失败");
			return "error/exception";
		}
	}


}

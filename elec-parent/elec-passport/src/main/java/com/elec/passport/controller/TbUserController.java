package com.elec.passport.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elec.commoms.pojo.ElecResult;
import com.elec.passport.service.TbUserService;
import com.muzi.muxin.pojo.TbUser;

@Controller
public class TbUserController {
	@Resource
	private TbUserService tbUserServiceImpl;
	
	
	/**
	 * 显示登陆
	 * @return
	 */
	@RequestMapping("user/showLogin")
	public String showLogin(@RequestHeader(value="Referer",defaultValue="") String url,Model model,String interurl) {
		if(!interurl.equals("")) {
			model.addAttribute("redirect",interurl);
		}else if(!url.equals("")){
			model.addAttribute("redirect",url);
		}
		return "login";
	}
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	
	@RequestMapping("user/login")
	@ResponseBody
	public ElecResult login(TbUser user,HttpServletRequest req,HttpServletResponse rep) {
		return tbUserServiceImpl.login(user,req,rep);
	}
	
	
	/**
	 * 通过token获取用户信息
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public Object getUserInfo(@PathVariable String token,String callback) {
		ElecResult er =tbUserServiceImpl.getUserInfoByTokern(token);
		if(callback!=null&&!callback.equals("")) {
			MappingJacksonValue mjv =new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return er;
	}
	
	
	

	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token,String callback,HttpServletRequest req,HttpServletResponse rep) {
		ElecResult er =tbUserServiceImpl.logout(token, req, rep);
		if(callback!=null&&!callback.equals("")) {
			MappingJacksonValue mjv =new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return er;
	}
	

}

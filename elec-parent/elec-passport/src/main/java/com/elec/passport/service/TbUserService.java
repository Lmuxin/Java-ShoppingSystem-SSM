package com.elec.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elec.commoms.pojo.ElecResult;
import com.muzi.muxin.pojo.TbUser;

public interface TbUserService {
	
	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	ElecResult login(TbUser user,HttpServletRequest req,HttpServletResponse rep);
	
	/**
	 * 根据token查询用户信息
	 * @param token
	 * @return
	 */
	ElecResult getUserInfoByTokern(String token);
	
	
	/**
	 * 退出
	 * @param user
	 * @param req
	 * @param rep
	 * @return
	 */
	ElecResult logout(String token,HttpServletRequest req,HttpServletResponse rep);

}

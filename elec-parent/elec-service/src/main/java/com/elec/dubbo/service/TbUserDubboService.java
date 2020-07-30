package com.elec.dubbo.service;

import com.muzi.muxin.pojo.TbUser;

public interface TbUserDubboService {
	
	/**
	 * 根据用户名密码查询登陆
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
	

}

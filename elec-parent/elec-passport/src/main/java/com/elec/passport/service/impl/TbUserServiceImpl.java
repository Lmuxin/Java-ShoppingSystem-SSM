package com.elec.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commoms.pojo.ElecResult;
import com.elec.commons.utils.CookieUtils;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbUserDubboService;
import com.elec.passport.service.TbUserService;
import com.elec.redis.dao.JedisDao;
import com.muzi.muxin.pojo.TbUser;

@Service
public class TbUserServiceImpl implements TbUserService{
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoUImpl;
	
	
	
	@Override
	public ElecResult login(TbUser user,HttpServletRequest req,HttpServletResponse rep) {
		ElecResult er =new ElecResult();
		TbUser selByUser = tbUserDubboServiceImpl.selByUser(user);
		if(selByUser!=null) {
			er.setStatus(200);
			//用户登陆成功后把用户信息存入redis
			String key =UUID.randomUUID().toString();
			jedisDaoUImpl.set(key, JsonUtils.objectToJson(selByUser));
			//设置失效时间 7天
			jedisDaoUImpl.expire(key, 60*60*24*7);
			//产生cookie
			CookieUtils.setCookie(req, rep, "TT_TOKEN",key, 60*60*24*7);
			
		}else {
			er.setMsg("用户名或密码错误");
		}
		return er;
	}



	@Override
	public ElecResult getUserInfoByTokern(String token) {
		ElecResult er =new ElecResult();
		String json = jedisDaoUImpl.get(token);
		if(json!=null&&!json.equals("")) {
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
			tbUser.setPassword(null);
			er.setStatus(200);
			er.setMsg("OK");
			er.setData(tbUser);
		}else {
			er.setMsg("获取失败");
		}
		return er;
	}



	@Override
	public ElecResult logout(String token, HttpServletRequest req, HttpServletResponse rep) {
		jedisDaoUImpl.del(token);
		CookieUtils.deleteCookie(req, rep, "TT_TOKEN");
		ElecResult er =new ElecResult();
		er.setStatus(200);
		er.setMsg("OK");
		return er;
		
	}

}

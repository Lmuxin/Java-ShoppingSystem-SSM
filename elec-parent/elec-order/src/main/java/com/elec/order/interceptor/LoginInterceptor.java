package com.elec.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.elec.commoms.pojo.ElecResult;
import com.elec.commons.utils.CookieUtils;
import com.elec.commons.utils.HttpClientUtil;
import com.elec.commons.utils.JsonUtils;

public class LoginInterceptor implements HandlerInterceptor {
	
	

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object arg2) throws Exception {
		String token = CookieUtils.getCookieValue(req, "TT-TOKEN");
		if(token!=null&&!token.equals("")) {
			String json = HttpClientUtil.doPost("http://localhost:8084/user/token/"+token);
			ElecResult er = JsonUtils.jsonToPojo(json, ElecResult.class);
			if(er.getStatus()==200) {
				return true;
			}
		}
		String num =req.getParameter("num");
		if(num!=null&&!num.equals("")) {
			rep.sendRedirect("http://localhost:8084/user/showLogin?interurl="+req.getRequestURL()+"%3Fnum="+num);
		}else {
			rep.sendRedirect("http://localhost:8084/user/showLogin");
		}
		
		return false;
	}

}

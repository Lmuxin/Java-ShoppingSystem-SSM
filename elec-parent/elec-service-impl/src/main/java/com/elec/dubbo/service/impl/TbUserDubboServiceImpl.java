package com.elec.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.elec.dubbo.service.TbUserDubboService;
import com.muzi.muxin.mapper.TbUserMapper;
import com.muzi.muxin.pojo.TbUser;
import com.muzi.muxin.pojo.TbUserExample;

public class TbUserDubboServiceImpl implements TbUserDubboService {
	@Resource
	private TbUserMapper tbUserMapper;
	
	
	@Override
	public TbUser selByUser(TbUser user) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}

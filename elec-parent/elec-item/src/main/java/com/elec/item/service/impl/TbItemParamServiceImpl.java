package com.elec.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.elec.commons.utils.JsonUtils;
import com.elec.dubbo.service.TbItemParamItemDubboService;
import com.elec.item.pojo.ParamItem;
import com.elec.item.service.TbItemParamItemService;
import com.muzi.muxin.pojo.TbItemParamItem;

@Service
public class TbItemParamServiceImpl implements TbItemParamItemService {
	@Reference
	private TbItemParamItemDubboService tbItemParamItemDubboServiceImpl;
	@Override
	public String showParam(long itemId) {
		TbItemParamItem item = tbItemParamItemDubboServiceImpl.selByItemid(itemId);
		List<ParamItem> list = JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
		System.out.println(list);
		StringBuffer sf = new StringBuffer();
		
		for (ParamItem param : list) {
			sf.append("<table width='500' style='color:gray;'>");
			for (int i = 0 ;i<param.getParams().size();i++) {
				if(i==0){
					sf.append("<tr>");
					sf.append("<td align='right' width='30%'>"+param.getGroup()+"</td>");
					sf.append("<td align='right' width='30%'>"+param.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
					sf.append("<tr/>");
				}else{
					sf.append("<tr>");
					sf.append("<td> </td>");
					sf.append("<td align='right'>"+param.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
					sf.append("</tr>");
				}
			}
			sf.append("</table>");
			sf.append("<hr style='color:gray;'/>");
		}
		return sf.toString();
	}

}

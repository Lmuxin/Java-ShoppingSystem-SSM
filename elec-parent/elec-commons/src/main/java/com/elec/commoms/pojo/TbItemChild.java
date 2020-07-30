package com.elec.commoms.pojo;

import com.muzi.muxin.pojo.TbItem;

public class TbItemChild extends TbItem {
	private String[] images;
	//库存是否满足
	private Boolean enough;

	public String[] getImages() {
		return images;
	}

	public Boolean getEnough() {
		return enough;
	}

	public void setEnough(Boolean enough) {
		this.enough = enough;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

}

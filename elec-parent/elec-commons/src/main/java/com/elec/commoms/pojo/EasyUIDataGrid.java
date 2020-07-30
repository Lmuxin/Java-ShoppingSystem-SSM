package com.elec.commoms.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGrid implements Serializable {
	//当前列显示条数
	private List<?> rows;
	//总条数
	private long total;
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	

}

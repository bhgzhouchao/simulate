package com.zjt.model;

import java.io.Serializable;
import java.util.List;

public class BusinessDataVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String month;
	
	private String remarks;
	
	private String type;
	
	private List<CommVo> businessList;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public List<CommVo> getBusinessList() {
		return businessList;
	}
	
	public void setBusinessList(List<CommVo> businessList) {
		this.businessList = businessList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

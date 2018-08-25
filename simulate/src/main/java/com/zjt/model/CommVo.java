package com.zjt.model;

import java.io.Serializable;

public class CommVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String num;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}

package com.zjt.service;

import java.util.List;

import com.zjt.entity.Dept;
import com.zjt.entity.Tuser;

public interface TuserService  extends IService<Tuser>{

	List<Dept> selectDeptList();
	
}
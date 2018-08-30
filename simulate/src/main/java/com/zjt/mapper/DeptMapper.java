package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Business;
import com.zjt.entity.Dept;
import com.zjt.util.MyMapper;

public interface DeptMapper extends MyMapper<Dept> {
	List<Dept> getDeptListSerch(Map<String, Object> params);
	
	int getDeptByDeptId(Dept dept);
	
	int getDeptByDeptName(Dept dept);
	
	List<Dept>  getDeptList();

	int getBusinessByDeptName(Business business);
}
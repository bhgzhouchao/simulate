package com.zjt.service;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Dept;

public interface DeptService extends IService<Dept> {
	
	Map<String, Object> getDeptListSerch(Map<String,Object> params);

	Map<String, Object> addOrUpdateDept(Dept dept);

	Map<String, Object> getUserList();

	Map<String, Object> deleteDepts(List<String> reserveOrderNoList);

	Map<String, Object> selectDeptById(String id);

}

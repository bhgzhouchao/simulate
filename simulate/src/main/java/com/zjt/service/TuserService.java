package com.zjt.service;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Dept;
import com.zjt.entity.Tuser;

public interface TuserService  extends IService<Tuser>{

	List<Dept> selectDeptList();

	Map<String, Object> getUserListBySearch(Map<String, Object> params);

	Map<String, Object> deleteUsers(List<String> ids);
	
}
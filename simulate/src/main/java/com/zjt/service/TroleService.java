package com.zjt.service;

import com.zjt.entity.Trole;

import java.util.List;
import java.util.Map;

public interface TroleService extends IService<Trole>{

	//根据userid查询所有的角色
    List<Trole> selectRolesByUserId(Integer userid);

	Map<String, Object> getRoleListBySearch(Map<String, Object> params);

	Map<String, Object> deleteDepts(List<String> ids);

}
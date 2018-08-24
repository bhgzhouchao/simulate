package com.zjt.mapper;

import com.zjt.entity.Trole;
import com.zjt.entity.Tuser;
import com.zjt.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface TroleMapper extends MyMapper<Trole> {

    List<Trole> selectRolesByUserId(Integer userid);

	List<Tuser> getRoleListBySearch(Map<String, Object> params);

}
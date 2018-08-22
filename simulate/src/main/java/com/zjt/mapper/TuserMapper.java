package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Tuser;
import com.zjt.util.MyMapper;

public interface TuserMapper extends MyMapper<Tuser> {

	List<Tuser> getUserListSerch(Map<String, Object> params);
}
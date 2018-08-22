package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zjt.entity.Business;
import com.zjt.util.MyMapper;

public interface BusinessMapper extends MyMapper<Business> {

	List<Business> getBusinessListSerch(Map<String, Object> params);

	List<Business> selectBusinessList(@Param(value="deptId") String dept);
}
package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.BusinessData;
import com.zjt.entity.Dept;
import com.zjt.util.MyMapper;

public interface BusinessDataMapper extends MyMapper<BusinessData> {

	List<Dept> getBusinessDataListSerch(Map<String, Object> params);
}
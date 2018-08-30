package com.zjt.mapper;

import java.util.Map;

import com.zjt.entity.BaseData;
import com.zjt.util.MyMapper;

public interface BaseDataMapper extends MyMapper<BaseData> {

	BaseData selectBaseDataByMonth(Map<String, Object> params);
}
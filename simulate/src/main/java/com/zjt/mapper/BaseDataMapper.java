package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.BaseData;
import com.zjt.util.MyMapper;

public interface BaseDataMapper extends MyMapper<BaseData> {

	BaseData selectBaseDataByMonth(Map<String, Object> params);

	List<BaseData> getBaseDataListSerch(Map<String, Object> params);

	int getBaseDataById(BaseData baseData);
}
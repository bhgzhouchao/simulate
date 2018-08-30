package com.zjt.mapper;

import java.util.Map;

import com.zjt.entity.ErpData;
import com.zjt.util.MyMapper;

public interface ErpDataMapper extends MyMapper<ErpData> {

	ErpData selectErpDataMonth(Map<String, Object> params);
}
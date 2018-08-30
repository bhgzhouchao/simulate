package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.BusinessData;
import com.zjt.util.MyMapper;

public interface BusinessDataMapper extends MyMapper<BusinessData> {

	List<BusinessData> getBusinessDataListSerch(Map<String, Object> params);

	Integer insertBusinessData(BusinessData businessData);

	List<BusinessData> getDeptExamineListSerch(Map<String, Object> params);

	List<BusinessData> getLeaderExamineListSerch(Map<String, Object> params);

	BusinessData selectBusinesData(List<String> months);
}
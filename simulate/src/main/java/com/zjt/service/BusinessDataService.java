package com.zjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.BusinessData;

public interface BusinessDataService extends IService<BusinessData> {
	Map<String, Object> getBusinessDataListSerch(Map<String,Object> params);

	Map<String, Object> addOrUpdateBusinessData(BusinessData business);

	Map<String, Object> getBusinessTypeList();

	Map<String, Object> deleteBusinessData(List<String> reserveOrderNoList);

	Map<String, Object> selectBusinessById(String id);

	ModelAndView toBusiness();
}

package com.zjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Business;

public interface BusinessService extends IService<Business> {
	Map<String, Object> getBusinessListSerch(Map<String,Object> params);

	Map<String, Object> addOrUpdateBusiness(Business business);

	Map<String, Object> getBusinessTypeList();

	Map<String, Object> deleteBusiness(List<String> reserveOrderNoList);

	Map<String, Object> selectBusinessById(String id);

	ModelAndView toBusiness();
}

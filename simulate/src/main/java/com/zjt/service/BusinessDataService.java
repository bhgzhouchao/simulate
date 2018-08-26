package com.zjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.BusinessData;
import com.zjt.model.BusinessDataVo;

public interface BusinessDataService extends IService<BusinessData> {
	Map<String, Object> getBusinessDataListSerch(Map<String,Object> params);

	Map<String, Object> addOrUpdateBusinessData(BusinessDataVo businessDataVo);

	Map<String, Object> getBusinessTypeList();

	Map<String, Object> selectBusinessById(String id);

	ModelAndView toBusiness();

	Map<String, Object> toBusinessDataAdd(String type, String id);

	Map<String, Object> deleteBusinessDatas(List<String> ids);

	Map<String, Object> examineBusinessData(List<String> ids, boolean b,String type);

}

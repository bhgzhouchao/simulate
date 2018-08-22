package com.zjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Business;
import com.zjt.entity.BusinessData;
import com.zjt.entity.Dept;
import com.zjt.entity.Tuser;
import com.zjt.mapper.BusinessDataDetailMapper;
import com.zjt.mapper.BusinessDataMapper;
import com.zjt.mapper.BusinessMapper;
import com.zjt.service.BusinessDataService;

@Service("BusinessDataService")
public class BusinessDataServiceImpl extends BaseService<BusinessData> implements  BusinessDataService {
	
	@Autowired
	private BusinessDataMapper businessDataMapper;
	
	@Autowired
	private BusinessDataDetailMapper businessDataDetailMapper;
	
	@Autowired
	private BusinessMapper businessMapper;
	
	@Override
	public Map<String, Object> getBusinessDataListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Dept> list = businessDataMapper.getBusinessDataListSerch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", businessDataMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateBusinessData(BusinessData business) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getBusinessTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteBusinessData(List<String> reserveOrderNoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBusinessById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView toBusiness() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> toBusinessDataAdd() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		Tuser loginUser =(Tuser) subject.getSession().getAttribute("user");
		List<Business> businessList = businessMapper.selectBusinessList(loginUser.getDept());
		returnMap.put("businessList", businessList);
		returnMap.put("state", "success");
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateBusinessData(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}

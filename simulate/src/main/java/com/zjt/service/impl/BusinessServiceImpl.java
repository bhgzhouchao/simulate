package com.zjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Business;
import com.zjt.entity.BusinessType;
import com.zjt.entity.Dept;
import com.zjt.mapper.BusinessMapper;
import com.zjt.mapper.BusinessTypeMapper;
import com.zjt.mapper.DeptMapper;
import com.zjt.service.BusinessService;

@Service("BusinessService")
public class BusinessServiceImpl extends BaseService<Business> implements  BusinessService {
	@Autowired
	private BusinessMapper businessMapper;
	
	@Autowired
	private BusinessTypeMapper businessTypeMapper;
	
	@Autowired
	private DeptMapper deptMapper;

	@Override
	public ModelAndView toBusiness() {
		ModelAndView mav = new ModelAndView("power/business");
		mav.addObject("deptList",deptMapper.selectAll());
		return mav;
	}

	@Override
	public Map<String, Object> getBusinessListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Business> list = businessMapper.getBusinessListSerch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", businessMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateBusiness(Business business) {
		Map<String,Object> retuenMap =new HashMap<String, Object>();
		try {
			//新增
			if(business.getId() == null || business.getId().toString() == "") {
				/*int deptNameCount= deptMapper.getDeptByDeptName(dept);
				if(deptNameCount > 1 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门名称重复，请重新输入!");
					return retuenMap;
				}*/
				saveNotNull(business);
				retuenMap.put("state", "success");
				retuenMap.put("mesg", "保存成功!");
			} else {
				/*int deptNameCount= deptMapper.getDeptByDeptName(dept);
				if(deptNameCount > 1 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门名称重复，请重新输入!");
					return retuenMap;
				}*/
				updateNotNull(business);
				retuenMap.put("state", "success");
				retuenMap.put("mesg", "保存成功!");
			}
		} catch(Exception e) {
			retuenMap.put("state", "fail");
			retuenMap.put("mesg", "保存失败，请稍后再试！");
			e.printStackTrace();
		}
		
		return retuenMap;
	}

	@Override
	public Map<String, Object> getBusinessTypeList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<BusinessType> busiTypeList = businessTypeMapper.selectAll();
		List<Dept> deptList = deptMapper.selectAll();
		returnMap.put("state", "success");
		returnMap.put("busiTypeList",busiTypeList);
		returnMap.put("deptList",deptList);
		return returnMap;
	}

	@Override
	public Map<String, Object> deleteBusiness(List<String> Ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : Ids) {
				businessMapper.deleteByPrimaryKey(Integer.valueOf(id));
			}
			returnMap.put("state", "success");
			returnMap.put("mesg", "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "删除失败，请稍后再试!");
			return returnMap;
		}
		
		return returnMap;
	}

	@Override
	public Map<String, Object> selectBusinessById(String id) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			Business business = businessMapper.selectByPrimaryKey(Integer.valueOf(id));
			List<BusinessType> busiTypeList = businessTypeMapper.selectAll();
			List<Dept> deptList = deptMapper.selectAll();
			returnMap.put("state", "success");
			returnMap.put("business", business);
			returnMap.put("busiTypeList", busiTypeList);
			returnMap.put("deptList",deptList);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "系统异常，请稍后再试!");
		}
		return returnMap;
	}

}

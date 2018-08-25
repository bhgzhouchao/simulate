package com.zjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjt.entity.Business;
import com.zjt.entity.BusinessType;
import com.zjt.entity.Dept;
import com.zjt.entity.Quota;
import com.zjt.entity.Subject;
import com.zjt.mapper.QuotaMapper;
import com.zjt.mapper.SubjectMapper;
import com.zjt.service.QuotaService;

@Service("QuotaService")
public class QuotaServiceImpl extends BaseService<Quota> implements QuotaService {
	
	@Autowired
	private QuotaMapper quotaMapper;
	
	@Autowired
	private SubjectMapper subjectMapper;
	
	@Override
	public Map<String, Object> getQuotaListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Business> list = quotaMapper.getQuotaListSerch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", quotaMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateQuota(Quota quota) {
		Map<String,Object> retuenMap =new HashMap<String, Object>();
		try {
			//新增
			if(quota.getId() == null || quota.getId().toString() == "") {
				saveNotNull(quota);
				retuenMap.put("state", "success");
				retuenMap.put("mesg", "保存成功!");
			} else {
				updateNotNull(quota);
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
	public Map<String, Object> getSubjectList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Subject> subjectList = subjectMapper.selectAll();
		returnMap.put("state", "success");
		returnMap.put("subjectList",subjectList);
		return returnMap;
	}

	@Override
	public Map<String, Object> deleteQuotas(List<String> Ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : Ids) {
				quotaMapper.deleteByPrimaryKey(Integer.valueOf(id));
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
	public Map<String, Object> selectQuotaById(String id) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			Quota quota = quotaMapper.selectByPrimaryKey(Integer.valueOf(id));
			List<Subject> subjectList = subjectMapper.selectAll();
			returnMap.put("state", "success");
			returnMap.put("quota", quota);
			returnMap.put("subjectList", subjectList);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "系统异常，请稍后再试!");
		}
		return returnMap;
	}

}

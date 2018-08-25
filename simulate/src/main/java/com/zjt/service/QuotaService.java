package com.zjt.service;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Quota;


public interface QuotaService  extends IService<Quota>{
	
	Map<String, Object> getQuotaListSerch(Map<String,Object> params);

	Map<String, Object> addOrUpdateQuota(Quota quota);

	Map<String, Object> getSubjectList();

	Map<String, Object> deleteQuotas(List<String> reserveOrderNoList);

	Map<String, Object> selectQuotaById(String id);
}

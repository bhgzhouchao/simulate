package com.zjt.mapper;

import java.util.List;
import java.util.Map;

import com.zjt.entity.Business;
import com.zjt.entity.Quota;
import com.zjt.util.MyMapper;

public interface QuotaMapper extends MyMapper<Quota> {

	List<Business> getQuotaListSerch(Map<String, Object> params);

	int selectIndexByYear(int id, int year);

	Quota selectIndexByYear(Quota quota);

	int getQuotaById(Quota quota);
}
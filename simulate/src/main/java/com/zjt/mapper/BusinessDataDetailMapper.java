package com.zjt.mapper;

import java.util.List;

import com.zjt.entity.BusinessDataDetail;
import com.zjt.util.MyMapper;

public interface BusinessDataDetailMapper extends MyMapper<BusinessDataDetail> {

	void deleteByBusinessDataId(String businessDataId);

	List<BusinessDataDetail> selectListByBusinessDataId(String id);
}
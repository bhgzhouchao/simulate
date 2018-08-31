package com.zjt.service;

import java.util.List;
import java.util.Map;

import com.zjt.entity.BaseData;

public interface BaseDataService extends IService<BaseData>  {

	Map<String, Object> getBaseDataListSerch(Map<String, Object> params);

	Map<String, Object> addOrUpdateBaseData(BaseData baseData);

	Map<String, Object> deleteBaseDatas(List<String> ids);

	Map<String, Object> selectBaseDataById(String id);

}

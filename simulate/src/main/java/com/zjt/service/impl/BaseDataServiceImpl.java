package com.zjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjt.entity.BaseData;
import com.zjt.mapper.BaseDataMapper;
import com.zjt.service.BaseDataService;

@Service("BaseDataService")
public class BaseDataServiceImpl extends BaseService<BaseData> implements  BaseDataService {
	
	@Autowired
	private BaseDataMapper baseDataMapper;

	@Override
	public Map<String, Object> getBaseDataListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<BaseData> list = baseDataMapper.getBaseDataListSerch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", baseDataMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateBaseData(BaseData baseData) {
		Map<String,Object> retuenMap =new HashMap<String, Object>();
		try {
			//判断相同的年月是否数据重复
			int baseDataIdCount= baseDataMapper.getBaseDataById(baseData);
			if(baseDataIdCount > 0 ) {
				retuenMap.put("state", "fail");
				retuenMap.put("mesg", "当前月份已存在数据，请重新输入!");
				return retuenMap;
			}
			//新增
			if(baseData.getId() == null || baseData.getId().toString() == "") {
				saveNotNull(baseData);
				retuenMap.put("state", "success");
				retuenMap.put("mesg", "保存成功!");
			} else {
				updateNotNull(baseData);
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
	public Map<String, Object> deleteBaseDatas(List<String> ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : ids) {
				baseDataMapper.deleteByPrimaryKey(Integer.valueOf(id));
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
	public Map<String, Object> selectBaseDataById(String id) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			BaseData baseData = baseDataMapper.selectByPrimaryKey(Integer.valueOf(id));
			returnMap.put("state", "success");
			returnMap.put("baseData", baseData);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "系统异常，请稍后再试!");
			return returnMap;
		}
		return returnMap;
	}

}

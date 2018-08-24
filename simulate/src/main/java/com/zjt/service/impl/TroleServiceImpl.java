package com.zjt.service.impl;

import com.zjt.entity.Trole;
import com.zjt.entity.Trolemenu;
import com.zjt.entity.Tuser;
import com.zjt.mapper.TroleMapper;
import com.zjt.service.TroleService;
import com.zjt.service.TrolemenuService;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @version 1.0, 2017/11/10
 * @description
 */

@Service("troleService")
public class TroleServiceImpl   extends BaseService<Trole> implements TroleService {
    @Autowired
    private TroleMapper troleMapper;
    
    @Resource
    private TrolemenuService trolemenuService;
    
    @Override
    public List<Trole> selectRolesByUserId(Integer userid) {
        return troleMapper.selectRolesByUserId(userid);
    }

	@Override
	public Map<String, Object> getRoleListBySearch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Tuser> list = troleMapper.getRoleListBySearch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", troleMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> deleteDepts(List<String> ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : ids) {
				troleMapper.deleteByPrimaryKey(Integer.valueOf(id));
				Example trolemenuexample = new Example(Trolemenu.class);
	            trolemenuexample.or().andEqualTo("roleId",id);
	            trolemenuService.deleteByExample(trolemenuexample);
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
}

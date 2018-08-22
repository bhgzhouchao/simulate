package com.zjt.service.impl;

import com.zjt.entity.Dept;
import com.zjt.entity.Trole;
import com.zjt.entity.Tuser;
import com.zjt.mapper.DeptMapper;
import com.zjt.mapper.TroleMapper;
import com.zjt.mapper.TuserMapper;
import com.zjt.service.TuserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0, 2017/11/10
 * @description
 */
@Service("tuserService")
public class TuserServiceImpl   extends BaseService<Tuser> implements TuserService {
	
	@Autowired
	private TuserMapper userMapper;
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private TroleMapper roleMapper;
	
	
	@Override
	public List<Dept> selectDeptList() {
		List<Dept> list = deptMapper.getDeptList();
		return list;
	}


	@Override
	public Map<String, Object> getUserListBySearch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Tuser> list = userMapper.getUserListSerch(params);
		for (Tuser u : list) {
            List<Trole> roleList = roleMapper.selectRolesByUserId(u.getId());
            StringBuffer sb = new StringBuffer();
            for (Trole r : roleList) {
                sb.append("," + r.getName());
            }
            u.setRoles(sb.toString().replaceFirst(",", ""));
        }
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", userMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}


	@Override
	public Map<String, Object> deleteUsers(List<String> ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : ids) {
				userMapper.deleteByPrimaryKey(Integer.valueOf(id));
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

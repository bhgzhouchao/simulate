package com.zjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjt.entity.Dept;
import com.zjt.entity.Tuser;
import com.zjt.mapper.DeptMapper;
import com.zjt.mapper.TuserMapper;
import com.zjt.service.DeptService;

@Service("DeptService")
public class DeptServiceImpl extends BaseService<Dept> implements  DeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	@Autowired
	private TuserMapper userMapper;
	
	@Override
	public Map<String, Object> getDeptListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		List<Dept> list = deptMapper.getDeptListSerch(params);
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", deptMapper.selectAll().size());
		returnMap.put("data",list);
		return returnMap;
	}

	@Override
	public Map<String, Object> addOrUpdateDept(Dept dept) {
		Map<String,Object> retuenMap =new HashMap<String, Object>();
		try {
			//新增
			if(dept.getId() == null || dept.getId().toString() == "") {
				int deptIdCount= deptMapper.getDeptByDeptId(dept);
				if(deptIdCount > 0 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门编号重复，请重新输入!");
					return retuenMap;
				}
				int deptNameCount= deptMapper.getDeptByDeptName(dept);
				if(deptNameCount > 0 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门名称重复，请重新输入!");
					return retuenMap;
				}
				saveNotNull(dept);
				retuenMap.put("state", "success");
				retuenMap.put("mesg", "保存成功!");
			} else {
				int deptIdCount= deptMapper.getDeptByDeptId(dept);
				if(deptIdCount > 0 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门编号重复，请重新输入!");
					return retuenMap;
				}
				int deptNameCount= deptMapper.getDeptByDeptName(dept);
				if(deptNameCount > 0 ) {
					retuenMap.put("state", "fail");
					retuenMap.put("mesg", "部门名称重复，请重新输入!");
					return retuenMap;
				}
				updateNotNull(dept);
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
	public Map<String, Object> getUserList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Tuser> userList = userMapper.selectAll();
		returnMap.put("state", "success");
		returnMap.put("userList",userList);
		return returnMap;
	}

	@Override
	public Map<String, Object> deleteDepts(List<String> Ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			for(String id : Ids) {
				deptMapper.deleteByPrimaryKey(Integer.valueOf(id));
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
	public Map<String, Object> selectDeptById(String id) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			Dept dept = deptMapper.selectByPrimaryKey(Integer.valueOf(id));
			List<Tuser> userList = userMapper.selectAll();
			returnMap.put("state", "success");
			returnMap.put("dept", dept);
			returnMap.put("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "系统异常，请稍后再试!");
			return returnMap;
		}
		return returnMap;
	}

	

}

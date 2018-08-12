package com.zjt.web;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjt.entity.Dept;
import com.zjt.entity.Trole;
import com.zjt.entity.Tuser;
import com.zjt.entity.Tuserrole;
import com.zjt.service.DeptService;

import tk.mybatis.mapper.entity.Example;


@Controller
@RequestMapping("/admin/dept")
public class DeptController {
	
	@Resource
	private DeptService deptService;
	
	
	@RequestMapping("/todept")
    @RequiresPermissions(value = {"部门管理"})
    public String tousermanage() {
        return "power/dept";
    }
	
	
	@RequestMapping("/deptList")
	@ResponseBody
    @RequiresPermissions(value = {"部门管理"})
    public Map<String,Object> getDeptList(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = deptService.getDeptListSerch(params);
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateDept")
    @RequiresPermissions(value = {"部门管理"})
    public Map<String, Object> addOrUpdateDept(Dept dept) {
		Map<String, Object> map = deptService.addOrUpdateDept(dept);
    	return map;
    }
	
	@RequestMapping("/getUserList")
	@ResponseBody
    @RequiresPermissions(value = {"部门管理"})
    public Map<String,Object> getUserList() {
		Map<String, Object> map = deptService.getUserList();
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteDepts")
    @RequiresPermissions(value = {"部门管理"})
    public Map<String, Object> deleDepts(@RequestParam List<String> Ids) {
		Map<String, Object> map = deptService.deleteDepts(Ids);
		return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/selectDeptById")
    @RequiresPermissions(value = {"部门管理"})
    public Map<String, Object> selectDeptById(@RequestParam String id) {
		Map<String, Object> map = deptService.selectDeptById(id);
		return map;
    }
}

package com.zjt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjt.entity.Quota;
import com.zjt.service.QuotaService;

@Controller
@RequestMapping("/busi/quota")
public class QuotaController {
	@Resource
	private QuotaService quotaService;
	
	
	@RequestMapping("/toQuota")
    @RequiresPermissions(value = {"下放指标管理"})
    public String tousermanage() {
        return "power/quota";
    }
	
	
	@RequestMapping("/quotaList")
	@ResponseBody
    @RequiresPermissions(value = {"下放指标管理"})
    public Map<String,Object> getQuotaList(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = quotaService.getQuotaListSerch(params);
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateQuota")
    @RequiresPermissions(value = {"下放指标管理"})
    public Map<String, Object> addOrUpdateQuota(Quota dept) {
		Map<String, Object> map = quotaService.addOrUpdateQuota(dept);
    	return map;
    }
	
	@RequestMapping("/getSubjectList")
	@ResponseBody
    @RequiresPermissions(value = {"下放指标管理"})
    public Map<String,Object> getSubjectList() {
		Map<String, Object> map = quotaService.getSubjectList();
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteQuotas")
    @RequiresPermissions(value = {"下放指标管理"})
    public Map<String, Object> deleQuotas(@RequestParam List<String> Ids) {
		Map<String, Object> map = quotaService.deleteQuotas(Ids);
		return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/selectQuotaById")
    @RequiresPermissions(value = {"下放指标管理"})
    public Map<String, Object> selectQuotaById(@RequestParam String id) {
		Map<String, Object> map = quotaService.selectQuotaById(id);
		return map;
    }
}

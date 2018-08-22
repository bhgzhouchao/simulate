package com.zjt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Business;
import com.zjt.service.BusinessService;

@Controller
@RequestMapping("/admin/business")
public class BusinessController {

	@Resource
	private BusinessService businessService;
	
	
	@RequestMapping("/toBusi")
    @RequiresPermissions(value = {"业务管理"})
    public ModelAndView toBusiness() {
		ModelAndView modelView = businessService.toBusiness();
        return modelView;
    }
	
	
	@RequestMapping("/busiList")
	@ResponseBody
    @RequiresPermissions(value = {"业务管理"})
    public Map<String,Object> getBusinessList(@RequestParam Map<String, Object> params,HttpServletRequest request) {
		Map<String, Object> map = businessService.getBusinessListSerch(params);
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateBusi")
    @RequiresPermissions(value = {"业务管理"})
    public Map<String, Object> addOrUpdateBusiness(Business Business) {
		Map<String, Object> map = businessService.addOrUpdateBusiness(Business);
    	return map;
    }
	
	@RequestMapping("/getBusiTypeList")
	@ResponseBody
    @RequiresPermissions(value = {"业务管理"})
    public Map<String,Object> getBusinessTypeList() {
		Map<String, Object> map = businessService.getBusinessTypeList();
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteBusis")
    @RequiresPermissions(value = {"业务管理"})
    public Map<String, Object> deleBusinesss(@RequestParam List<String> Ids) {
		Map<String, Object> map = businessService.deleteBusiness(Ids);
		return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/selectBusiById")
    @RequiresPermissions(value = {"业务管理"})
    public Map<String, Object> selectBusinessById(@RequestParam String id) {
		Map<String, Object> map = businessService.selectBusinessById(id);
		return map;
    }

}

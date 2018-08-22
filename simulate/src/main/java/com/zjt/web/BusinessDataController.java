package com.zjt.web;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Dept;
import com.zjt.service.BusinessDataService;

@Controller
@RequestMapping("/busi/busiData")
public class BusinessDataController {
	
	@Resource
	private BusinessDataService businessDataService;
	
	
	@RequestMapping("/toBusinessData")
    @RequiresPermissions(value = {"业务数据录入"})
    public String toBusinessData() {
        return "power/businessData";
    }
	
	
	@RequestMapping("/businessDataList")
	@ResponseBody
    @RequiresPermissions(value = {"业务数据录入"})
    public Map<String,Object> getBusinessDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = businessDataService.getBusinessDataListSerch(params);
    	return map;
    }
	
	/*@RequestMapping("/toBusinessDataAdd")
	@ResponseBody
    @RequiresPermissions(value = {"业务数据录入"})
    public Map<String,Object> toBusinessDataAdd() {
		Map<String, Object> map = businessDataService.toBusinessDataAdd();
    	return map;
    }*/
	
	@RequestMapping("/toBusinessDataAdd")
    @RequiresPermissions(value = {"业务数据录入"})
    public ModelAndView toBusinessDataAdd() {
		ModelAndView modelView = new ModelAndView("power/businessDataDetail");
		Map<String, Object> map = businessDataService.toBusinessDataAdd();
		modelView.addObject("businessList", map.get("businessList"));
        return modelView;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateBusinessData")
    @RequiresPermissions(value = {"业务数据录入"})
    public Map<String, Object> addOrUpdateBusinessData(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = businessDataService.addOrUpdateBusinessData(params);
    	return map;
    }
	
}

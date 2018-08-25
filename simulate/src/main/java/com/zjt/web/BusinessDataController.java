package com.zjt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.model.BusinessDataVo;
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
	
	
	@RequestMapping("/toBusinessDataAdd")
    @RequiresPermissions(value = {"业务数据录入"})
    public ModelAndView toBusinessDataAdd(@RequestParam String type,@RequestParam String id) {
		ModelAndView modelView = new ModelAndView("power/businessDataDetail");
		Map<String, Object> map = businessDataService.toBusinessDataAdd(type,id);
		modelView.addObject("businessList", map.get("businessList"));
		if(type == "2" || "2".equals(type)) {
			modelView.addObject("businessData", map.get("businessData"));
		}
        return modelView;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateBusinessData")
    @RequiresPermissions(value = {"业务数据录入"})
    public Map<String, Object> addOrUpdateBusinessData(@RequestBody BusinessDataVo businessDataVo) {
		Map<String, Object> map = businessDataService.addOrUpdateBusinessData(businessDataVo);
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/deleteBusinessDatas")
    @RequiresPermissions(value = {"业务数据录入"})
    public Map<String, Object> deleteBusinessDatas(@RequestParam List<String> Ids) {
		Map<String, Object> map = businessDataService.deleteBusinessDatas(Ids);
		return map;
    }
}

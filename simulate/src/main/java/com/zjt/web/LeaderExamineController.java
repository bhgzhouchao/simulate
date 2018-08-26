package com.zjt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.service.BusinessDataService;

@Controller
@RequestMapping("/busi/leaderExamine")
public class LeaderExamineController {
	@Resource
	private BusinessDataService businessDataService;
	
	
	@RequestMapping("/toLeaderExamine")
    @RequiresPermissions(value = {"财务审批"})
    public String toBusinessData() {
        return "power/leaderExamine";
    }
	
	@RequestMapping("/businessDataList")
	@ResponseBody
    @RequiresPermissions(value = {"财务审批"})
    public Map<String,Object> getBusinessDataList(@RequestParam Map<String, Object> params) {
		params.put("type", "leaderExamine");
		Map<String, Object> map = businessDataService.getBusinessDataListSerch(params);
    	return map;
    }
	
	@RequestMapping("/toBusinessDataAdd")
    @RequiresPermissions(value = {"财务审批"})
    public ModelAndView toBusinessDataAdd(@RequestParam String id) {
		ModelAndView modelView = new ModelAndView("power/leaderExamineDetail");
		Map<String, Object> map = businessDataService.toBusinessDataAdd("2",id);
		modelView.addObject("businessList", map.get("businessList"));
		modelView.addObject("businessData", map.get("businessData"));
        return modelView;
    }
	

	@ResponseBody
    @RequestMapping(value = "/examineYes")
    @RequiresPermissions(value = {"财务审批"})
    public Map<String, Object> examineYes(@RequestParam List<String> Ids) {
		Map<String, Object> map = businessDataService.examineBusinessData(Ids,true,"leaderExamine");
		return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/examineNo")
    @RequiresPermissions(value = {"财务审批"})
    public Map<String, Object> examineNo(@RequestParam List<String> Ids) {
		Map<String, Object> map = businessDataService.examineBusinessData(Ids,false,"leaderExamine");
		return map;
    }
}


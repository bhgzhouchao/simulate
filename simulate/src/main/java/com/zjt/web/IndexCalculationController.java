package com.zjt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjt.entity.Dept;
import com.zjt.service.IndexCalculationService;

@Controller
@RequestMapping("/busi/indexCalculation")
public class IndexCalculationController {
	
	@Resource
	private IndexCalculationService indexCalculation;
	
	@RequestMapping("/toIndex")
    @RequiresPermissions(value = {"指标测算表"})
    public String tousermanage() {
        return "power/indexCalculation";
    }
	
	@RequestMapping("/indexList")
	@ResponseBody
    @RequiresPermissions(value = {"指标测算表"})
    public Map<String,Object> getIndexCalculation(@RequestParam Map<String, Object> params) {
		Map<String, Object> returnMap = indexCalculation.getIndexCalculation(params);
    	return returnMap;
    }
	
}

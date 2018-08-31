package com.zjt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjt.entity.BaseData;
import com.zjt.service.BaseDataService;

@Controller
@RequestMapping("/busi/baseData")
public class BaseDataController {
	@Resource
	private BaseDataService baseDataService;
	
	@RequestMapping("/toBaseData")
    @RequiresPermissions(value = {"基础数据管理"})
    public String tousermanage() {
        return "power/baseData";
    }
	@RequestMapping("/baseDataList")
	@ResponseBody
    @RequiresPermissions(value = {"基础数据管理"})
    public Map<String,Object> getBaseDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = baseDataService.getBaseDataListSerch(params);
    	return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/addOrUpdateBaseData")
    @RequiresPermissions(value = {"基础数据管理"})
    public Map<String, Object> addOrUpdateBaseData(BaseData baseData) {
		Map<String, Object> map = baseDataService.addOrUpdateBaseData(baseData);
    	return map;
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/deleteBaseDatas")
    @RequiresPermissions(value = {"基础数据管理"})
    public Map<String, Object> deleBaseDatas(@RequestParam List<String> Ids) {
		Map<String, Object> map = baseDataService.deleteBaseDatas(Ids);
		return map;
    }
	
	@ResponseBody
    @RequestMapping(value = "/selectBaseDataById")
    @RequiresPermissions(value = {"基础数据管理"})
    public Map<String, Object> selectBaseDataById(@RequestParam String id) {
		Map<String, Object> map = baseDataService.selectBaseDataById(id);
		return map;
    }
}

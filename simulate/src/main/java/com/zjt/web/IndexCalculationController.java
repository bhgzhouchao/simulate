package com.zjt.web;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjt.model.IndexVo;
import com.zjt.service.IndexCalculationService;
import com.zjt.util.ExcelUtil;

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
	
	@RequestMapping("/export")
	@ResponseBody
    @RequiresPermissions(value = {"指标测算表"})
    public void export(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response) {
		//查询数据
		Map<String, Object> returnMap = indexCalculation.getIndexCalculation(params);
		
		List<IndexVo> dalaList = (List<IndexVo>) returnMap.get("data");
		//创建表头
		Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        
        //文件名
        String fileName = "电科院" + year + "年内模指标测算表";
        //sheet名
        String sheetName = "模指标测算表";
        
        List<String> titleList = getTitleList(params, cal);
        
        Object type = params.get("type");
        
        String [][] content = new String[dalaList.size()][];
        for (int i = 0; i < dalaList.size(); i++) {
        	content[i] = new String[titleList.size()];
            IndexVo indexVo = dalaList.get(i);
            if(type == null || type.toString() == "season" || type.toString().equals("season")) {
            	if(month == 1 || month == 2 || month == 3){
            		content[i][0] = indexVo.getSubject();
            		content[i][1] = indexVo.getSeason1().toString();
            		content[i][2] = indexVo.getLastsSeason1().toString();
            		content[i][3] = indexVo.getOldYearAll().toString();
            		content[i][4] = indexVo.getIndex().toString();
            		content[i][5] = indexVo.getYearAll().toString();
        		} else if(month == 4 || month == 5 || month == 6){
        			content[i][0] = indexVo.getSubject();
            		content[i][1] = indexVo.getSeason1().toString();
            		content[i][2] = indexVo.getLastsSeason1().toString();
            		content[i][3] = indexVo.getSeason2().toString();
            		content[i][4] = indexVo.getLastsSeason2().toString();
            		content[i][5] = indexVo.getOldYearAll().toString();
            		content[i][6] = indexVo.getIndex().toString();
            		content[i][7] = indexVo.getYearAll().toString();
        		} else if(month == 7 || month == 8 || month == 9){
        			content[i][0] = indexVo.getSubject();
            		content[i][1] = indexVo.getSeason1().toString();
            		content[i][2] = indexVo.getLastsSeason1().toString();
            		content[i][3] = indexVo.getSeason2().toString();
            		content[i][4] = indexVo.getLastsSeason2().toString();
            		content[i][5] = indexVo.getSeason2().toString();
            		content[i][6] = indexVo.getLastsSeason2().toString();
            		content[i][7] = indexVo.getOldYearAll().toString();
            		content[i][8] = indexVo.getIndex().toString();
            		content[i][9] = indexVo.getYearAll().toString();
        		} else if(month == 10 || month == 11 || month == 12){
        			content[i][0] = indexVo.getSubject();
            		content[i][1] = indexVo.getSeason1().toString();
            		content[i][2] = indexVo.getLastsSeason1().toString();
            		content[i][3] = indexVo.getSeason2().toString();
            		content[i][4] = indexVo.getLastsSeason2().toString();
            		content[i][5] = indexVo.getSeason2().toString();
            		content[i][6] = indexVo.getLastsSeason2().toString();
            		content[i][7] = indexVo.getSeason2().toString();
            		content[i][8] = indexVo.getLastsSeason2().toString();
            		content[i][9] = indexVo.getOldYearAll().toString();
            		content[i][10] = indexVo.getIndex().toString();
            		content[i][11] = indexVo.getYearAll().toString();
        		}
            }
       }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, titleList, content, null);
        
      //响应到客户端
        try {
        	this.setResponseHeader(response, fileName);
        	OutputStream os = response.getOutputStream();
        	wb.write(os);
        	os.flush();
        	os.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public static List<String> getTitleList(Map<String, Object> params,Calendar cal) {
		List<String> titleList = new ArrayList<String>();
		String[] numArray = {"一","二","三","四","五","六","七","八","九","十","十一","十二"};
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int lastYear = year - 1;
        Object type = params.get("type");
    	
    	String oldYearAll = lastYear+"年全年";
    	String index = year+"年下达";
    	String yearAll = year+"年全年";
    	
    	titleList.add("");
    	
        if(type == null || type.toString() == "season" || type.toString().equals("season")) {
        	if(month == 1 || month == 2 || month == 3){
        		for(int i = 0; i < 1; i++) {
    				titleList.add(year+"年" + numArray[i] + "季度");
            		titleList.add(lastYear + "年" + numArray[i] + "季度");
    			}
    		} else if(month == 4 || month == 5 || month == 6){
    			for(int i = 0; i < 2; i++) {
    				titleList.add(year+"年" + numArray[i] + "季度");
            		titleList.add(lastYear + "年" + numArray[i] + "季度");
    			}
    		} else if(month == 7 || month == 8 || month == 9){
    			for(int i = 0; i < 3; i++) {
    				titleList.add(year+"年" + numArray[i] + "季度");
            		titleList.add(lastYear + "年" + numArray[i] + "季度");
    			}
    		} else if(month == 10 || month == 11 || month == 12){
    			for(int i = 0; i < 4; i++) {
    				titleList.add(year+"年" + numArray[i] + "季度");
            		titleList.add(lastYear + "年" + numArray[i] + "季度");
    			}
    		} 
        } else {
        	for(int i =0; i < month; i++){
        		titleList.add(year +"年" + numArray[i] + "月");
        		titleList.add(lastYear + "年" + numArray[i] + "月");
        	}
        }
        titleList.add(oldYearAll);
        titleList.add(index);
        titleList.add(yearAll);
        return titleList;
	}
	
	public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.zjt.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjt.entity.BaseData;
import com.zjt.entity.BusinessData;
import com.zjt.entity.ErpData;
import com.zjt.entity.Quota;
import com.zjt.entity.Subject;
import com.zjt.mapper.BaseDataMapper;
import com.zjt.mapper.BusinessDataMapper;
import com.zjt.mapper.ErpDataMapper;
import com.zjt.mapper.QuotaMapper;
import com.zjt.mapper.SubjectMapper;
import com.zjt.model.IndexVo;
import com.zjt.service.IndexCalculationService;



@Service("IndexCalculationService")
public class IndexCalculationServiceImpl implements IndexCalculationService {
	
	@Autowired
	private SubjectMapper subjectMapper;
	
	@Autowired
	private QuotaMapper quotaMapper;
	
	@Autowired
	private BaseDataMapper baseDataMapper;
	
	@Autowired
	private ErpDataMapper erpDataMapper;
	
	@Autowired
	private BusinessDataMapper businessDataMapper;

	@Override
	public Map<String, Object> getIndexCalculation(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Object type = params.get("type");
		
		//当前系统时间
		Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int lastYear = year - 1;
		
        //所以科目
        List<Subject> subjectList = subjectMapper.selectAll();
        
		//季度
		List<IndexVo> season1 = new ArrayList<IndexVo>();
		List<IndexVo> season2 = new ArrayList<IndexVo>();
		List<IndexVo> season3 = new ArrayList<IndexVo>();
		List<IndexVo> season4 = new ArrayList<IndexVo>();
		List<IndexVo> lastsSeason1 = new ArrayList<IndexVo>();
		List<IndexVo> lastsSeason2 = new ArrayList<IndexVo>();
		List<IndexVo> lastsSeason3 = new ArrayList<IndexVo>();
		List<IndexVo> lastsSeason4 = new ArrayList<IndexVo>();
		//月份
		List<IndexVo> month1 = new ArrayList<IndexVo>();
		List<IndexVo> month2 = new ArrayList<IndexVo>();
		List<IndexVo> month3 = new ArrayList<IndexVo>();
		List<IndexVo> month4 = new ArrayList<IndexVo>();
		List<IndexVo> month5 = new ArrayList<IndexVo>();
		List<IndexVo> month6 = new ArrayList<IndexVo>();
		List<IndexVo> month7 = new ArrayList<IndexVo>();
		List<IndexVo> month8 = new ArrayList<IndexVo>();
		List<IndexVo> month9 = new ArrayList<IndexVo>();
		List<IndexVo> month10 = new ArrayList<IndexVo>();
		List<IndexVo> month11 = new ArrayList<IndexVo>();
		List<IndexVo> month12 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth1 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth2 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth3 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth4 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth5 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth6 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth7 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth8 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth9 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth10 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth11 = new ArrayList<IndexVo>();
		List<IndexVo> oldMonth12 = new ArrayList<IndexVo>();
		
		String season = "season";
		String noSeason = "month";
		
		//共通
		List<IndexVo> yearAll = selectIndex(year,getMonthList(year,12,season),subjectList,season);
		List<IndexVo> oldYearAll = selectIndex(lastYear,getMonthList(lastYear,12,season),subjectList,season);
		List<IndexVo> index = selectYearIndex(year, subjectList);
        
		
        if(type == null || type.toString() == "season" || type.toString().equals("season")) {
        	if(month == 1 || month == 2 || month == 3){
        		season1 = selectIndex(year,getMonthList(year,3,season),subjectList,season);
        		lastsSeason1 = selectIndex(lastYear,getMonthList(lastYear,3,season),subjectList,season);
    		} else if(month == 4 || month == 5 || month == 6){
    			season1 = selectIndex(year,getMonthList(year,3,season),subjectList,season);
    			season2 = selectIndex(year,getMonthList(year,6,season),subjectList,season);
    			lastsSeason1 = selectIndex(lastYear,getMonthList(lastYear,3,season),subjectList,season);
    			lastsSeason2 = selectIndex(lastYear,getMonthList(lastYear,6,season),subjectList,season);
    		} else if(month == 7 || month == 8 || month == 9){
    			season1 = selectIndex(year,getMonthList(year,3,season),subjectList,season);
    			season2 = selectIndex(year,getMonthList(year,6,season),subjectList,season);
    			season3 = selectIndex(year,getMonthList(year,9,season),subjectList,season);
    			lastsSeason1 = selectIndex(lastYear,getMonthList(lastYear,3,season),subjectList,season);
    			lastsSeason2 = selectIndex(lastYear,getMonthList(lastYear,6,season),subjectList,season);
    			lastsSeason3 = selectIndex(lastYear,getMonthList(lastYear,9,season),subjectList,season);
    		} else if(month == 10 || month == 11 || month == 12){
    			season1 = selectIndex(year,getMonthList(year,3,season),subjectList,season);
    			season2 = selectIndex(year,getMonthList(year,6,season),subjectList,season);
    			season3 = selectIndex(year,getMonthList(year,9,season),subjectList,season);
    			season4 = selectIndex(year,getMonthList(year,12,season),subjectList,season);
    			lastsSeason1 = selectIndex(lastYear,getMonthList(lastYear,3,season),subjectList,season);
    			lastsSeason2 = selectIndex(lastYear,getMonthList(lastYear,6,season),subjectList,season);
    			lastsSeason3 = selectIndex(lastYear,getMonthList(lastYear,9,season),subjectList,season);
    			lastsSeason4 = selectIndex(lastYear,getMonthList(lastYear,9,season),subjectList,season);
    		}
		} else {
			if(month == 1){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
			} else if(month == 2){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
			} else if(month == 3){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
			} else if(month == 4){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
			} else if(month == 5){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
			} else if(month == 6){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
			} else if(month == 7){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
			} else if(month == 8){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				month8 = selectIndex(year,getMonthList(year,8,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
				oldMonth8 = selectIndex(lastYear,getMonthList(lastYear,8,noSeason),subjectList,noSeason);
			} else if(month == 9){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				month8 = selectIndex(year,getMonthList(year,8,noSeason),subjectList,noSeason);
				month9 = selectIndex(year,getMonthList(year,9,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
				oldMonth8 = selectIndex(lastYear,getMonthList(lastYear,8,noSeason),subjectList,noSeason);
				oldMonth9 = selectIndex(lastYear,getMonthList(lastYear,9,noSeason),subjectList,noSeason);
			} else if(month == 10){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				month8 = selectIndex(year,getMonthList(year,8,noSeason),subjectList,noSeason);
				month9 = selectIndex(year,getMonthList(year,9,noSeason),subjectList,noSeason);
				month10 = selectIndex(year,getMonthList(year,10,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
				oldMonth8 = selectIndex(lastYear,getMonthList(lastYear,8,noSeason),subjectList,noSeason);
				oldMonth9 = selectIndex(lastYear,getMonthList(lastYear,9,noSeason),subjectList,noSeason);
				oldMonth10 = selectIndex(lastYear,getMonthList(lastYear,10,noSeason),subjectList,noSeason);
			} else if(month == 11){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				month8 = selectIndex(year,getMonthList(year,8,noSeason),subjectList,noSeason);
				month9 = selectIndex(year,getMonthList(year,9,noSeason),subjectList,noSeason);
				month10 = selectIndex(year,getMonthList(year,10,noSeason),subjectList,noSeason);
				month11 = selectIndex(year,getMonthList(year,11,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
				oldMonth8 = selectIndex(lastYear,getMonthList(lastYear,8,noSeason),subjectList,noSeason);
				oldMonth9 = selectIndex(lastYear,getMonthList(lastYear,9,noSeason),subjectList,noSeason);
				oldMonth10 = selectIndex(lastYear,getMonthList(lastYear,10,noSeason),subjectList,noSeason);
				oldMonth11 = selectIndex(lastYear,getMonthList(lastYear,11,noSeason),subjectList,noSeason);
			} else if(month == 12){
				month1 = selectIndex(year,getMonthList(year,1,noSeason),subjectList,noSeason);
				month2 = selectIndex(year,getMonthList(year,2,noSeason),subjectList,noSeason);
				month3 = selectIndex(year,getMonthList(year,3,noSeason),subjectList,noSeason);
				month4 = selectIndex(year,getMonthList(year,4,noSeason),subjectList,noSeason);
				month5 = selectIndex(year,getMonthList(year,5,noSeason),subjectList,noSeason);
				month6 = selectIndex(year,getMonthList(year,6,noSeason),subjectList,noSeason);
				month7 = selectIndex(year,getMonthList(year,7,noSeason),subjectList,noSeason);
				month8 = selectIndex(year,getMonthList(year,8,noSeason),subjectList,noSeason);
				month9 = selectIndex(year,getMonthList(year,9,noSeason),subjectList,noSeason);
				month10 = selectIndex(year,getMonthList(year,10,noSeason),subjectList,noSeason);
				month11 = selectIndex(year,getMonthList(year,11,noSeason),subjectList,noSeason);
				month12 = selectIndex(year,getMonthList(year,12,noSeason),subjectList,noSeason);
				oldMonth1 = selectIndex(lastYear,getMonthList(lastYear,1,noSeason),subjectList,noSeason);
				oldMonth2 = selectIndex(lastYear,getMonthList(lastYear,2,noSeason),subjectList,noSeason);
				oldMonth3 = selectIndex(lastYear,getMonthList(lastYear,3,noSeason),subjectList,noSeason);
				oldMonth4 = selectIndex(lastYear,getMonthList(lastYear,4,noSeason),subjectList,noSeason);
				oldMonth5 = selectIndex(lastYear,getMonthList(lastYear,5,noSeason),subjectList,noSeason);
				oldMonth6 = selectIndex(lastYear,getMonthList(lastYear,6,noSeason),subjectList,noSeason);
				oldMonth7 = selectIndex(lastYear,getMonthList(lastYear,7,noSeason),subjectList,noSeason);
				oldMonth8 = selectIndex(lastYear,getMonthList(lastYear,8,noSeason),subjectList,noSeason);
				oldMonth9 = selectIndex(lastYear,getMonthList(lastYear,9,noSeason),subjectList,noSeason);
				oldMonth10 = selectIndex(lastYear,getMonthList(lastYear,10,noSeason),subjectList,noSeason);
				oldMonth11 = selectIndex(lastYear,getMonthList(lastYear,11,noSeason),subjectList,noSeason);
				oldMonth12 = selectIndex(lastYear,getMonthList(lastYear,12,noSeason),subjectList,noSeason);
			}
		}
        
        List<IndexVo> indexList = integrationData(season1,season2,season3,season4,lastsSeason1,lastsSeason2,lastsSeason3,lastsSeason4,
        		month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12,
        		oldMonth1,oldMonth2,oldMonth3,oldMonth4,oldMonth5,oldMonth6,oldMonth7,oldMonth8,oldMonth9,oldMonth10,oldMonth11,oldMonth12,
        		yearAll,oldYearAll,index,subjectList);
        
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", indexList.size());
		returnMap.put("data",indexList);
		return returnMap;
	}
	
	public List<IndexVo> integrationData(List<IndexVo> season1,List<IndexVo> season2,List<IndexVo> season3,List<IndexVo> season4,
			List<IndexVo> lastsSeason1,List<IndexVo> lastsSeason2,List<IndexVo> lastsSeason3,List<IndexVo> lastsSeason4,
			List<IndexVo> month1,List<IndexVo> month2,List<IndexVo> month3,List<IndexVo> month4,
			List<IndexVo> month5,List<IndexVo> month6,List<IndexVo> month7,List<IndexVo> month8,
			List<IndexVo> month9,List<IndexVo> month10,List<IndexVo> month11,List<IndexVo> month12,
			List<IndexVo> oldMonth1,List<IndexVo> oldMonth2,List<IndexVo> oldMonth3,List<IndexVo> oldMonth4,
			List<IndexVo> oldMonth5,List<IndexVo> oldMonth6,List<IndexVo> oldMonth7,List<IndexVo> oldMonth8,
			List<IndexVo> oldMonth9,List<IndexVo> oldMonth10,List<IndexVo> oldMonth11,List<IndexVo> oldMonth12,
			List<IndexVo> yearAll,List<IndexVo> oldYearAll,List<IndexVo> index,List<Subject> subjectList){
		
		List<IndexVo> indexList = new ArrayList<IndexVo>();
		
		for(int i = 0; i < subjectList.size() ; i++) {
			IndexVo vo = new IndexVo();
			vo.setId(subjectList.get(i).getId());
			vo.setSubject(subjectList.get(i).getSubjectName());
			// 今年季度
			if(season1.size() > 0) {
				vo.setSeason1(season1.get(i).getIndex());
			}
			if(season2.size() > 0) {
				vo.setSeason2(season2.get(i).getIndex());
			}
			if(season3.size() > 0) {
				vo.setSeason3(season3.get(i).getIndex());
			}
			if(season4.size() > 0) {
				vo.setSeason4(season4.get(i).getIndex());
			}
			
			//上一年季度
			if(lastsSeason1.size() > 0) {
				vo.setLastsSeason1(lastsSeason1.get(i).getIndex());
			}
			if(lastsSeason2.size() > 0) {
				vo.setLastsSeason2(lastsSeason2.get(i).getIndex());
			}
			if(lastsSeason3.size() > 0) {
				vo.setLastsSeason3(lastsSeason3.get(i).getIndex());
			}
			if(lastsSeason4.size() > 0) {
				vo.setLastsSeason4(lastsSeason4.get(i).getIndex());
			}
			
			// 今年月
			if(month1.size() > 0) {
				vo.setMonth1(month1.get(i).getIndex());
			}
			if(month2.size() > 0) {
				vo.setMonth2(month2.get(i).getIndex());
			}
			if(month3.size() > 0) {
				vo.setMonth3(month3.get(i).getIndex());
			}
			if(month4.size() > 0) {
				vo.setMonth4(month4.get(i).getIndex());
			}
			if(month5.size() > 0) {
				vo.setMonth5(month5.get(i).getIndex());
			}
			if(month6.size() > 0) {
				vo.setMonth6(month6.get(i).getIndex());
			}
			if(month7.size() > 0) {
				vo.setMonth7(month7.get(i).getIndex());
			}
			if(month8.size() > 0) {
				vo.setMonth8(month8.get(i).getIndex());
			}
			if(month9.size() > 0) {
				vo.setMonth9(month9.get(i).getIndex());
			}
			if(month10.size() > 0) {
				vo.setMonth10(month10.get(i).getIndex());
			}
			if(month11.size() > 0) {
				vo.setMonth11(month11.get(i).getIndex());
			}
			if(month12.size() > 0) {
				vo.setMonth12(month12.get(i).getIndex());
			}
			
			//上一年月
			if(oldMonth1.size() > 0) {
				vo.setOldMonth1(oldMonth1.get(i).getIndex());
			}
			if(oldMonth2.size() > 0) {
				vo.setOldMonth2(oldMonth2.get(i).getIndex());
			}
			if(oldMonth3.size() > 0) {
				vo.setOldMonth3(oldMonth3.get(i).getIndex());
			}
			if(oldMonth4.size() > 0) {
				vo.setOldMonth4(oldMonth4.get(i).getIndex());
			}
			if(oldMonth5.size() > 0) {
				vo.setOldMonth5(oldMonth5.get(i).getIndex());
			}
			if(oldMonth6.size() > 0) {
				vo.setOldMonth6(oldMonth6.get(i).getIndex());
			}
			if(oldMonth7.size() > 0) {
				vo.setOldMonth7(oldMonth7.get(i).getIndex());
			}
			if(oldMonth8.size() > 0) {
				vo.setOldMonth8(oldMonth8.get(i).getIndex());
			}
			if(oldMonth9.size() > 0) {
				vo.setOldMonth9(oldMonth9.get(i).getIndex());
			}
			if(oldMonth10.size() > 0) {
				vo.setOldMonth10(oldMonth10.get(i).getIndex());
			}
			if(oldMonth11.size() > 0) {
				vo.setOldMonth11(oldMonth11.get(i).getIndex());
			}
			if(oldMonth12.size() > 0) {
				vo.setOldMonth12(oldMonth12.get(i).getIndex());
			}
			
			//共通
			if(yearAll.size() > 0) {
				vo.setYearAll(yearAll.get(i).getIndex());
			}
			if(oldYearAll.size() > 0) {
				vo.setOldYearAll(oldYearAll.get(i).getIndex());
			}
			if(index.size() > 0) {
				vo.setIndex(index.get(i).getIndex());
			}
			indexList.add(vo);
		}
		return indexList;
	}
	
	
	public List<IndexVo> selectIndex(int year,List<String> months,List<Subject> subjectList,String type){
		
		List<IndexVo> indexList = new ArrayList<IndexVo>();
		//不可控成本
		BigDecimal noCost = new BigDecimal(0);
		//可控成本
		BigDecimal yesCost = new BigDecimal(0);
		
		for(Subject subject : subjectList) {
			IndexVo indexVo = new IndexVo();
			if(subject.getId() == 1 || subject.getId() == 2 || subject.getId() == 3 || subject.getId() == 11
					|| subject.getId() == 63 || subject.getId() == 71 || subject.getId() == 73
					|| subject.getId() == 74 || subject.getId() == 75 || subject.getId() == 76) {
				//需要计算的科目
				indexVo.setId(subject.getId());
				indexVo.setIndex(BigDecimal.ZERO);
			} else if(subject.getId() == 60 ) {
				//税金
				BaseData baseData = selectBaseData(months);
				if(baseData != null) {
					indexVo.setId(subject.getId());
					indexVo.setIndex(baseData.getAmount());
				} else {
					indexVo.setId(subject.getId());
					indexVo.setIndex(BigDecimal.ZERO);
				}
			} else if(subject.getId() == 72) {
				//人数
				BaseData baseData = selectBaseData(months);
				if(baseData != null) {
					indexVo.setId(subject.getId());
					indexVo.setIndex(new BigDecimal(baseData.getNumPeople()));
				} else {
					indexVo.setId(subject.getId());
					indexVo.setIndex(BigDecimal.ZERO);
				}
			} else if(subject.getId() == 64) {
				//核心业务收入
				indexVo.setId(subject.getId());
				indexVo.setIndex(selectBusinesData(months));
			} else {
				//ERP获取数据
				BigDecimal value = selectErpData(months,String.valueOf(subject.getId()));
				if(subject.getId() >= 4 && subject.getId() <= 10) {
					noCost = noCost.add(value);
				} else if(subject.getId() >= 12 && subject.getId() <= 58) {
					yesCost = yesCost.add(value);
				}
				indexVo.setId(subject.getId());
				indexVo.setIndex(value);
			}
			indexList.add(indexVo);
		}
		//财务费用
		BigDecimal finance = indexList.get(60).getIndex();
		finance = finance.divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
		indexList.get(60).setIndex(finance);
		
		BigDecimal costTotal = noCost.add(yesCost);
		//加上其他业务成本
		costTotal = costTotal.add(indexList.get(58).getIndex());
		//加上税金
		costTotal = costTotal.add(indexList.get(59).getIndex());
		//加上财务费用
		costTotal = costTotal.add(indexList.get(60).getIndex());
		
		//营业总成本
		indexList.get(0).setIndex(costTotal);
		//生产成本
		indexList.get(1).setIndex(noCost.add(yesCost));
		//不可控成本
		indexList.get(2).setIndex(noCost);
		//可控成本
		indexList.get(10).setIndex(yesCost);
		//营业总成本（扣除特殊因素后）
		indexList.get(61).setIndex(costTotal);
		
		//收入计算
		BigDecimal income = indexList.get(63).getIndex();//核心业务收入
		//加上科技项目专项补偿
		income = income.add(indexList.get(64).getIndex());
		//加上其他业务收入
		income = income.add(indexList.get(65).getIndex());
		//加上主营业务收入
		income = income.add(indexList.get(66).getIndex());
		//加上其他收益
		income = income.add(indexList.get(67).getIndex());
		//加上营业外收入
		income = income.add(indexList.get(68).getIndex());
		indexList.get(62).setIndex(income);
		
		//报表利润计算
		//BigDecimal incomeCore = indexList.get(63).getIndex();//核心业务收入
		BigDecimal incomeCore = indexList.get(65).getIndex();//其他业务收入
		//加上主营业务收入
		incomeCore = incomeCore.add(indexList.get(66).getIndex());
		//加上其他收益
		incomeCore = incomeCore.add(indexList.get(67).getIndex());
		//加上营业外收入
		incomeCore = incomeCore.add(indexList.get(68).getIndex());
		//减去营业总成本
		incomeCore = incomeCore.subtract(indexList.get(0).getIndex());
		//减去营业外成本
		incomeCore = incomeCore.subtract(indexList.get(69).getIndex());
		indexList.get(70).setIndex(incomeCore);
		
		//模拟利润计算
		BigDecimal incomeCore1 = indexList.get(63).getIndex();//核心业务收入
		//加上报表利润
		incomeCore1 = incomeCore1.add(indexList.get(70).getIndex());
		//加上固定资产折旧
		incomeCore1 = incomeCore1.add(indexList.get(4).getIndex());
		//加上无形资产摊销
		incomeCore1 = incomeCore1.add(indexList.get(8).getIndex());
		//加上科技项目专项补偿
		incomeCore1 = incomeCore1.add(indexList.get(64).getIndex());
		indexList.get(72).setIndex(incomeCore1);
		
		//人均模拟利润计算
		if(indexList.get(71).getIndex() == BigDecimal.ZERO) {
			indexList.get(73).setIndex(BigDecimal.ZERO);
		} else {
			BigDecimal income1 = indexList.get(72).getIndex();
			income1 = income1.divide(indexList.get(71).getIndex(),2,BigDecimal.ROUND_HALF_UP);
			indexList.get(73).setIndex(income1);
		}
		
		//人均成本费用
		if(indexList.get(71).getIndex() == BigDecimal.ZERO) {
			indexList.get(74).setIndex(BigDecimal.ZERO);
		} else {
			BigDecimal cost = indexList.get(0).getIndex();
			cost = cost.subtract(indexList.get(4).getIndex());
			cost = cost.subtract(indexList.get(8).getIndex());
			cost = cost.divide(indexList.get(71).getIndex(),2,BigDecimal.ROUND_HALF_UP);
			indexList.get(74).setIndex(cost);
		}
		//投入产出比计算
		List<IndexVo> selectInputOutputRate = selectInputOutputRate(year, months, subjectList, type);
		/**
		 * 投入产出比 计算公式
		 * 投入产出比 = （当年模拟利润-去年模拟利润）/（（当年营业总成本-当年固定资产折旧-当年无形资产摊销）-（去年营业总成本-去年固定资产折旧-去年无形资产摊销））
		 */
		BigDecimal index75 = indexList.get(72).getIndex(); 
		index75 = index75.subtract(selectInputOutputRate.get(72).getIndex());
		
		BigDecimal index1 = indexList.get(1).getIndex(); 
		index1 = index1.subtract(indexList.get(4).getIndex());
		index1 = index1.subtract(indexList.get(8).getIndex());
		index1 = index1.subtract(selectInputOutputRate.get(1).getIndex());
		index1 = index1.add(selectInputOutputRate.get(4).getIndex());
		index1 = index1.add(selectInputOutputRate.get(8).getIndex());
		
		if(index1 == BigDecimal.ZERO) {
			indexList.get(75).setIndex(BigDecimal.ZERO);
		} else {
			index75 = index75.divide(index1,2,BigDecimal.ROUND_HALF_UP);
			indexList.get(75).setIndex(index75);
		}
		
		return indexList;
	}
	
	
	/**
	 * 投入产出比计算
	 * @param year
	 * @param months
	 * @param subjectList
	 * @return
	 */
	public List<IndexVo> selectInputOutputRate(int year,List<String> months,List<Subject> subjectList,String type){
		List<String> lastMonths = new ArrayList<String>();
		for(int i = 0; i < months.size(); i++) {
			String obj[] = months.get(i).split("-");
			Integer ye = Integer.valueOf(obj[0]) - 1;
			lastMonths.add(String.valueOf(ye) + "-" + obj[1]);
		}
		List<IndexVo> indexList = new ArrayList<IndexVo>();
		//不可控成本
		BigDecimal noCost = new BigDecimal(0);
		//可控成本
		BigDecimal yesCost = new BigDecimal(0);
		
		for(Subject subject : subjectList) {
			IndexVo indexVo = new IndexVo();
			if(subject.getId() == 1 || subject.getId() == 2 || subject.getId() == 3 || subject.getId() == 11
					|| subject.getId() == 63 || subject.getId() == 71 || subject.getId() == 73
					|| subject.getId() == 74 || subject.getId() == 75 || subject.getId() == 76) {
				//需要计算的科目
				indexVo.setId(subject.getId());
				indexVo.setIndex(BigDecimal.ZERO);
			} else if(subject.getId() == 60 ) {
				//税金
				BaseData baseData = selectBaseData(lastMonths);
				if(baseData != null) {
					indexVo.setId(subject.getId());
					indexVo.setIndex(baseData.getAmount());
				} else {
					indexVo.setId(subject.getId());
					indexVo.setIndex(BigDecimal.ZERO);
				}
			} else if(subject.getId() == 72) {
				//人数
				BaseData baseData = selectBaseData(lastMonths);
				if(baseData != null) {
					indexVo.setId(subject.getId());
					indexVo.setIndex(new BigDecimal(baseData.getNumPeople()));
				} else {
					indexVo.setId(subject.getId());
					indexVo.setIndex(BigDecimal.ZERO);
				}
			} else if(subject.getId() == 64) {
				//核心业务收入
				indexVo.setId(subject.getId());
				indexVo.setIndex(selectBusinesData(lastMonths));
			} else {
				//ERP获取数据
				BigDecimal value = selectErpData(lastMonths,String.valueOf(subject.getId()));
				if(subject.getId() >= 4 && subject.getId() <= 10) {
					noCost = noCost.add(value);
				} else if(subject.getId() >= 12 && subject.getId() <= 58) {
					yesCost = yesCost.add(value);
				}
				indexVo.setId(subject.getId());
				indexVo.setIndex(value);
			}
			indexList.add(indexVo);
		}
		//财务费用
		BigDecimal finance = indexList.get(60).getIndex();
		finance = finance.divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
		indexList.get(60).setIndex(finance);
		
		BigDecimal costTotal = noCost.add(yesCost);
		//加上其他业务成本
		costTotal = costTotal.add(indexList.get(58).getIndex());
		//加上税金
		costTotal = costTotal.add(indexList.get(59).getIndex());
		//加上财务费用
		costTotal = costTotal.add(indexList.get(60).getIndex());
		
		//营业总成本
		indexList.get(0).setIndex(costTotal);
		//生产成本
		indexList.get(1).setIndex(noCost.add(yesCost));
		//不可控成本
		indexList.get(2).setIndex(noCost);
		//可控成本
		indexList.get(10).setIndex(yesCost);
		//营业总成本（扣除特殊因素后）
		indexList.get(61).setIndex(costTotal);
		
		//收入计算
		BigDecimal income = indexList.get(63).getIndex();//核心业务收入
		//加上科技项目专项补偿
		income = income.add(indexList.get(64).getIndex());
		//加上其他业务收入
		income = income.add(indexList.get(65).getIndex());
		//加上主营业务收入
		income = income.add(indexList.get(66).getIndex());
		//加上其他收益
		income = income.add(indexList.get(67).getIndex());
		//加上营业外收入
		income = income.add(indexList.get(68).getIndex());
		indexList.get(62).setIndex(income);
		
		//报表利润计算
		//BigDecimal incomeCore = indexList.get(63).getIndex();//核心业务收入
		BigDecimal incomeCore = indexList.get(65).getIndex();//其他业务收入
		//加上主营业务收入
		incomeCore = incomeCore.add(indexList.get(66).getIndex());
		//加上其他收益
		incomeCore = incomeCore.add(indexList.get(67).getIndex());
		//加上营业外收入
		incomeCore = incomeCore.add(indexList.get(68).getIndex());
		//减去营业总成本
		incomeCore = incomeCore.subtract(indexList.get(0).getIndex());
		//减去营业外成本
		incomeCore = incomeCore.subtract(indexList.get(69).getIndex());
		indexList.get(70).setIndex(incomeCore);
		
		//模拟利润计算
		BigDecimal incomeCore1 = indexList.get(63).getIndex();//核心业务收入
		//加上报表利润
		incomeCore1 = incomeCore1.add(indexList.get(70).getIndex());
		//加上固定资产折旧
		incomeCore1 = incomeCore1.add(indexList.get(4).getIndex());
		//加上无形资产摊销
		incomeCore1 = incomeCore1.add(indexList.get(8).getIndex());
		//加上科技项目专项补偿
		incomeCore1 = incomeCore1.add(indexList.get(64).getIndex());
		indexList.get(72).setIndex(incomeCore1);
		
		//人均模拟利润计算
		if(indexList.get(71).getIndex() == BigDecimal.ZERO) {
			indexList.get(73).setIndex(BigDecimal.ZERO);
		} else {
			BigDecimal income1 = indexList.get(72).getIndex();
			income1 = income1.divide(indexList.get(71).getIndex(),2,BigDecimal.ROUND_HALF_UP);
			indexList.get(73).setIndex(income1);
		}
		
		//人均成本费用
		if(indexList.get(71).getIndex() == BigDecimal.ZERO) {
			indexList.get(74).setIndex(BigDecimal.ZERO);
		} else {
			BigDecimal cost = indexList.get(0).getIndex();
			cost = cost.subtract(indexList.get(4).getIndex());
			cost = cost.subtract(indexList.get(8).getIndex());
			cost = cost.divide(indexList.get(71).getIndex(),2,BigDecimal.ROUND_HALF_UP);
			indexList.get(74).setIndex(cost);
		}
		return indexList;
	}
	
	
	
	
	
	
	
	//查询科目当前年下达数据
	public List<IndexVo> selectYearIndex(int year,  List<Subject> subjectList) {
		List<IndexVo> indexList = new ArrayList<IndexVo>();
		for(Subject subejct : subjectList) {
			IndexVo vo = new IndexVo();
			Quota quota = new Quota();
			quota.setSubject(String.valueOf(subejct.getId()));
			quota.setYear(String.valueOf(year));
			Quota q = quotaMapper.selectIndexByYear(quota);
			
			BigDecimal index = (q != null)?q.getSubjectValue() : BigDecimal.ZERO;
			vo.setId(subejct.getId());
			vo.setIndex(index);
			indexList.add(vo);
		}
		return indexList;
	}
	
	//根据月份查询税金和人数
	public BaseData selectBaseData(List<String> months) {
		/*Example example = new Example(BaseData.class);
        Example.Criteria criteria = example.or();
        criteria.andEqualTo("month",month);
		baseDataMapper.selectByExample(example);*/
		Map<String, Object> params = new HashMap<>();
		params.put("months", months);
		BaseData baseData = baseDataMapper.selectBaseDataByMonth(params);
		return baseData;
	}
	
	//查询ERP基础数据
	public BigDecimal selectErpData(List<String> months,String subjectId) {
		Map<String, Object> params = new HashMap<>();
		params.put("subjectId", subjectId);
		params.put("months", months);
		ErpData erpData = erpDataMapper.selectErpDataMonth(params);
		if(erpData != null) {
			return erpData.getAmount();
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	//各部门核心业务收入汇总
	public BigDecimal selectBusinesData(List<String> months) {
		BusinessData busiData = businessDataMapper.selectBusinesData(months);
		if(busiData != null) {
			return busiData.getTotalPrice();
		} else {
			return BigDecimal.ZERO;
		}
	}
	
	/**
	 * 查询出日期list
	 * @param year
	 * @param month
	 * @return
	 */
	public List<String> getMonthList(int year, int month,String type){
		List<String> months = new ArrayList<>();
		if(type == null || type.toString() == "season" || type.toString().equals("season")) {
			for(int i = 1; i <= month; i++) {
				if(i > 9) {
					months.add(String.valueOf(year)+ "-" + String.valueOf(i));
				} else {
					months.add(String.valueOf(year)+ "-0" + String.valueOf(i));
				}
			} 
		} else {
			if(month > 9) {
				months.add(String.valueOf(year)+ "-" + String.valueOf(month));
			} else {
				months.add(String.valueOf(year)+ "-0" + String.valueOf(month));
			}
		}
		
		return months;
	}
}

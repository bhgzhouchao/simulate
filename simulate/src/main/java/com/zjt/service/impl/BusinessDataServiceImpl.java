package com.zjt.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zjt.entity.Business;
import com.zjt.entity.BusinessData;
import com.zjt.entity.BusinessDataDetail;
import com.zjt.entity.Tuser;
import com.zjt.mapper.BusinessDataDetailMapper;
import com.zjt.mapper.BusinessDataMapper;
import com.zjt.mapper.BusinessMapper;
import com.zjt.model.BusinessDataVo;
import com.zjt.model.CommVo;
import com.zjt.model.RelustBusiDataVo;
import com.zjt.service.BusinessDataService;

@Service("BusinessDataService")
public class BusinessDataServiceImpl extends BaseService<BusinessData> implements  BusinessDataService {
	
	@Autowired
	private BusinessDataMapper businessDataMapper;
	
	@Autowired
	private BusinessDataDetailMapper businessDataDetailMapper;
	
	@Autowired
	private BusinessMapper businessMapper;
	
	@Override
	public Map<String, Object> getBusinessDataListSerch(Map<String, Object> params) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int pageNo = Integer.valueOf(params.get("page").toString());
		int pageSize = Integer.valueOf(params.get("limit").toString());
		Subject subject = SecurityUtils.getSubject();
		Tuser loginUser =(Tuser) subject.getSession().getAttribute("user");
		params.put("pageNo", (pageNo-1) * pageSize);
		params.put("pageSize", pageSize);
		params.put("month", params.get("month"));
		params.put("dept", loginUser.getDept());
		String type = params.get("type").toString();
		List<BusinessData> list = null;
		if(type == "businessData" || type.equals("businessData") ) {
			list = businessDataMapper.getBusinessDataListSerch(params);
		} else if(type == "deptExamine" || type.equals("deptExamine")) {
			list = businessDataMapper.getDeptExamineListSerch(params);
		} else if(type == "leaderExamine" || type.equals("leaderExamine")) {
			list = businessDataMapper.getLeaderExamineListSerch(params);
		}
		List<RelustBusiDataVo> listVo = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(BusinessData data : list) {
			RelustBusiDataVo vo = new RelustBusiDataVo();
			vo.setId(data.getId());
			vo.setMonth(data.getMonth());
			vo.setDept(data.getDept());
			vo.setTotalPrice(data.getTotalPrice().toString());
			vo.setLeaderExamine(data.getLeaderExamine());
			if(data.getLeaderExamineDate() != null ) {
				vo.setLeaderExamineDate(df.format(data.getLeaderExamineDate()));
			}
			vo.setDeptExamine(data.getDeptExamine());
			if(data.getDeptExamineDate() != null ) {
				vo.setDeptExamineDate(df.format(data.getDeptExamineDate()));
			}
			vo.setCreateTime(df.format(data.getCreateTime()));
			vo.setCreateUser(data.getCreateUser());
			if(data.getSubmitDate() != null ) {
				vo.setSubmitDate(df.format(data.getSubmitDate()));
			}
			listVo.add(vo);
		}
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", businessDataMapper.selectAll().size());
		returnMap.put("data",listVo);
		return returnMap;
	}


	@Override
	public Map<String, Object> getBusinessTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteBusinessDatas(List<String> Ids) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			boolean ifExamine = false;
			for(String id : Ids) {
				//删除业务数据
				BusinessData data = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
				if(data.getDeptExamine() != "1" || !"1".equals(data.getDeptExamine())) {
					ifExamine = true;
					break;
				}
			} 
			if(!ifExamine) {
				for(String id : Ids) {
					//删除业务数据
					businessDataMapper.deleteByPrimaryKey(Integer.valueOf(id));
					//删除明细业务数据
					businessDataDetailMapper.deleteByBusinessDataId(id);
				}
				returnMap.put("state", "success");
				returnMap.put("mesg", "删除成功!");
			} else {
				returnMap.put("state", "fail");
				returnMap.put("mesg", "选中的数据中有已提交审批的数据，不能删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "删除失败，请稍后再试!");
			return returnMap; 
		}
		
		return returnMap;
	}

	@Override
	public Map<String, Object> selectBusinessById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView toBusiness() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> toBusinessDataAdd(String type, String id) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if(type == "1" || "1".equals(type)) {
			Subject subject = SecurityUtils.getSubject();
			Tuser loginUser =(Tuser) subject.getSession().getAttribute("user");
			List<Business> businessList = businessMapper.selectBusinessList(loginUser.getDept());
			returnMap.put("businessList", businessList);
			returnMap.put("state", "success");
		} else {
			BusinessData businessData = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
			List<BusinessDataDetail> businessList = businessDataDetailMapper.selectListByBusinessDataId(id);
			returnMap.put("businessData", businessData);
			returnMap.put("businessList", businessList);
			returnMap.put("state", "success");
		}
		
		return returnMap;
	}




	@Override
	public Map<String, Object> addOrUpdateBusinessData(BusinessDataVo businessDataVo) {
		Map<String,Object> retuenMap =new HashMap<String, Object>();
		try {
			//计算总金额
			BigDecimal totalPrice = new BigDecimal("0");
			for(CommVo vo :businessDataVo.getBusinessList()) {
				BigDecimal tPrice = null;
				Business business = businessMapper.selectByPrimaryKey(Integer.valueOf(vo.getId()));
				BigDecimal price = business.getPrice();
				BigDecimal num = new BigDecimal(vo.getNum());
				tPrice = price.multiply(num);
				totalPrice = totalPrice.add(tPrice);
			}
			//查询当前登录用户
			Subject subject = SecurityUtils.getSubject();
			Tuser loginUser =(Tuser) subject.getSession().getAttribute("user");
			
			if(businessDataVo.getId() == null || businessDataVo.getId().toString() == "") {
				
				//新增业务数据
				Integer busiDataId = addBusinessData(businessDataVo,totalPrice,loginUser);
				//新增业务详细数据
				addBusinessDataDetail(businessDataVo,busiDataId,loginUser);
			} else {
				//更新业务数据
				BusinessData businessData = businessDataMapper.selectByPrimaryKey(Integer.valueOf(businessDataVo.getId()));
				businessData.setMonth(businessDataVo.getMonth());
				businessData.setRemarks(businessDataVo.getRemarks());
				businessData.setTotalPrice(totalPrice);
				businessData.setUpdateTime(new Date());
				businessData.setUpdateUser(loginUser.getUserName());
				if(businessDataVo.getType() == "2" || businessDataVo.getType().equals("2")) {
					businessData.setSubmitDate(new Date());
					businessData.setDeptExamine("2");
					businessData.setLeaderExamine("1");
				}
				updateNotNull(businessData);
				
				//删除明细业务数据
				businessDataDetailMapper.deleteByBusinessDataId(businessDataVo.getId());
				
				//新增业务详细数据
				addBusinessDataDetail(businessDataVo,Integer.valueOf(businessDataVo.getId()),loginUser);
			}
			retuenMap.put("state", "success");
			retuenMap.put("mesg", "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			retuenMap.put("state", "fail");
			retuenMap.put("mesg", "保存失败，请稍后再试！");
		}
		
		return retuenMap;
	}
	
	//增加业务数据
	public Integer addBusinessData(BusinessDataVo businessDataVo,BigDecimal totalPrice,Tuser loginUser ) {
		BusinessData businessData = new BusinessData();
		businessData.setMonth(businessDataVo.getMonth());
		businessData.setRemarks(businessDataVo.getRemarks());
		businessData.setCreateTime(new Date());
		businessData.setCreateUser(loginUser.getUserName());
		businessData.setDept(loginUser.getDept());
		/**
		 * 1:未提交
		 * 2:待审批
		 * 3:审批通过
		 * 4:审批未通过
		 */
		businessData.setDeptExamine("1");
		businessData.setLeaderExamine("1");
		businessData.setTotalPrice(totalPrice);
		/**
		 * 1：保存
		 * 2：提交
		 */
		if(businessDataVo.getType() == "2" || businessDataVo.getType().equals("2")) {
			businessData.setSubmitDate(new Date());
			businessData.setDeptExamine("2");
			businessData.setLeaderExamine("1");
		}
		businessDataMapper.insertBusinessData(businessData);
		return businessData.getId();
	}
	
	public boolean addBusinessDataDetail(BusinessDataVo businessDataVo,Integer busiDataId,Tuser loginUser ) throws Exception {
		for(CommVo vo :businessDataVo.getBusinessList()) {
			Business business = businessMapper.selectByPrimaryKey(Integer.valueOf(vo.getId()));
			BusinessDataDetail bdd = new BusinessDataDetail();
			bdd.setBusinessDataId(busiDataId);
			bdd.setBusinessId(vo.getId());
			bdd.setBusinessName(business.getBusinessName());
			bdd.setBusinessType(business.getBusinessType());
			bdd.setPrice(business.getPrice());
			bdd.setNum(Integer.valueOf(vo.getNum()));
			BigDecimal num = new BigDecimal(vo.getNum());
			BigDecimal price = business.getPrice();
			BigDecimal totalPrice = price.multiply(num);
			bdd.setTotalPrice(totalPrice);
			bdd.setCreateTime(new Date());
			bdd.setCreateUser(loginUser.getUserName());
			businessDataDetailMapper.insert(bdd);
		}
		return true;
	}


	@Override
	public Map<String, Object> examineBusinessData(List<String> ids, boolean flg, String type) {
		Map<String,Object> returnMap =  new HashMap<String, Object>();
		try {
			//判断是否已经审批过
			boolean ifExamine = false;
			for(String id : ids) {
				if(type == "deptExamine" || type.equals("deptExamine")) {
					BusinessData data = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
					if(data.getDeptExamine() == "3" || "3".equals(data.getDeptExamine()) || data.getDeptExamine() == "4" || "4".equals(data.getDeptExamine())) {
						ifExamine = true;
						break;
					}
				} else {
					BusinessData data = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
					if(data.getLeaderExamine() == "3" || "3".equals(data.getLeaderExamine()) || data.getLeaderExamine() == "4" || "4".equals(data.getLeaderExamine())) {
						ifExamine = true;
						break;
					}
				}
			} 
			if(!ifExamine) {
				for(String id : ids) {
					//审批通过
					if(flg) {
						BusinessData businessData = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
						if(type == "deptExamine" || type.equals("deptExamine")) {
							businessData.setDeptExamine("3");
							businessData.setLeaderExamine("2");
							businessData.setDeptExamineDate(new Date());
						} else {
							businessData.setLeaderExamine("3");
							businessData.setLeaderExamineDate(new Date());
						}
						updateNotNull(businessData);
					} else {
						//审批不通过
						BusinessData businessData = businessDataMapper.selectByPrimaryKey(Integer.valueOf(id));
						if(type == "deptExamine" || type.equals("deptExamine")) {
							businessData.setDeptExamine("4");
							businessData.setDeptExamineDate(new Date());
						} else {
							businessData.setDeptExamine("4");
							businessData.setLeaderExamine("4");
							businessData.setLeaderExamineDate(new Date());
						}
						updateNotNull(businessData);
					}
				}
				returnMap.put("state", "success");
				returnMap.put("mesg", "审批成功!");
			} else {
				returnMap.put("state", "fail");
				returnMap.put("mesg", "数据已审批，不能重新审批!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("state", "fail");
			returnMap.put("mesg", "审批成功，请稍后再试!");
			return returnMap; 
		}
		
		return returnMap;
	}


}

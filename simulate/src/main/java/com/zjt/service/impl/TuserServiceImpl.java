package com.zjt.service.impl;

import com.zjt.entity.Dept;
import com.zjt.entity.Tuser;
import com.zjt.mapper.DeptMapper;
import com.zjt.mapper.TuserMapper;
import com.zjt.service.TuserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0, 2017/11/10
 * @description
 */
@Service("tuserService")
public class TuserServiceImpl   extends BaseService<Tuser> implements TuserService {
	@Autowired
	private DeptMapper deptMapper;
	
	
	@Override
	public List<Dept> selectDeptList() {
		List<Dept> list = deptMapper.getDeptList();
		return list;
	}
}

package com.yoohoo.en.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.mapper.TStudentBalanceLogMapper;
import com.yoohoo.en.dao.model.TStudentBalanceLog;
import com.yoohoo.en.dao.model.TStudentBalanceLogExample;
import com.yoohoo.en.service.IStudentBlanceLogService;

@Service
public class StudentBlanceLogServiceImpl implements IStudentBlanceLogService {

	@Autowired
	private TStudentBalanceLogMapper  studentBalanceLogMapper;
	
	@Override
	public List<TStudentBalanceLog> queryStudentBlanceLog(Integer studentId, Integer start, Integer limit) {
		TStudentBalanceLogExample example=new TStudentBalanceLogExample();
		example.setPageHelper(new PageHelper<>(start,limit));
		example.createCriteria().andUserIdEqualTo(studentId);
		example.setOrderByClause("addtime  desc");
		return studentBalanceLogMapper.selectByExample(example);
	}

	@Override
	public int countStudentBlanceLog(Integer studentId) {
		TStudentBalanceLogExample example=new TStudentBalanceLogExample();
		example.createCriteria().andUserIdEqualTo(studentId);
		return studentBalanceLogMapper.countByExample(example);
	}

}

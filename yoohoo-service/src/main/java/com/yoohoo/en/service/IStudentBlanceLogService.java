package com.yoohoo.en.service;

import java.util.List;

import com.yoohoo.en.dao.model.TStudentBalanceLog;

public interface IStudentBlanceLogService {

	List<TStudentBalanceLog> queryStudentBlanceLog(Integer studentId, Integer start, Integer limit);

	int countStudentBlanceLog(Integer studentId);
}

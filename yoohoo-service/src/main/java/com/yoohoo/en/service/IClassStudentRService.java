package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TClassStudentR;

import java.util.List;
import java.util.Map;

/**
 * 班级学生关系表
 * 
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
public interface IClassStudentRService {
	
	TClassStudentR queryObject(Long classId, Long studentId);
	
	List<TClassStudentR> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TClassStudentR tClassStudentR);
	
	void update(TClassStudentR tClassStudentR);
	
	void delete(Integer classId);
	
	void deleteBatch(List<Integer> classIds);
}

package com.yoohoo.en.teacher.service;

import java.util.List;
import java.util.Map;

import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherPositionRelation;
import com.yoohoo.en.teacher.entity.TTeacherInfoEntity;

/**
 * 
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:24:37
 */
public interface TTeacherInfoService {
	
	TTeacherInfoEntity queryObject(Integer teacherId);
	
	List<TTeacherInfoEntity> queryList(Map<String, Object> map);
	
	List<Long> queryIdList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TTeacherInfoEntity tTeacherInfo);
	
	void update(TTeacherInfoEntity tTeacherInfo);
	
	void delete(Integer teacherId);
	
	void deleteBatch(Integer[] teacherIds);
	
	boolean accountIsExists(String account);

    void insertBatchPositonType(List<TTeacherPositionRelation> list);

	List<TTeacherPositionRelation> selectByTeacherId(Integer teacherId, Long addUserId);

    List<TTeacherInfo> queryByPositionType(Integer positionType);

	TTeacherInfo queryById(Integer teacherId);

	List<TTeacherInfo> getTeacherByName(String likeName);
}

package com.yoohoo.en.student.service;

import java.util.List;
import java.util.Map;

import com.yoohoo.en.bean.request.RechargeRequest;
import com.yoohoo.en.student.entity.TStudentInfoEntity;

/**
 * 学生信息表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:25:01
 */
public interface TStudentInfoService {
	
	TStudentInfoEntity queryObject(Integer userId);
	
	List<TStudentInfoEntity> queryList(Map<String, Object> map);

	List<TStudentInfoEntity> queryListReg(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TStudentInfoEntity tStudentInfo);
	
	void update(TStudentInfoEntity tStudentInfo);
	
	void updateBind(TStudentInfoEntity tStudentInfo);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
	
	String checkStudentInfo(TStudentInfoEntity studentInfo);
	
	boolean userIsExistsAndNomal(Long userId);
	
	boolean doRecharge(RechargeRequest rechargeReq);

	boolean addLearnPath(Long studentId, Integer lessonId, Integer chapterId);

    boolean removeLearnPath(Long studentId, Integer lessonId, Integer chapterId);

	List<Long> queryIdList(Map<String, Object> map);
	
    boolean checkStudentUnConfirm(Long studentId, Long subAdminUserId);
}

package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TClass;
import com.yoohoo.en.dao.model.TClassStudentR;
import com.yoohoo.en.dao.model.ext.TClassExt;

import java.util.List;
import java.util.Map;

/**
 * 班级表
 * 
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
public interface IClassService {
	
	TClass queryObject(Long classId);
	
	List<TClass> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Long save(TClass tClass);
	
	void update(TClass tClass);
	
	void delete(Long classId);
	
	void deleteBatch(List<Integer> classIds);

	List<TClassExt> queryListExt(Map<String, Object> map);

	TClassExt queryObjectExt(Long classId);

	void saveBatchStudent(List<TClassStudentR> list);
	
	String checkClassInfo(TClass c);

	public void deleteBatchStudent(Long classId, List<Long> notInList, List<Long> inList);


	String findAssiantNameByClassId(Long classId);


	void updateShcedule(TClassExt tClass);

    List<TClassExt> queryList(TClass tClass);

    Integer queryTotal(TClass tClass);
}

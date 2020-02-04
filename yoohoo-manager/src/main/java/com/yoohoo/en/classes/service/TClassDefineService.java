package com.yoohoo.en.classes.service;

import java.util.List;
import java.util.Map;

import com.yoohoo.en.dao.model.TClassDefine;

/**
 * 班级定义表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 19:29:30
 */
public interface TClassDefineService {
	
	TClassDefine queryObject(Integer classItemId);
	
	List<TClassDefine> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TClassDefine tClassDefine);
	
	void update(TClassDefine tClassDefine);
	
	void delete(Integer classItemId);
	
	void deleteBatch(Integer[] classItemIds);
	
	String checkClassDefine(TClassDefine c);

	List<TClassDefine> queryList();
}

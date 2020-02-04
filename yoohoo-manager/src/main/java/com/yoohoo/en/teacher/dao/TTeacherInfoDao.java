package com.yoohoo.en.teacher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yoohoo.en.mcore.dao.BaseDao;
import com.yoohoo.en.teacher.entity.TTeacherInfoEntity;

/**
 * 
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:24:37
 */
@Repository
public interface TTeacherInfoDao extends BaseDao<TTeacherInfoEntity> {

	List<Long> queryIdList(Map<String, Object> map);

	
}

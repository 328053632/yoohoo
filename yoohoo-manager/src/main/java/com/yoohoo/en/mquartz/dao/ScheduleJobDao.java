package com.yoohoo.en.mquartz.dao;

import java.util.Map;

import com.yoohoo.en.mcore.dao.BaseDao;
import com.yoohoo.en.mquartz.entity.ScheduleJobEntity;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}

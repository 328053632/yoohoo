package com.yoohoo.en.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.yoohoo.en.dao.mapper.TTeacherScheduleNvMapper;
import com.yoohoo.en.dao.mapper.TTeacherScheduleRealTimeMapper;
import com.yoohoo.en.dao.model.TTeacherScheduleNv;
import com.yoohoo.en.dao.model.TTeacherScheduleRealTime;
import com.yoohoo.en.service.TeacherScheduleNvService;

@Service
public class TeacherScheduleNvServiceImpl implements TeacherScheduleNvService {
	@Autowired
	private TTeacherScheduleNvMapper tTeacherScheduleNvMapper;
	@Autowired
	private TTeacherScheduleRealTimeMapper tTeacherScheduleRealTimeMapper;

	@Override
	public void save(List<TTeacherScheduleNv> modelList) {
		tTeacherScheduleNvMapper.insertList(modelList);
		tTeacherScheduleRealTimeMapper.insertList(TTeacherScheduleRealTime.buildList(modelList));
	}

	@Override
	public List<TTeacherScheduleNv> getListByTeacherId(Long teacherId) {
		TTeacherScheduleNv record = new TTeacherScheduleNv();
		record.setTeacherId(teacherId);
		return tTeacherScheduleNvMapper.select(record);
	}

	@Override
	public List<TTeacherScheduleNv> getListByTeacherIdList(List<Object> teacherIdList) {
		Example example = new Example(TTeacherScheduleNv.class);
	    Example.Criteria criteria = example.createCriteria();
	    criteria.andIn("teacherId", teacherIdList);
		return tTeacherScheduleNvMapper.selectByExample(example);
	}

	@Override
	public List<TTeacherScheduleNv> getListByDateRange(Long teacherId, Date beginDate, Date endDate) {
		Example example = new Example(TTeacherScheduleNv.class);
	    Example.Criteria criteria = example.createCriteria();
	    criteria.andEqualTo("teacherId", teacherId);
	    criteria.andBetween("date", beginDate, endDate);
		return tTeacherScheduleNvMapper.selectByExample(example);
	}

	@Override
	public List<TTeacherScheduleNv> getListByDate(Long teacherId,
			Integer date) {
		Example example = new Example(TTeacherScheduleNv.class);
	    Example.Criteria criteria = example.createCriteria();
	    criteria.andEqualTo("teacherId", teacherId);
	    criteria.andEqualTo("date", date);
		return tTeacherScheduleNvMapper.selectByExample(example);
	}

	@Override
	public void update(List<TTeacherScheduleNv> list) {
		for(TTeacherScheduleNv e:list){
			if(e.getId() != null){
				tTeacherScheduleNvMapper.updateByPrimaryKeySelective(e);
				continue;
			}
			tTeacherScheduleNvMapper.insert(e);
		}
	}

	@Override
	public List<Long> queryTeacherIdsByTime(Date beginTime, Date endTime) {
		Example example = new Example(TTeacherScheduleRealTime.class);
	    Example.Criteria criteria = example.createCriteria();
	    criteria.andLessThanOrEqualTo("beginTime", beginTime);
	    criteria.andGreaterThan("endTime", beginTime);
	    criteria.andLessThan("beginTime", endTime);
	    criteria.andGreaterThanOrEqualTo("endTime", endTime);
		List<TTeacherScheduleRealTime> scheduleList = tTeacherScheduleRealTimeMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(scheduleList)){
			return null;
		}
		return scheduleList.stream().map(TTeacherScheduleRealTime::getTeacherId).distinct().collect(Collectors.toList());
	}
}

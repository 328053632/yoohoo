package com.yoohoo.en.web.teacher.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.mapper.TTeacherScheduleMapper;
import com.yoohoo.en.dao.model.TTeacherScheduleNv;
import com.yoohoo.en.service.TeacherScheduleNvService;
import com.yoohoo.en.utils.DateUtil;
import com.yoohoo.en.web.params.TeacherScheduleQuery;
import com.yoohoo.en.web.teacher.service.TTeacherScheduleService;

/**
 * Created By LiWenLong On 2018/11/13 14:17
 * E-Mail:it_lwl@163.com
 */
@Service
public class TTeacherScheduleServiceImpl implements TTeacherScheduleService {
    @Autowired
    TTeacherScheduleMapper tTeacherScheduleMapper;

    @Autowired
    private TeacherScheduleNvService teacherScheduleNvService;

    @Autowired
    TTeacherInfoMapper tTeacherInfoMapper;


    @Autowired
    TClassScheduleMapper tClassScheduleMapper;
    @Override
    public void saveTeacherSchedule(List<TTeacherScheduleNv> list) {
    	teacherScheduleNvService.save(list);
    }
    
    @Override
    public void updateTeacherSchedule(List<TTeacherScheduleNv> list) {
    	teacherScheduleNvService.update(list);
    }

    @Override
    public List<TTeacherScheduleNv> queryTeacherSchedule(TeacherScheduleQuery query) {
    	if(query.getDate() != null){
        	return teacherScheduleNvService.getListByDate(query.getTeacherId(),query.getDate());	
    	}
    	if(query.getBeginTime() != null || query.getEndTime() != null){
        	return teacherScheduleNvService.getListByDateRange(query.getTeacherId(),query.getBeginTime(), query.getEndTime());
    	}
    	return teacherScheduleNvService.getListByTeacherId(query.getTeacherId());	
    }

	@Override
	public void deleteTeacherScheduleAfterToday(Long teacherId) {
		TTeacherScheduleNv model = new TTeacherScheduleNv();
		model.setTeacherId(teacherId);
		model.setDate(DateUtil.getIntValueFromLocalDate(LocalDate.now()));
		tTeacherScheduleMapper.deleteTeacherScheduleAfterToday(model);
	}
}

package com.yoohoo.en.teacher.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TClassScheduleExample;
import com.yoohoo.en.student.dao.TStudentInfoDao;
import com.yoohoo.en.teacher.service.TClassScheduleService;

/**
 * Created By LiWenLong On 2018/11/13 15:20
 * E-Mail:it_lwl@163.com
 */
@Service
public class TClassScheduleServiceImpl implements TClassScheduleService {

  @Autowired
  TClassScheduleMapper tClassScheduleMapper;
  @Autowired
  TStudentInfoDao tStudentInfoDao;

    @Override
    public List<String>  queryTeacherSchedule(Integer teacherId, String dateTime) throws ParseException {
        List<String> teacherTimeList=new ArrayList<>();
        TClassScheduleExample example=new TClassScheduleExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId).andDateLabelEqualTo(dateTime).andStatusEqualTo(1);
        List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(example);
        if(null!= tClassSchedules &&tClassSchedules.size()>0)
        {
            //老师当天有课
            for(TClassSchedule t:tClassSchedules){
                String lessonTime = String.valueOf(t.getLessonTime()).substring(11, 16);
                String endTime = String.valueOf(t.getEndTime()).substring(11, 16);
                teacherTimeList.add(lessonTime+"-"+endTime);
            }
            if(teacherTimeList.size()>0){
                return teacherTimeList;
            }
        }
        return null;
    }
}

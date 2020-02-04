package com.yoohoo.en.teacher.service.impl;

import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.mapper.TTeacherScheduleMapper;
import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.teacher.service.TTeacherScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created By LiWenLong On 2018/11/13 14:17
 * E-Mail:it_lwl@163.com
 */
@Service
public class TTeacherScheduleServiceImpl implements TTeacherScheduleService {
    @Autowired
    TTeacherScheduleMapper tTeacherScheduleMapper;


    @Autowired
    TClassScheduleMapper tClassScheduleMapper;

    @Autowired
    TTeacherInfoMapper tTeacherInfoMapper;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
    @Override
    public void saveTeacherSchedule(TTeacherSchedule tTeacherSchedule) throws ParseException {
        DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        TTeacherScheduleExample example=new TTeacherScheduleExample();
        example.createCriteria().andTeacherIdEqualTo(tTeacherSchedule.getTeacherId()).andDateEqualTo(f2.parse(tTeacherSchedule.getDate()));
        List<TTeacherSchedule> tTeacherSchedules = tTeacherScheduleMapper.selectByExample(example);
        if(null!=tTeacherSchedules &&tTeacherSchedules.size()>0){
            TTeacherSchedule bean = tTeacherSchedules.get(0);
            if(null!=tTeacherSchedule.getTimeScheduleStr() && ! tTeacherSchedule.getTimeScheduleStr().equals("") ){
                bean.setTimeScheduleStr(tTeacherSchedule.getTimeScheduleStr());
            }
            tTeacherScheduleMapper.updateByExample(bean,example);
        }else{
            tTeacherScheduleMapper.insert(tTeacherSchedule);
        }
    }

    @Override
    public List<String> queryTeacherSchedule(Integer teacherId, String date) throws ParseException {
        DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        TTeacherScheduleExample example=new TTeacherScheduleExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId).andDateEqualTo(f2.parse(date));
        List<TTeacherSchedule> tTeacherSchedules = tTeacherScheduleMapper.selectByExample(example);
        List<String> teacherList=new ArrayList<>();
        if(null!=tTeacherSchedules && tTeacherSchedules.size()>0){
            TTeacherSchedule tTeacherSchedule = tTeacherSchedules.get(0);
            //获取尝试
            String timeScheduleStr = tTeacherSchedule.getTimeScheduleStr();
            System.out.println(timeScheduleStr);
            String[] split = timeScheduleStr.split(",");
            for(int i=0;i<split.length;i++){
                teacherList.add(split[i]);
            }
            if(teacherList.size()>0){
                return teacherList;
            }
        }
        return null;
    }

    @Override
    public List<TTeacherInfo> getTeacherListByTime(String dateTime, String timeSchedule) throws ParseException {
       List<TTeacherInfo> tTeacherInfoList=new ArrayList<>();
        String[] split = timeSchedule.split("-");
        String lesson= dateTime+" "+split[0]+":00";
        String end=dateTime+" "+split[1]+":00";
        DateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lessonTime = f2.parse(lesson);
        Date endTime = f2.parse(end);
        //记录已经符合条件的老师ID集合
        List<Integer> teacherList=new ArrayList<>();
        //1.根据日期和时刻写查询语句查询 老师时刻表中符合条件的老师
        Long loginUserId = null;
        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId())){
        	loginUserId = ShiroUtils.getUserId();
        }
        List<Integer> tTeacherIds = tTeacherScheduleMapper.queryTeacherByDateAndTimeSchedule(dateTime, timeSchedule, loginUserId);
        if(null !=tTeacherIds && tTeacherIds.size()>0){
            //2.循环遍历符合条件的老师进行二次筛选，根据老师ID 和当天日期和时刻查询是否已经被选择,记录被选择老师ID
            for(Integer teacherId:tTeacherIds){
                TClassScheduleExample classScheduleExample=new TClassScheduleExample();
                classScheduleExample.createCriteria().andLessonTimeEqualTo(lessonTime).andEndTimeEqualTo(endTime).andDateLabelEqualTo(dateTime).andTeacherIdEqualTo(teacherId).andStatusEqualTo(1);
                List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(classScheduleExample);
                if(null!=tClassSchedules && tClassSchedules.size()>0){
                    teacherList.add(tClassSchedules.get(0).getTeacherId());
                }
            }
            //去掉已经被选择的老师ID
            if(teacherList.size()>0){
                for(Integer delId:teacherList){
                    tTeacherIds.remove(delId);
                }
            }
            //3.遍历查询老师信息
            if(tTeacherIds.size()>0){
                for(Integer teacherId:tTeacherIds){
                    tTeacherInfoList.add(tTeacherInfoMapper.selectByPrimaryKey(teacherId));
                }
                //4.返回老师List集合
                return tTeacherInfoList;
            }else{
                return null;
            }

        }
        return null;
    }
}

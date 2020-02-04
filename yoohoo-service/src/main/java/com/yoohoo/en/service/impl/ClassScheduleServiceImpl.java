package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TClassStudentRMapper;
import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.mapper.TStudentLessonScheduleMapper;
import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.dao.model.ext.TClassScheduleExt;
import com.yoohoo.en.service.IClassScheduleService;
import com.yoohoo.en.service.IStudentLessonScheduleService;
import com.yoohoo.en.service.ITalkCloudService;
import com.yoohoo.en.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("tClassScheduleService")
public class ClassScheduleServiceImpl implements IClassScheduleService
{
    @Autowired
    private TClassScheduleMapper tClassScheduleMapper;

    @Autowired
    private TLessonChapterMapper tLessonChapterMapper;

    @Autowired
    private TClassStudentRMapper tClassStudentRMapper;

    @Autowired
    private IStudentLessonScheduleService studentLessonScheduleService;

    @Autowired
    private ITalkCloudService talkCloudService;

    @Autowired
    private TStudentLessonScheduleMapper tStudentLessonScheduleMapper;



    @Autowired
    private ITalkCloudService iTalkCloudService;
    @Override
    public TClassSchedule queryObject(Long scheduleId)
    {
        return tClassScheduleMapper.selectByPrimaryKey(scheduleId);
    }

    @Override
    public List<TClassSchedule> queryList(Map<String, Object> map)
    {
        TClassScheduleExample example = this.getExample(map);
        return tClassScheduleMapper.selectByExample(example);
    }

    @Override
    public int queryTotal(Map<String, Object> map)
    {
        TClassScheduleExample example = this.getExample(map);
        return tClassScheduleMapper.countByExample(example);
    }

    private TClassScheduleExample getExample(Map<String, Object> map)
    {
        TClassScheduleExample example = new TClassScheduleExample();
        TClassScheduleExample.Criteria criteria = example.createCriteria();
        if (null != map.get("classId"))
        {
        	Long classId = Long.valueOf(map.get("classId").toString());
            if (classId != 0)
            {
                criteria.andClassIdEqualTo(classId);
            }
        }
        if (null != map.get("lessonId"))
        {
            Integer lessonId = Integer.valueOf(map.get("lessonId").toString());
            if (lessonId != 0)
            {
                criteria.andLessonIdEqualTo(lessonId);
            }
        }
        return example;
    }

    @Override
    public void save(TClassSchedule tClassSchedule)
    {
        tClassScheduleMapper.insertSelective(tClassSchedule);
    }

    @Override
    public void update(TClassSchedule tClassSchedule)
    {
        tClassScheduleMapper.updateByPrimaryKeySelective(tClassSchedule);
    }

    @Override
    public void delete(Integer scheduleId)
    {
        tClassScheduleMapper.deleteByPrimaryKey(scheduleId);
    }

    @Override
    public void deleteBatch(List<Integer> scheduleIds)
    {
        TClassScheduleExample example = new TClassScheduleExample();
        example.createCriteria().andScheduleIdIn(scheduleIds);
        tClassScheduleMapper.deleteByExample(example);
    }

    @Override
    public List<TClassScheduleExt> queryListExt(Map<String, Object> map) {
        List<TClassScheduleExt> extList = new ArrayList<>();
        List<TClassSchedule> list = this.queryList(map);
        for (TClassSchedule tClassSchedule : list)
        {
            TClassScheduleExt tClassScheduleExt = new TClassScheduleExt();
            BeanUtils.copyProperties(tClassSchedule, tClassScheduleExt);
            extList.add(tClassScheduleExt);
            if (null != tClassScheduleExt.getChapterId())
            {
                TLessonChapter lessonChapter = tLessonChapterMapper.selectByPrimaryKey(tClassScheduleExt.getChapterId());
                if (null != lessonChapter)
                {
                    tClassScheduleExt.setChapterName(lessonChapter.getTitle());
                    tClassScheduleExt.setOrderNum(lessonChapter.getOrderNum());
                }
                if (null != tClassScheduleExt.getLessonTime())
                {
                    tClassScheduleExt.setLessonTimeStr(DateFormatUtils.format(tClassScheduleExt.getLessonTime(), "yyyy-MM-dd HH:mm"));
                }
                if (null != tClassScheduleExt.getEndTime())
                {
                    tClassScheduleExt.setEndTimeStr(DateFormatUtils.format(tClassScheduleExt.getEndTime(), "yyyy-MM-dd HH:mm"));
                }
                if(null !=tClassScheduleExt.getEndTime()&& null != tClassScheduleExt.getLessonTime()){

                    String endTimeStr = tClassScheduleExt.getEndTimeStr();
                    String lessonTimeStr = tClassScheduleExt.getLessonTimeStr();
                    List<String> endTimeStrList = StringUtil.splitString(endTimeStr, " ");
                    List<String> lessonTimeStrList = StringUtil.splitString(lessonTimeStr, " ");
                    tClassScheduleExt.setLessonTimeS(lessonTimeStrList.get(1)+"-"+endTimeStrList.get(1));
                }
            }
        }
        Collections.sort(extList, new Comparator<TClassScheduleExt>()
        {
            @Override
            public int compare(TClassScheduleExt o1, TClassScheduleExt o2)
            {
                return Integer.compare(o1.getOrderNum(), o2.getOrderNum());
            }
        });
        return extList;
    }

    @Override
    public void saveSchedule(TClassScheduleExt classScheduleExt) {
        if (null == classScheduleExt)
        {
            return;
        }
        Date curDate = new Date();
        try {
            if (StringUtils.isNotEmpty(classScheduleExt.getLessonTimeStr()))
            {
                Date lessonTime = DateUtils.parseDate(classScheduleExt.getLessonTimeStr(), new String[]{"yyyy-MM-dd HH:mm"});
                classScheduleExt.setLessonTime(lessonTime);
                classScheduleExt.setDateLabel(DateFormatUtils.format(lessonTime, "yyyy-MM-dd"));
            }
            if (StringUtils.isNotEmpty(classScheduleExt.getEndTimeStr()))
            {
                Date endTime = DateUtils.parseDate(classScheduleExt.getEndTimeStr(), new String[]{"yyyy-MM-dd HH:mm"});
                classScheduleExt.setEndTime(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //更新班级课程信息
        if (classScheduleExt.getStatus() == 0)
        {
            //状态更改为已发布
            classScheduleExt.setStatus(1);

            //将课程信息同步给当前班级下的所有学生
        //    this.syncStuSchedule(classScheduleExt);
        }
            tClassScheduleMapper.updateByPrimaryKeySelective(classScheduleExt);
            talkCloudService.createOrUpdateRoom(classScheduleExt.getScheduleId() == null ?null:classScheduleExt.getScheduleId().intValue(), 
							            		classScheduleExt.getLessonId() == null ?null:classScheduleExt.getLessonId().intValue(),
							            		classScheduleExt.getChapterId() == null ?null:classScheduleExt.getChapterId().intValue(), 
							            		classScheduleExt.getClassId() == null ?null:classScheduleExt.getClassId().intValue(), 
							            		classScheduleExt.getTeacherId() == null ?null:classScheduleExt.getTeacherId().intValue());
    }

    @Override
    public Integer updateTime(TClassScheduleExt classScheduleExt) {

        Integer i = null;
            if (null == classScheduleExt)
            {
                return null;
            }
            //更新班级课程信息
            if (classScheduleExt.getStatus() == 1)
            {
                //删除当前课节在talk云中关联课件
                //查询当前房间关联的文件进行删除
               List<String> talkFileId=   iTalkCloudService.getRoomFile(classScheduleExt.getRoomId());
                if(null!=talkFileId&& talkFileId.size()!=0) {
                    i = talkCloudService.deleteFileById(talkFileId);
                    if(i!=0){
                        return null;
                    }
                 }
                    classScheduleExt.setIsUpload(0);
                    //状态更改为未发布(更新 t_class_schedule的表中状态)
                    classScheduleExt.setStatus(0);
                    this.delStuSchedule(classScheduleExt);
                    TClassSchedule tClassSchedule = tClassScheduleMapper.selectByPrimaryKey(classScheduleExt.getScheduleId());
                    classScheduleExt.setRoomStuPasswd(tClassSchedule.getRoomStuPasswd());
                    classScheduleExt.setRoomTeacherPasswd(tClassSchedule.getRoomTeacherPasswd());
            }
                    tClassScheduleMapper.updateByPrimaryKey(classScheduleExt);
                    talkCloudService.createOrUpdateRoom(classScheduleExt.getScheduleId() == null ?null:classScheduleExt.getScheduleId().intValue(), 
									            		classScheduleExt.getLessonId() == null ?null:classScheduleExt.getLessonId().intValue(),
									            		classScheduleExt.getChapterId() == null ?null:classScheduleExt.getChapterId().intValue(), 
									            		classScheduleExt.getClassId() == null ?null:classScheduleExt.getClassId().intValue(), 
									            		classScheduleExt.getTeacherId() == null ?null:classScheduleExt.getTeacherId().intValue());
            return 0;
    }


    /**
     *
     * 取消课节时删除当前课节的
     * @param classScheduleExt
     */

    private void delStuSchedule(TClassScheduleExt classScheduleExt) {

        if(classScheduleExt.getScheduleId()!=null){
            //删除当前课节的的插班学生
            TStudentLessonScheduleExample example=new TStudentLessonScheduleExample();
            example.createCriteria().andScheduleIdEqualTo(classScheduleExt.getScheduleId()).andTypeNotEqualTo(1);
            List<TStudentLessonSchedule> tStudentLessonSchedules = tStudentLessonScheduleMapper.selectByExample(example);
            if (null!=tStudentLessonSchedules &&tStudentLessonSchedules.size()>0)
            {
                for(TStudentLessonSchedule t:tStudentLessonSchedules){
                    //遍历删除
                    tStudentLessonScheduleMapper.deleteByPrimaryKey(t.getRecordId());
                }
            }
        }
        return ;
    }

    /**
     * 同步班级课程表到班级下面的学生
     * @param classSchedule
     */
    @Override
    public void syncStuSchedule(TClassSchedule classSchedule) {
//        if (classSchedule.getStatus() == 0)
//        {
//            return;
//        }
        //更新班级下的学生课程信息
        TClassStudentRExample example = new TClassStudentRExample();
        example.createCriteria().andClassIdEqualTo(classSchedule.getClassId()).andStatusNotEqualTo(0);
        List<TClassStudentR> classStudentRList = tClassStudentRMapper.selectByExample(example);
        if (null != classStudentRList && classStudentRList.size() > 0)
        {
            Date curDate = new Date();
            List<TStudentLessonSchedule> studentScheduleList = new ArrayList<>();
            for (TClassStudentR tClassStudentR : classStudentRList)
            {
                TStudentLessonSchedule studentSchedule = new TStudentLessonSchedule();
                studentSchedule.setAddtime(curDate);
                studentSchedule.setStudentId(tClassStudentR.getStudentId());
                studentSchedule.setScheduleId(classSchedule.getScheduleId());
                studentSchedule.setLessonId(classSchedule.getLessonId());
                studentSchedule.setChapterId(classSchedule.getChapterId());
                studentSchedule.setStatus(classSchedule.getStatus());
                studentSchedule.setType(1);
                studentScheduleList.add(studentSchedule);
            }
            //批量添加学生对应的课程信息
            studentLessonScheduleService.saveBatchStuSchedule(studentScheduleList);
        }
    }

    @Override
    public Integer selectByScheduleId(Long scheduleId) {
        TClassScheduleExample example=new TClassScheduleExample();
        example.createCriteria().andScheduleIdEqualTo(scheduleId);
        TClassSchedule tClassSchedule = tClassScheduleMapper.selectByPrimaryKey(scheduleId);
        if(null==tClassSchedule.getIsUpload() || tClassSchedule.getIsUpload()==0){
            return 0;
        }
        return 1;
    }

    @Override
    public boolean checkTimeSchedule(String lessonTimeS, String endTimeStr,Integer teacherId) throws ParseException {

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date lessonTime = format.parse(lessonTimeS+":00");
            Date endTime = format.parse(endTimeStr+":00");
            // endTimeStr"2018-11-19 08:55"   lessonTimeS 2018-11-19 08:30
            TClassScheduleExample example=new TClassScheduleExample();
            example.createCriteria().andLessonTimeEqualTo(lessonTime).andEndTimeEqualTo(endTime).andTeacherIdEqualTo(teacherId);

            List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(example);
            if(null!=tClassSchedules && tClassSchedules.size()>0){
                //如果不为NULL 说明该老师已经选择过该时间
                return false;
            }else{
                return  true;
            }

    }
}

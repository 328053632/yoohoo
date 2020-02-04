package com.yoohoo.en.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TStudentLessonScheduleMapper;
import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TClassScheduleExample;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.TStudentLessonScheduleExample;
import com.yoohoo.en.dao.model.ext.TStudentLessonScheduleExt;
import com.yoohoo.en.service.IStudentInfoService;
import com.yoohoo.en.service.IStudentLessonScheduleService;

@Service("tStudentLessonScheduleService")
public class StudentLessonScheduleServiceImpl implements IStudentLessonScheduleService
{
    @Autowired
    private TStudentLessonScheduleMapper tStudentLessonScheduleMapper;

    @Autowired
    private IStudentInfoService studentInfoService;

    @Autowired
    private TClassScheduleMapper tClassScheduleMapper;


    @Override
    public List<TStudentLessonScheduleExt> getListExt(Map<String, Object> map) {

        TStudentLessonScheduleExample studentLessonScheduleExample = new TStudentLessonScheduleExample();
        TStudentLessonScheduleExample.Criteria criteria = studentLessonScheduleExample.createCriteria();
        if (null != map.get("scheduleId"))
        {
        	Long scheduleId = Long.valueOf(map.get("scheduleId").toString());
            if (scheduleId != 0)
            {
                criteria.andScheduleIdEqualTo(scheduleId);
            }
        }
        if (null != map.get("type"))
        {
            criteria.andTypeEqualTo(Integer.valueOf(map.get("type").toString()));
        }
        if (null != map.get("addUserId"))
        {
            criteria.andUserIdEquals(Long.valueOf(map.get("addUserId").toString()));
        }
        List<TStudentLessonSchedule> list = tStudentLessonScheduleMapper.selectByExample(studentLessonScheduleExample);
        if (null != list && list.size() > 0)
        {
            List<TStudentLessonScheduleExt> extList = new ArrayList<>();
            for (TStudentLessonSchedule tStudentLessonSchedule : list)
            {
                TStudentLessonScheduleExt tStudentLessonScheduleExt = new TStudentLessonScheduleExt();
                BeanUtils.copyProperties(tStudentLessonSchedule, tStudentLessonScheduleExt);
                if (null != tStudentLessonScheduleExt.getStudentId())
                {
                    TStudentInfo studentInfo = studentInfoService.query(tStudentLessonScheduleExt.getStudentId().intValue());
                    if(studentInfo != null)
                    	tStudentLessonScheduleExt.setStudentName(studentInfo.getName());
                    extList.add(tStudentLessonScheduleExt);
                }
            }
            return extList;
        }
        return null;
    }

    @Override
    public void saveBatchStuSchedule(List<TStudentLessonSchedule> list)
    {
        Date curDate = new Date();
        List<TStudentLessonSchedule> tmpList = new ArrayList<>();
        for (TStudentLessonSchedule tStudentLessonSchedule : list)
        {
            TStudentLessonScheduleExample example = new TStudentLessonScheduleExample();
            example.createCriteria()
                .andScheduleIdEqualTo(tStudentLessonSchedule.getScheduleId())
                .andStudentIdEqualTo(tStudentLessonSchedule.getStudentId());
            if (tStudentLessonScheduleMapper.countByExample(example) > 0)
            {
                List<TStudentLessonSchedule> tStudentLessonSchedules = tStudentLessonScheduleMapper.ByExample(tStudentLessonSchedule.getScheduleId());
                for (TStudentLessonSchedule tStudentLessonSchedule1:tStudentLessonSchedules
                     ) {
                    TStudentLessonSchedule tStudentLessonSchedule222 = new TStudentLessonSchedule();
                    tStudentLessonSchedule222.setStatus(tStudentLessonSchedule.getStatus());
                    tStudentLessonSchedule222.setRecordId(tStudentLessonSchedule1.getRecordId());
                    tStudentLessonScheduleMapper.updateByPrimaryKeySelective(tStudentLessonSchedule222);
                }
                continue;
            }
            tStudentLessonSchedule.setAddtime(curDate);
            tmpList.add(tStudentLessonSchedule);
        }
        if (tmpList.size() > 0)
        {
            this.tStudentLessonScheduleMapper.insertBatch(tmpList);
        }
    }

    @Override
    public int delStuSchedule(int recordId)
    {
        return tStudentLessonScheduleMapper.deleteByPrimaryKey(recordId);
    }



    /**
     * 批量存储状态为1 的学生
     * @param list
     */

    @Transactional
    @Override
    public void saveBatchNomalStuSchedule(List<TStudentLessonSchedule> list) {
        Date curDate = new Date();
        //临时课节ID
        List<Long> tempScheduleId=new ArrayList<>();

        List<TStudentLessonSchedule> tmpList = new ArrayList<>();

        //1.获取班级ID，
        Long classId = list.get(0).getClassId();

        //2.获取scheduleId
        Long scheduleId = list.get(0).getScheduleId();

        //3通过班级ID 查询当前班级的所有课节状态为未发布状态(0)
        TClassScheduleExample tClassScheduleExample=new TClassScheduleExample();
        tClassScheduleExample.createCriteria().andClassIdEqualTo(classId).andStatusEqualTo(0);
        //4获得符合条件的ALLscheduleID集合
        List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(tClassScheduleExample);
        Set<Long> allScheduleList=new HashSet<>();
        for (TClassSchedule t:tClassSchedules){
            allScheduleList.add(t.getScheduleId());
        }
            //5.查询StuLessonSchedule中当前Currentshcedule的数组
            TStudentLessonScheduleExample studentLessonScheduleExample=new TStudentLessonScheduleExample();
            studentLessonScheduleExample.createCriteria().andScheduleIdEqualTo(scheduleId);
            List<TStudentLessonSchedule> currentSchedule = tStudentLessonScheduleMapper.selectByExample(studentLessonScheduleExample);
            //遍历要添加的学生信息
            for(TStudentLessonSchedule tStudentLessonSchedule:list){
                TStudentLessonScheduleExample example=new TStudentLessonScheduleExample();
                example.createCriteria().andScheduleIdEqualTo(tStudentLessonSchedule.getScheduleId())
                        .andStudentIdEqualTo(tStudentLessonSchedule.getStudentId());
                List<TStudentLessonSchedule> schedulelist = tStudentLessonScheduleMapper.selectByExample(example);
                if(null!=schedulelist && schedulelist.size()>0){
                    TStudentLessonSchedule bean=schedulelist.get(0);
                    bean.setStatus(tStudentLessonSchedule.getStatus());
                    tStudentLessonScheduleMapper.updateByExampleSelective(bean,example);
               }else{
                    tStudentLessonSchedule.setAddtime(curDate);
                    tmpList.add(tStudentLessonSchedule);
                }
            }
        if (tmpList.size() > 0)
        {
            this.tStudentLessonScheduleMapper.insertBatch(tmpList);
        }
        for(TStudentLessonSchedule t:tmpList){
            allScheduleList.remove(t.getScheduleId());
        }
        List<TStudentLessonSchedule> newStuLessonScheduleList=new ArrayList<>();
        //剩余的进行批量关联学生
        for(Long i:allScheduleList){
            for(TStudentLessonSchedule oldList:list){
                //查询是否已经存储过当前学生信息
                TStudentLessonScheduleExample example=new TStudentLessonScheduleExample();
                example.createCriteria().andStudentIdEqualTo(oldList.getStudentId()).andScheduleIdEqualTo(i);
                List<TStudentLessonSchedule> tStudentLessonSchedules = tStudentLessonScheduleMapper.selectByExample(example);
                if(null!=tStudentLessonSchedules &&tStudentLessonSchedules.size()>0){
                   //说明当前ID已经存在当前学生不需要在添加跳出循环即可
                    continue;
                }
                TClassSchedule tClassSchedule = tClassScheduleMapper.selectByPrimaryKey(i);
                TStudentLessonSchedule newStu=new TStudentLessonSchedule();
                newStu.setStatus(oldList.getStatus());
                newStu.setAddtime(new Date());
                newStu.setType(oldList.getType());
                newStu.setStudentId(oldList.getStudentId());
                newStu.setScheduleId(i);
                newStu.setLessonId(oldList.getLessonId());
                newStu.setChapterId(tClassSchedule.getChapterId());
                newStu.setAddBaseInfo(oldList.getAddUserId());
                newStuLessonScheduleList.add(newStu);
            }
        }
        if (newStuLessonScheduleList.size() > 0)
        {
            this.tStudentLessonScheduleMapper.insertBatch(newStuLessonScheduleList);
        }
    }

    @Override
    public void delBatchStuScheduleByStuId(Long classId, Long studentId) {
        //1.根据班级ID查询该班级课节 还未发布课节的状态  0
        TClassScheduleExample example=new TClassScheduleExample();
        example.createCriteria().andClassIdEqualTo(classId).andStatusEqualTo(0);
        List<TClassSchedule> tClassSchedules = tClassScheduleMapper.selectByExample(example);
            List<Long> ScheduleIds=new ArrayList<>();
        for (TClassSchedule t:tClassSchedules ) {
            ScheduleIds.add(t.getScheduleId());
        }
        //2.遍历删除即可
        List<TStudentLessonSchedule> delList=new ArrayList<>();
        for(Long sId:ScheduleIds){
            TStudentLessonSchedule tStudentLessonSchedule=new TStudentLessonSchedule();
            tStudentLessonSchedule.setScheduleId(sId);
            tStudentLessonSchedule.setStudentId(studentId);
            delList.add(tStudentLessonSchedule);
        }

        if(delList.size()>0){
            this.tStudentLessonScheduleMapper.deleteBatch(delList);
        }
    }
}

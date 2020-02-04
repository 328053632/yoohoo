package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.mapper.*;
import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.dao.model.ext.TClassExt;
import com.yoohoo.en.service.IClassScheduleService;
import com.yoohoo.en.service.IClassService;
import com.yoohoo.en.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static  com.yoohoo.en.constant.ApplicationConstant.*;

@Service("tClassService")
public class ClassServiceImpl implements IClassService
{
    @Autowired
    private TClassMapper tClassMapper;

    @Autowired
    private TClassDefineMapper tClassDefineMapper;

    @Autowired
    private TLessonInfoMapper tLessonInfoMapper;

    @Autowired
    private TTeacherInfoMapper tTeacherInfoMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TClassStudentRMapper tClassStudentRMapper;

    @Autowired
    private TLessonChapterMapper tLessonChapterMapper;

    @Autowired
    private TClassScheduleMapper tClassScheduleMapper;

    @Autowired
    private TStudentLessonScheduleMapper tStudentLessonScheduleMapper;

    @Autowired
    private IClassScheduleService classScheduleService;

    @Override
    public TClass queryObject(Long classId)
    {
        return tClassMapper.selectByPrimaryKey(classId);
    }

    @Override
    public List<TClass> queryList(Map<String, Object> map)
    {
        TClassExample example = this.getExample(map);
        example.setPageHelper(new PageHelper((Integer) map.get("offset"), (Integer) map.get("limit")));
        example.setOrderByClause("last_update_time DESC");
        return tClassMapper.selectByExample(example);
    }

    @Override
    public int queryTotal(Map<String, Object> map)
    {
        TClassExample example = this.getExample(map);
        return tClassMapper.countByExample(example);
    }

    private TClassExample getExample(Map<String, Object> map)
    {
        TClassExample example = new TClassExample();
        TClassExample.Criteria criteria = example.createCriteria();
        if (null != map.get("lessonId"))
        {
            Integer lessonId = Integer.valueOf(map.get("lessonId").toString());
            if (lessonId != 0)
            {
                criteria.andLessonIdEqualTo(lessonId);
            }
        }
        if (null != map.get("beginDate"))
        {
            String beginDateStr = map.get("beginDate").toString();
            if (StringUtils.isNotEmpty(beginDateStr)) {
                try {
                    Date beginDate = DateUtils.parseDate(map.get("beginDate").toString(), new String[]{"yyyy-MM-dd"});
                    criteria.andBeginDateGreaterThan(beginDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (null != map.get("endDate"))
        {
            String endDateStr = map.get("endDate").toString();
            if (StringUtils.isNotEmpty(endDateStr)){
                try {
                    Date endDate = DateUtils.parseDate(map.get("endDate").toString(), new String[]{"yyyy-MM-dd"});
                    if(null != endDate)
                	{
                    	endDate=DateUtils.addDays(endDate, 1);
                	}
                    criteria.andBeginDateLessThan(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        String className=(String) map.get("className");
        if(StringUtils.isNotEmpty(className))
        {
        	criteria.andClassNameLike("%"+className+"%");
        }

        if(null != map.get("classType"))
        {

            Integer classType=Integer.parseInt(map.get("classType").toString());

            if(classType!=0){
                criteria.andClassTypeEqualTo(classType);
            }

        }

        if(null!=map.get("consumeType")){

            Integer consumeType = Integer.parseInt(map.get("consumeType").toString());
            if(consumeType!=0){
                criteria.andClassItemIdEqualTo(consumeType);
            }
        }


        return example;
    }

    @Override
    public Long save(TClass tClass)
    {
        tClassMapper.insertSelective(tClass);
        Long classId = tClass.getClassId();
        if (null != classId)
        {
            //初始化班级课程表
            TLessonChapterExample chapterExample = new TLessonChapterExample();
            chapterExample.createCriteria().andLessonIdEqualTo(tClass.getLessonId());
            List<TLessonChapter> chapterList = tLessonChapterMapper.selectByExample(chapterExample);
            if (null != chapterList && chapterList.size() > 0)
            {
                List<TClassSchedule> classScheduleList = new ArrayList<>();
                for (TLessonChapter tLessonChapter : chapterList)
                {
                    TClassSchedule tClassSchedule = new TClassSchedule();
                    tClassSchedule.setClassId(tClass.getClassId());
                    tClassSchedule.setLessonId(tClass.getLessonId());
                    tClassSchedule.setTeacherId(tClass.getTeacherId());
                    tClassSchedule.setEteacherId(tClass.geteTeacherId());
                    tClassSchedule.setMasterteacherId(tClass.getMasterTeacherId());
                    tClassSchedule.setChapterId(tLessonChapter.getChapterId());
                    tClassSchedule.setStatus(0);
                    tClassSchedule.setCheckStatus(CheckStatus.NOTCHECK.getValue());
                    classScheduleList.add(tClassSchedule);
                }
                tClassScheduleMapper.insertBatch(classScheduleList);
            }
        }
        return classId;
    }

    @Transactional
    @Override
    public void update(TClass tClass)
    {
        tClass.setLastUpdateTime(new Date());
        tClassMapper.updateByPrimaryKeySelective(tClass);

    }

    @Override
    public void delete(Long classId)
    {
        //删除班级下面的学生
        this.deleteBatchStudent(classId, null,null);

        //删除班级课程表
        TClassScheduleExample classScheduleExample = new TClassScheduleExample();
        classScheduleExample.createCriteria().andClassIdEqualTo(classId);
        tClassScheduleMapper.deleteByExample(classScheduleExample);
        //删除班级
        tClassMapper.deleteByPrimaryKey(classId);
    }

    @Override
    public void deleteBatch(List<Integer> classIds)
    {
        TClassExample example = new TClassExample();
        example.createCriteria().andClassIdIn(classIds);
        tClassMapper.deleteByExample(example);
    }

    @Override
    public List<TClassExt> queryListExt(Map<String, Object> map) {
        List<TClassExt> extList = new ArrayList<>();
        List<TClass> list = this.queryList(map);
        for (TClass tClass : list)
        {
            TClassExt tClassExt = new TClassExt();
            BeanUtils.copyProperties(tClass, tClassExt);
            extList.add(tClassExt);
            if (null != tClassExt.getClassItemId())
            {
                TClassDefine tClassDefine = tClassDefineMapper.selectByPrimaryKey(tClassExt.getClassItemId());
                if (null != tClassDefine)
                {
                    tClassExt.setClassItem(tClassDefine.getClassItemName());
                }
            }
            if (null != tClassExt.getLessonId())
            {
                TLessonInfo tLessonInfo = tLessonInfoMapper.selectByPrimaryKey(tClassExt.getLessonId());
                if (null != tLessonInfo)
                {
                    tClassExt.setLessonName(tLessonInfo.getTitle());
                }
            }
            if (null != tClassExt.getMasterTeacherId())
            {
                TTeacherInfo tTeacherInfo = tTeacherInfoMapper.selectByPrimaryKey(tClassExt.getMasterTeacherId());
                if (null != tTeacherInfo)
                {
                    tClassExt.setMasterTeacher(tTeacherInfo.getName());
                }
            }
            if (null != tClassExt.getTeacherId())
            {
                TTeacherInfo tTeacherInfo = tTeacherInfoMapper.selectByPrimaryKey(tClassExt.getTeacherId());
                if (null != tTeacherInfo)
                {
                    tClassExt.setTeacher(tTeacherInfo.getName());
                }
            }
            if (null != tClassExt.getAssistantId())
            {
                TTeacherInfo tTeacherInfo = tTeacherInfoMapper.selectByPrimaryKey(tClassExt.getAssistantId());
                if (null != tTeacherInfo)
                {
                    tClassExt.setAssistant(tTeacherInfo.getName());
                }
            }
            if (null != tClassExt.geteTeacherId())
            {
                TTeacherInfo tTeacherInfo = tTeacherInfoMapper.selectByPrimaryKey(tClassExt.geteTeacherId());
                if (null != tTeacherInfo)
                {
                    tClassExt.seteTeacher(tTeacherInfo.getName());
                }
            }



            if (null != tClassExt.getLastUpdateAdmin())
            {
                SysUser sysUser = sysUserMapper.selectByPrimaryKey(Long.valueOf(tClassExt.getLastUpdateAdmin().toString()));
                if (null != sysUser)
                {
                    tClassExt.setLastUpdateAccount(sysUser.getUsername());
                }
            }
        }
        return extList;
    }

    @Override
    public TClassExt queryObjectExt(Long classId) {
        TClass tClass = this.queryObject(classId);
        if (null != tClass)
        {
            TClassExt tClassExt = new TClassExt();
            BeanUtils.copyProperties(tClass, tClassExt);
            if (null != tClassExt.getBeginDate())
            {
                tClassExt.setBeginDateStr(DateFormatUtils.format(tClassExt.getBeginDate(), "yyyy-MM-dd HH:mm:ss"));
            }
            if (null != tClassExt.getEndDate())
            {
                tClassExt.setEndDateStr(DateFormatUtils.format(tClassExt.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
            }
            return tClassExt;
        }

        return null;
    }

    @Override
    public void saveBatchStudent(List<TClassStudentR> list) {
        if (null != list && list.size() > 0)
        {
        	Long classId = list.get(0).getClassId();
            Date curDate = new Date();

            List<Long> newStudentIds = new ArrayList<>();
            List<TClassStudentR> addStudents = new ArrayList<>();
            for (TClassStudentR newStudentR : list)
            {
                newStudentIds.add(newStudentR.getStudentId());

                TClassStudentRExample example = new TClassStudentRExample();
                example.createCriteria().andClassIdEqualTo(classId).andStudentIdEqualTo(newStudentR.getStudentId());
                List<TClassStudentR> studentRList = tClassStudentRMapper.selectByExample(example);
                if (null != studentRList && studentRList.size() > 0)
                {
                    TClassStudentR oldStudentR = studentRList.get(0);
                    oldStudentR.setStatus(newStudentR.getStatus());
                    tClassStudentRMapper.updateByExampleSelective(oldStudentR,example);
                }
                else
                {
                    newStudentR.setAddTime(curDate);
                    addStudents.add(newStudentR);
                }
            }
            if (addStudents.size() > 0)
            {
                tClassStudentRMapper.insertBatch(addStudents);
            }

            //删除旧列表中存在但在新列表中不存在的学生数据
            this.deleteBatchStudent(classId, newStudentIds,null);

            //同步班级课程表到新加的学生
            this.syncStuSchedule(classId);

            //更新班级总人数
            TClass tClass = new TClass();
            tClass.setClassId(classId);
            tClass.setStudentNum(newStudentIds.size());
            this.update(tClass);
        }
    }

    /**
     * 批量删除学生信息
     */
    public void deleteBatchStudent(Long classId, List<Long> notInList, List<Long> inList)
    {
        if (null == classId)
        {
            return;
        }
        //删除班级下面学生对应的当前班级的课程表
        TClassScheduleExample classScheduleExample = new TClassScheduleExample();
        classScheduleExample.createCriteria().andClassIdEqualTo(classId);
        List<TClassSchedule> classScheduleList = tClassScheduleMapper.selectByExample(classScheduleExample);
        List<Long> scheduleIdList = new ArrayList<>();
        for (TClassSchedule classSchedule : classScheduleList)
        {
            if( CheckStatus.NOTCHECK.eq(classSchedule.getCheckStatus()) && (null ==classSchedule.getLessonTime() || new Date().before(classSchedule.getLessonTime())))
            {
                scheduleIdList.add(classSchedule.getScheduleId());
            }
        }
        if (scheduleIdList.size() > 0)
        {
            TStudentLessonScheduleExample studentLessonScheduleExample = new TStudentLessonScheduleExample();
            TStudentLessonScheduleExample.Criteria studentLessonScheduleCriteria = studentLessonScheduleExample.createCriteria();
            studentLessonScheduleCriteria.andScheduleIdIn(scheduleIdList);
            if (CollectionUtils.isNotEmpty(notInList))
            {
                studentLessonScheduleCriteria.andStudentIdNotIn(notInList);
            }
            if(CollectionUtils.isNotEmpty(inList))
            {
                studentLessonScheduleCriteria.andStudentIdIn(inList);
            }
            tStudentLessonScheduleMapper.deleteByExample(studentLessonScheduleExample);
        }

        //删除班级下面的学生
        TClassStudentRExample classStudentRExample = new TClassStudentRExample();
        TClassStudentRExample.Criteria classStudentRCriteria = classStudentRExample.createCriteria();
        classStudentRCriteria.andClassIdEqualTo(classId);
        if (CollectionUtils.isNotEmpty(notInList))
        {
            classStudentRCriteria.andStudentIdNotIn(notInList);
        }
        if(CollectionUtils.isNotEmpty(inList))
        {
            classStudentRCriteria.andStudentIdIn(inList);
        }
        TClassStudentR record=new TClassStudentR();
        record.setStatus(0);
        tClassStudentRMapper.updateByExampleSelective(record,classStudentRExample);
    }



    /**
     *
     * 根据class_id 查询到assiant_id 根据assiant_id  去t_teacher_info表中查询 name 字段
     * @param classId
     * @return
     */
    @Override
    public String findAssiantNameByClassId(Long classId) {
        TClass tClass = tClassMapper.selectByPrimaryKey(classId);
        if(null!=tClass){
            Integer assistantId = tClass.getAssistantId();
            if(null !=assistantId){
            String AssicantName =    tTeacherInfoMapper.selectNameById(assistantId);
                if(AssicantName!=null){
                    return  AssicantName;
                }else {
                    return  null;
                }
            }else{
                return  null;
            }
        }
        return null;
    }

    @Override
    public void updateShcedule(TClassExt tClass) {
        tClassScheduleMapper.updateTeacher(tClass.getTeacherId(),tClass.getClassId(),tClass.geteTeacherId(),tClass.getMasterTeacherId());
    }

    @Override
    public List<TClassExt> queryList(TClass tClass) {

        return  tClassMapper.queryList(tClass);
    }

    @Override
    public Integer queryTotal(TClass tClass) {
        return tClassMapper.queryTotal(tClass);
    }

    /**
     * 同步班级课程表到班级下面的学生
     * @param classId
     */
    private void syncStuSchedule(Long classId)
    {
        TClassScheduleExample classScheduleExample = new TClassScheduleExample();
        classScheduleExample.createCriteria().andClassIdEqualTo(classId);
        List<TClassSchedule> classScheduleList = tClassScheduleMapper.selectByExample(classScheduleExample);
        for(TClassSchedule tClassSchedule : classScheduleList)
        {
            classScheduleService.syncStuSchedule(tClassSchedule);
        }
    }

	@Override
	public String checkClassInfo(TClass c) {
		if(StringUtils.isEmpty(c.getClassName()))
		{
			return "班级名称不能为空";
		}
		
		TClassExample example=new TClassExample();
		TClassExample.Criteria cri =example.createCriteria().andClassNameEqualTo(c.getClassName());
		if(null != c.getClassId())
		{
			cri.andClassIdNotEqualTo(c.getClassId());
		}
		if(tClassMapper.countByExample(example) > 0)
		{
			return "班级名称不能重复";
		}
		return null;
	}
}

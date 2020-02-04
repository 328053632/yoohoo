package com.yoohoo.en.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yoohoo.en.bean.ServiceParam;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.mapper.*;
import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.dao.model.ext.*;
import com.yoohoo.en.model.PageModel;
import com.yoohoo.en.service.IStudentInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.TempLessonInfo;
import com.yoohoo.en.bean.ScheduleInfo;
import com.yoohoo.en.bean.message.LeaveMessage;
import com.yoohoo.en.bean.message.RemediationMessage;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.service.IStudentService;
import com.yoohoo.en.utils.DateUtil;
import com.yoohoo.en.utils.TypeItemUtils;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private TClassScheduleMapper classScheduleMapper;

    @Autowired
    private TLessonInfoMapper lessonInfoMapper;

    @Autowired
    private TLessonChapterMapper lessonChapterMapper;

    @Autowired
    private TClassMapper classMapper;

    @Autowired
    private TMessageMapper messageMapper;

    @Autowired
    private TTeacherInfoMapper tTeacherInfoMapper;

    @Autowired
    private TStudentLessonScheduleMapper studentLessonScheduleMapper;

    @Autowired
    private TStudentLearnPathMapper studentLearnPathMapper;

    @Autowired
    private IStudentInfoService studentInfoService;

    @Override
    public Map<String, List<ScheduleInfo>> scheduleList(int studentId, Map<String, Date> dateMap) {
        Map<String, List<ScheduleInfo>> result = new HashMap<>(8);

        if (dateMap == null || dateMap.isEmpty()) {
            return result;
        }

        for (String key : dateMap.keySet()) {
            List<ScheduleInfo> scheduleInfos = scheduleList(studentId, dateMap.get(key));

            Collections.sort(scheduleInfos, new Comparator<ScheduleInfo>() {
                @Override
                public int compare(ScheduleInfo o1, ScheduleInfo o2) {
                    if (o1 == null || o1.getDate() == null) {
                        return 1;
                    }
                    if (o2 == null || o2.getDate() == null) {
                        return -1;
                    }
                    return o1.getDate().compareTo(o2.getDate());
                }
            });

            status(scheduleInfos);
            result.put(key, scheduleInfos);
        }
        return result;
    }

    /**
     * 处理上课时间
     *
     * @param scheduleInfos
     */
    private void status(List<ScheduleInfo> scheduleInfos) {
        Date now = new Date();
        for (ScheduleInfo scheduleInfo : scheduleInfos) {
            if (scheduleInfo.getStatus() == 2) {
                scheduleInfo.setJoinStatus(1);
                continue;
            }

            if (new Date(now.getTime() + ApplicationConstant.LESSON_OPEN_BEFORE_STUDENT).before(scheduleInfo.getDate())) {
                scheduleInfo.setJoinStatus(-1);
                continue;
            }

            if (now.after(scheduleInfo.getEndTime())) {
                scheduleInfo.setJoinStatus(1);
                continue;
            }
            scheduleInfo.setJoinStatus(0);
        }
    }

    /**
     * 查询当天课程
     *
     * @param studentId
     * @param time
     * @return
     */
    private List<ScheduleInfo> scheduleList(int studentId, Date time) {
        Date date = DateUtil.startWithDay(time);
        return scheduleDAO.queryList(studentId, date, DateUtils.addDays(date, 1));
    }

    @Override
    public boolean applyLeave(Long scheduleId, TStudentInfo studentInfo) {
        TClassSchedule tClassSchedule = classScheduleMapper.selectByPrimaryKey(scheduleId);
        if (tClassSchedule == null) {
            return false;
        }

        TClass tClass = classMapper.selectByPrimaryKey(tClassSchedule.getClassId());
        if (tClass == null) {
            return false;
        }

        TLessonInfo tLessonInfo = lessonInfoMapper.selectByPrimaryKey(tClassSchedule.getLessonId());
        if (null == tLessonInfo) {
            return false;
        }

        TLessonChapter tLessonChapter = lessonChapterMapper.selectByPrimaryKey(tClassSchedule.getChapterId());
        if (null == tLessonChapter) {
            return false;
        }

        Date lessonTime = tClassSchedule.getLessonTime();
        Date now = new Date();

        // 修改课程状态
        TStudentLessonSchedule record = new TStudentLessonSchedule();
        record.setType((lessonTime.getTime() - now.getTime() > 1000 * 60 * 60 * 24) ?
                ApplicationConstant.MESSAGE_TYPE_LEAVE_2 :
                ApplicationConstant.MESSAGE_TYPE_LEAVE);
        TStudentLessonScheduleExample example = new TStudentLessonScheduleExample();
        example.createCriteria().andStudentIdEqualTo(studentInfo.getUserId()).andScheduleIdEqualTo(scheduleId);
        studentLessonScheduleMapper.updateByExampleSelective(record, example);

        LeaveMessage message = new LeaveMessage();
        message.setName(studentInfo.getName());
        message.setApplyPhone(studentInfo.getMsisdn());
        message.setStudentId(studentInfo.getUserId());
        message.setClassId(tClass.getClassId());
        message.setClassName(tClass.getClassName());
        message.setLessonName(tLessonInfo.getTitle());
        message.setChapterName(tLessonChapter.getTitle());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        message.setLessonTime(format.format(tClassSchedule.getLessonTime()));
        TMessage tMessage = new TMessage();
        tMessage.setuType(ApplicationConstant.USER_TYPE_STUDENT);
        tMessage.setmType(ApplicationConstant.MESSAGE_TYPE_LEAVE);
        tMessage.setCotent(JSON.toJSONString(message));
        tMessage.setAddTime(new Date());
        tMessage.setStatus(ApplicationConstant.MESSAGE_STATUS_NOT_READ);
        tMessage.setuId(studentInfo.getUserId());

        messageMapper.insert(tMessage);

        return tMessage.getmId() > 0;
    }

    /**
     * 处理课节列表, 过滤重复的课节记录, 选择最新的状态
     *
     * @param chapterInfos
     */
    private List<ChapterInfo> chapterFilter(List<ChapterInfo> chapterInfos, LessonInfo lessonInfo) {
        Map<Integer, ChapterInfo> mapper = new HashMap<>();

        int lessonType = ApplicationConstant.LESSON_TYPE_AUDITION;

        for (ChapterInfo chapterInfo : chapterInfos) {
            if (chapterInfo.getType() != 4) {
                lessonType = ApplicationConstant.LESSON_TYPE_FU_FEI;
            }

            ChapterInfo info = mapper.get(chapterInfo.getChapterId());
            if (null == info) {
                mapper.put(chapterInfo.getChapterId(), chapterInfo);
            } else {
                // 选取最新的一条记录
                if (chapterInfo.getStatus() != 0 && chapterInfo.getAddTime().after(info.getAddTime())) {
                    mapper.put(chapterInfo.getChapterId(), chapterInfo);
                }
            }
        }
        lessonInfo.setType(lessonType);
        List<ChapterInfo> infoList = new ArrayList<>();
        for (Integer key : mapper.keySet()) {
            infoList.add(mapper.get(key));
        }

        Collections.sort(infoList, new Comparator<ChapterInfo>() {
            @Override
            public int compare(ChapterInfo o1, ChapterInfo o2) {
                return Integer.compare(o1.getOrderNum(), o2.getOrderNum());
            }
        });

        return infoList;
    }

    /**
     * 类型转换
     *
     * @param tempLessonInfos
     * @return
     */
    private List<LessonInfo> tempInfo2LessonInfo(List<TempLessonInfo> tempLessonInfos) {
        List<LessonInfo> result = new ArrayList<>();
        LessonInfo lessonInfo = null;
        TTeacherInfoExample example = null;

        // 排序
        Collections.sort(tempLessonInfos, new Comparator<TempLessonInfo>() {
            @Override
            public int compare(TempLessonInfo o1, TempLessonInfo o2) {
                return o2.getBeginTime().compareTo(o1.getBeginTime());
            }
        });

        for (TempLessonInfo temp : tempLessonInfos) {
            example = new TTeacherInfoExample();
            example.createCriteria().andTeacherIdIn(Arrays.asList(Long.parseLong(temp.getMasterTeacherId()+""),
            		Long.parseLong(temp.getTeacherId()+""),
            		Long.parseLong(temp.getAssistantId()+""),
            		Long.parseLong(temp.getEteacherId()+"")));

            lessonInfo = new LessonInfo();
            assembleLessonInfo(lessonInfo, temp, tTeacherInfoMapper.selectByExample(example));
            result.add(lessonInfo);
        }
        return result;
    }

    private void assembleLessonInfo(LessonInfo lessonInfo, TempLessonInfo temp, List<TTeacherInfo> tTeacherInfos) {
        // 查询课程的 开始结束时间
        Date startTime = scheduleDAO.lessonStartTime(temp.getClassId(), temp.getLessonId());
        Date endTime = scheduleDAO.lessonEndTime(temp.getClassId(), temp.getLessonId());

        lessonInfo.setLessonId(temp.getLessonId());
        lessonInfo.setLessonName(temp.getLessonName());
        lessonInfo.setCoverUrl(temp.getCoverUrl());
        lessonInfo.setTimeQuantum(DateUtil.dateToString(startTime, "yyyy年MM月dd日")
                + " - " + DateUtil.dateToString(endTime, "yyyy年MM月dd日"));
        lessonInfo.setClassId(temp.getClassId());
        lessonInfo.setClassName(temp.getClassName());
        if (CollectionUtils.isNotEmpty(tTeacherInfos)) {
            Map<Integer, String> nameMapper = new HashMap<>();
            for (TTeacherInfo info : tTeacherInfos) {
                nameMapper.put(info.getTeacherId(), info.getName());
            }
            lessonInfo.setHeadTeacher(nameMapper.get(temp.getMasterTeacherId()));
            lessonInfo.setTeacher(nameMapper.get(temp.getTeacherId()));
            lessonInfo.setAssistant(nameMapper.get(temp.getAssistantId()));
            lessonInfo.setElectrified(nameMapper.get(temp.getEteacherId()));
        }
    }

    @Override
    public boolean applyRemediation(RemediationMessage message) {

        TLessonInfo tLessonInfo = lessonInfoMapper.selectByPrimaryKey(message.getLessonId());
        message.setLessonName(tLessonInfo.getTitle());

        TLessonChapter tLessonChapter = lessonChapterMapper.selectByPrimaryKey(message.getChapterId());
        message.setChapterName(tLessonChapter.getTitle());

        TMessage tMessage = new TMessage();
        tMessage.setStatus(ApplicationConstant.MESSAGE_STATUS_NOT_READ);
        tMessage.setAddTime(new Date());
        tMessage.setCotent(JSON.toJSONString(message));
        tMessage.setmType(message.getMessageType());
        tMessage.setuType(ApplicationConstant.USER_TYPE_STUDENT);
        tMessage.setuId(message.getStudentId());
        messageMapper.insert(tMessage);
        return tMessage.getmId() > 0;
    }
    @Override
    public List<ConsumeLog> comsumeLog(ConsumeExample example) {
        List<ConsumeLog> consumeLogs = scheduleDAO.consumeLogList(example);
        if (CollectionUtils.isNotEmpty(consumeLogs)) {
            for (ConsumeLog consumeLog : consumeLogs) {
                TypeItemUtils.buildStuTypeItemType(consumeLog);
                if (consumeLog.getStartTime() == null) {
                    continue;
                }
                String sTime = DateUtil.dateToString(consumeLog.getStartTime(), "yyyy-MM-dd hh:mm");
                String eTime = DateUtil.dateToString(consumeLog.getEndTime(), "hh:mm");
                consumeLog.setTimeStr(sTime + " - " + eTime);
            }
        }
        return consumeLogs;
    }

    @Override
    public int countComsumeLog(ConsumeExample example) {
        return scheduleDAO.countConsumeLogList(example);
    }
    @Override
    public PageModel<LessonInfoExt>  myStudyLessonList(ServiceParam param) {



    	Long studentId = param.getUserId();
        int pageNo = param.getPageNo();
        int limit = param.getLimit();
        int categoryId = param.getCategoryId();
        Long lessonId = param.getLessonId();
        String name = param.getName();
        List<TLessonInfo> lessonInfos = scheduleDAO.studentLessons(studentId,
                (pageNo - 1) * limit,
                limit,
                name,
                categoryId,lessonId);

        List<LessonInfoExt> lessonInfoList = lessonInfo2LessonInfoExt(lessonInfos, studentId);

        int total = scheduleDAO.totalStudentLessons(studentId, name, categoryId);       //学生课程总数

        return new PageModel<>(pageNo, limit, lessonInfoList, total);
    }



    @Override
    public BaseResp myStudyChapterList(ServiceParam param) {
        BaseResp resp = new BaseResp();
        Long studentId = param.getUserId();

        TLessonChapterExample lcExample = new TLessonChapterExample();
        lcExample.setOrderByClause("order_num asc");
        TLessonChapterExample.Criteria criteria = lcExample.createCriteria();
        criteria.andLessonIdEqualTo(param.getLessonId());
        List<TLessonChapter> chapterList = lessonChapterMapper.selectByExample(lcExample);

        List<Integer> lightIdList = lightChapterIds(param.getLessonId(), studentId);
        if (CollectionUtils.isNotEmpty(chapterList)) {
            List<TLessonChapterExt> cxList = new ArrayList<>(chapterList.size());
            for (TLessonChapter c : chapterList) {
                TLessonChapterExt tLessonChapterExt = new TLessonChapterExt().copyFrom(c);
                tLessonChapterExt.setLight(light(c.getChapterId(), lightIdList));
                cxList.add(tLessonChapterExt);
            }
            chapterList.addAll(cxList);
            resp.getData().put("chapterList",chapterList);
        }
        return resp;
    }



    private List<LessonInfoExt> lessonInfo2LessonInfoExt(List<TLessonInfo> lessonList, Long studentId) {
        List<LessonInfoExt> ls = new ArrayList<LessonInfoExt>();
        for (TLessonInfo l : lessonList) {
            LessonInfoExt le = new LessonInfoExt();
            le.setCoverUrl(l.getCoverUrl());
            le.setTimes(l.getTimes());
            le.setTitle(l.getTitle());
            le.setLessonId(l.getLessonId());
            le.setIntroduce(l.getIntroduce());
            le.setCategoryId(l.getCategoryId());

            TLessonChapterExample lcExample = new TLessonChapterExample();
            lcExample.setOrderByClause("order_num asc");
            lcExample.createCriteria().andLessonIdEqualTo(l.getLessonId());
            List<TLessonChapter> chapterList = lessonChapterMapper.selectByExample(lcExample);
            List<Integer> lightIdList = lightChapterIds(l.getLessonId(), studentId);

            if (CollectionUtils.isNotEmpty(chapterList)) {
                List<TLessonChapterExt> cxList = new ArrayList<>(chapterList.size());
                for (TLessonChapter c : chapterList) {
                    //TLessonChapterExt tLessonChapterExt = new TLessonChapterExt().copyFrom(c);
                    TLessonChapterExt tLessonChapterExt = new TLessonChapterExt();
                    tLessonChapterExt.setTitle(c.getTitle());
                    tLessonChapterExt.setLessonId(c.getLessonId());
                    tLessonChapterExt.setChapterId(c.getChapterId());
                    tLessonChapterExt.setIntroduce(c.getIntroduce());
                    tLessonChapterExt.setOrderNum(c.getOrderNum());
               //     BeanUtils.copyProperties(tLessonChapterExt,c);
                    tLessonChapterExt.setLight(light(c.getChapterId(), lightIdList));
                    cxList.add(tLessonChapterExt);
                }
                le.setChapterList(cxList);
            }
            ls.add(le);
        }
        return ls;
    }

    /**
     * 修改章节点亮状态
     *
     * @param chapterId
     * @param lightIdList
     * @return
     */
    private boolean light(Integer chapterId, List<Integer> lightIdList) {
        if (CollectionUtils.isEmpty(lightIdList) || chapterId == null) {
            return false;
        }

        for (Integer id : lightIdList) {
            if (chapterId.equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询学生电亮章节
     *
     * @param lessonId
     * @param studentId
     * @return
     */
    private List<Integer> lightChapterIds(Long lessonId, Long studentId) {
        List<Integer> ids = new ArrayList<>();
        TStudentLearnPathExample example = new TStudentLearnPathExample();
        example.createCriteria().andLessonIdEqualTo(lessonId).andStudentIdEqualTo(studentId);
        List<TStudentLearnPath> tStudentLearnPaths = studentLearnPathMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tStudentLearnPaths)) {
            return ids;
        }
        for (TStudentLearnPath tStudentLearnPath : tStudentLearnPaths) {
            ids.add(tStudentLearnPath.getChapterId());
        }
        return ids;
    }


}

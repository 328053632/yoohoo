package com.yoohoo.en.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yoohoo.en.bean.ScheduleInfo;
import com.yoohoo.en.bean.ServiceParam;
import com.yoohoo.en.bean.message.RemediationMessage;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.ext.*;
import com.yoohoo.en.model.PageModel;

public interface IStudentService
{
    /**
     * 获取学生课程表
     * @param dateMap
     * @return
     */
    Map<String,List<ScheduleInfo>> scheduleList(int studentId, Map<String, Date> dateMap);
    /**
     * 申请请假
     * @param scheduleId
     * @param studentInfo
     * @return
     */
    boolean applyLeave(Long scheduleId, TStudentInfo studentInfo);

    /**
     * 申请补课
     * @param message
     * @return
     */
    boolean applyRemediation(RemediationMessage message);

    /**
     * 查询消费记录
     * @return
     */
    List<ConsumeLog> comsumeLog(ConsumeExample example);

    /**
     * 查询记录总数
     * @return
     */
    int countComsumeLog(ConsumeExample example);

    /**
     * 查询学生在学课程列表
     * @param param
     * @return
     */
    PageModel<LessonInfoExt> myStudyLessonList(ServiceParam param);

    /**
     * 在学课程章节路径

     */

    BaseResp myStudyChapterList(ServiceParam param);


}

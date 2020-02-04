package com.yoohoo.en.dao.mapper;

import java.util.Date;
import java.util.List;

import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.ext.*;
import org.apache.ibatis.annotations.Param;

import com.yoohoo.en.TempLessonInfo;
import com.yoohoo.en.bean.ScheduleInfo;

public interface ScheduleDAO
{
    /**
     * 查询课程表
     *
     * @param studentId
     * @param stime
     * @param etime
     * @return
     */
    List<ScheduleInfo> queryList(
        @Param("studentId") int studentId,
        @Param("stime") Date stime,
        @Param("etime") Date etime);

    /**
     * 查看学生在线课程列表
     * @param studentId
     * @param start
     * @param limit
     * @return
     */
    List<Integer> studentLessonIds(
        @Param("studentId") int studentId,
        @Param("start") int start,
        @Param("limit") int limit);

    /**
     *
     * @param studentId
     * @param name
     * @param categoryId
     * @param start
     * @param limit
     * @return
     */
    List<TLessonInfo> studentLessons(@Param("studentId") Long studentId,
        @Param("start") int start,
        @Param("limit") int limit,
        @Param("name") String name,
        @Param("categoryId") int categoryId,
        @Param("lessonId") Long lessonId
                                     );

    /**
     * 学生课程总数
     * @param studentId
     * @return
     */
    int totalStudentLessons(@Param("studentId") Long studentId,
        @Param("name") String name,
        @Param("categoryId") int categoryId);

    /**
     * 查看课程信息列表
     * @param studentId
     * @param list
     * @return
     */
    List<TempLessonInfo> tempLessonInfos(
        @Param("studentId") int studentId,
        @Param("list") List<Integer> list);

    /**
     * 查询课节列表信息
     * @param lessonId
     * @param studentId
     * @return
     */
    List<TLessonChapterExt> chapterInfoList(
        @Param("lessonId") int lessonId,
        @Param("studentId") int studentId,
        @Param("classId") int classId);

    /**
     * 查询消费记录列表
     * @param studentId
     * @param type
     * @param stime
     * @param etime
     * @return
     */
    List<ConsumeLog> consumeLogList(ConsumeExample example);

    /**
     * 查询总记录数
     * @param studentId
     * @param type
     * @param stime
     * @param etime
     * @return
     */
    int countConsumeLogList( ConsumeExample example);

    /**
     * 查询老师每天的课程数
     * @param stime
     * @param etime
     * @param teacherId
     * @return
     */
    List<TeacherScheduleInfo> queryScheduleList(@Param("teacherType")String TeacherType,
        @Param("stime") Date stime,
        @Param("etime") Date etime,
        @Param("teacherId") int teacherId);

    /**
     * 查询某个班级课程的结束时间
     * @param classId
     * @param lessonId
     * @return
     */
    Date lessonEndTime(
        @Param("classId") int classId,
        @Param("lessonId") int lessonId
    );

    /**
     * 查询某个班级课程的开始时间
     * @param classId
     * @param lessonId
     * @return
     */
    Date lessonStartTime(
        @Param("classId") int classId,
        @Param("lessonId") int lessonId
    );
}

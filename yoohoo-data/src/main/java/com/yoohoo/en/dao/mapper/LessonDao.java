package com.yoohoo.en.dao.mapper;

import java.util.List;

import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.ext.TLessonChapterExt;
import org.apache.ibatis.annotations.Param;

import com.yoohoo.en.dao.model.TClass;
import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.ext.TeacherLessonPeriodInfo;

public interface LessonDao {

    List<TClass> queryStudentLesson(@Param("studentId") Integer studentId);

    List<TStudentLessonSchedule> querytryLessonChapters(@Param("studentId") Integer studentId);

    List<TeacherLessonPeriodInfo> queryTeacherLessonSchedule(
            @Param("teacherId") Integer teacherId,
            @Param("dateStr") String dateStr);

    List<TeacherLessonPeriodInfo> queryLessonChapterListNotClassSchedule(@Param("lessonId") Integer lessonId);

    List<TLessonChapterExt> queryLessonChapterLearnPath(@Param("studentId") Long studentId);

    List<TeacherLessonPeriodInfo> queryeTeacherLessonSchedule(@Param("teacherId")Integer teacherId,  @Param("dateStr") String dateStr);

    List<TeacherLessonPeriodInfo> querymasterTeacherLessonSchedule( @Param("teacherId") Integer teacherId,   @Param("dateStr") String dateStr);
}

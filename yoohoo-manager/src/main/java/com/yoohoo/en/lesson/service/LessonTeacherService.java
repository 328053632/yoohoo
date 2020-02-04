package com.yoohoo.en.lesson.service;

import com.yoohoo.en.dao.model.TLessonTeacher;
import com.yoohoo.en.dao.model.TLessonTeacherRelation;

import java.util.List;

/**
 * Created By LiWenLong On 2018/8/10 14:00
 * E-Mail:it_lwl@163.com
 */
public interface LessonTeacherService {
    void saveBatchTeacherSchedule(List<TLessonTeacherRelation> list);
    List<TLessonTeacherRelation> queryListByPositionType(Integer lessonId,Integer positionType);
    void saveBatchLessonTeacher(List<TLessonTeacher> list);
    List<TLessonTeacher> queryListByLessonId(Integer lessonId);
    List<TLessonTeacher> queryListByTeacherId(Long teacherId);
    public List<TLessonTeacher> queryHasFileListByLessonId(Integer lessonId);
    void updateLessonTeacher(TLessonTeacher tLessonTeacher);
	void deleteLessonTeacher(Long lessonId);
	void deleteTeacherLesson(Long teacherId);
}

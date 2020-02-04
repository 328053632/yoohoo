package com.yoohoo.en.web.lesson.service;

import com.yoohoo.en.dao.model.TLessonTeacher;

import java.util.List;

/**
 * Created By LiWenLong On 2018/8/10 14:00
 * E-Mail:it_lwl@163.com
 */
public interface LessonTeacherService {
    List<TLessonTeacher> queryListByLessonId(Integer lessonId);
    List<TLessonTeacher> queryListByTeacherId(Long teacherId);
    public List<TLessonTeacher> queryHasFileListByLessonId(Integer lessonId);
	void saveBatchLessonTeacher(List<TLessonTeacher> list, Integer userId);
}

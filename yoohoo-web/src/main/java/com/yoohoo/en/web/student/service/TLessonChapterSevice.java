package com.yoohoo.en.web.student.service;

import com.yoohoo.en.dao.model.TLessonChapter;

import java.util.List;

/**
 * Created By LiWenLong On 2018/11/22 10:08
 * E-Mail:it_lwl@163.com
 */
public interface TLessonChapterSevice {
    List<TLessonChapter> getChapterListByLessonId(String lessonId);
}

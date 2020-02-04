package com.yoohoo.en.dao.model.ext;

import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TTeacherInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;


import java.io.Serializable;
import java.util.List;

/**
 * Created By LiWenLong On 2018/11/8 11:44
 * E-Mail:it_lwl@163.com
 */
public class LessonDetailPack   implements Serializable {

    //课程章节列表
    List<TLessonChapter> lessonChapterList;


    //课程关联老师列表
    List<TTeacherInfo> lessonTeacherList;

    //课程关联课长列表
    List<TTeacherInfo> lessonMasterTeacherList;




    public List<TTeacherInfo> getLessonMasterTeacherList() {
        return lessonMasterTeacherList;
    }

    public void setLessonMasterTeacherList(List<TTeacherInfo> lessonMasterTeacherList) {
        this.lessonMasterTeacherList = lessonMasterTeacherList;
    }

    public List<TTeacherInfo> getLessonTeacherList() {
        return lessonTeacherList;
    }

    public void setLessonTeacherList(List<TTeacherInfo> lessonTeacherList) {
        this.lessonTeacherList = lessonTeacherList;
    }

    public List<TLessonChapter> getLessonChapterList() {
        return lessonChapterList;
    }

    public void setLessonChapterList(List<TLessonChapter> lessonChapterList) {
        this.lessonChapterList = lessonChapterList;
    }
}

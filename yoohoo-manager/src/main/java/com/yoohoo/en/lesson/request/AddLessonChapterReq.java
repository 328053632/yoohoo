package com.yoohoo.en.lesson.request;

import com.yoohoo.en.dao.model.TLessonChapter;

import java.io.Serializable;
import java.util.List;

public class AddLessonChapterReq implements Serializable
{
    private static final long serialVersionUID = -3685440716718812044L;

    private int lessonId;

    private List<TLessonChapter> chapters;

    private List<Integer> delChapterIds;

    public int getLessonId()
    {
        return lessonId;
    }

    public void setLessonId(int lessonId)
    {
        this.lessonId = lessonId;
    }

    public List<TLessonChapter> getChapters()
    {
        return chapters;
    }

    public void setChapters(List<TLessonChapter> chapters)
    {
        this.chapters = chapters;
    }

    public List<Integer> getDelChapterIds()
    {
        return delChapterIds;
    }

    public void setDelChapterIds(List<Integer> delChapterIds)
    {
        this.delChapterIds = delChapterIds;
    }
}

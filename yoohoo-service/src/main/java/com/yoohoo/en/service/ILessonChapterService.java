package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;

import java.util.List;

public interface ILessonChapterService
{
    /**
     * 插入单条记录
     * @param chapter
     * @return 插入id
     */
    int insert(TLessonChapter chapter);

    /**
     * 插入批量数据
     * @param chapters
     * @return 操作成功数
     */
    int inserBatch(List<TLessonChapter> chapters);

    /**
     * 更新
     * @param chapter
     * @return
     */
    int update(TLessonChapter chapter);

    /**
     * 批量更新
     * @param chapters
     * @return
     */
    int updateBatch(List<TLessonChapter> chapters);

    /**
     * 查询列表
     * @param example
     * @return
     */
    List<TLessonChapter> query(TLessonChapterExample example);

    /**
     * 根据条件删除记录
     * @param example
     * @return
     */
    int delete(TLessonChapterExample example);

    /**
     * 统计课程下的课节数
     * @param lessonId
     * @return
     */
    int countChapter(Long lessonId);

}

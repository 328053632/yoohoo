package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLessonChapterMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TLessonChapterExample example);

    int deleteByExample(TLessonChapterExample example);

    int deleteByPrimaryKey(Integer chapterId);

    int insert(TLessonChapter record);

    int insertSelective(TLessonChapter record);

    List<TLessonChapter> selectByExample(TLessonChapterExample example);

    TLessonChapter selectByPrimaryKey(Integer chapterId);

    int updateByExampleSelective(@Param("record") TLessonChapter record, @Param("example") TLessonChapterExample example);

    int updateByExample(@Param("record") TLessonChapter record, @Param("example") TLessonChapterExample example);

    int updateByPrimaryKeySelective(TLessonChapter record);

    int updateByPrimaryKey(TLessonChapter record);

    int insertBatch(List<TLessonChapter> records);
}
package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLibraryChapter;
import com.yoohoo.en.dao.model.TLibraryChapterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TLibraryChapterMapper {
    int countByExample(TLibraryChapterExample example);

    int deleteByExample(TLibraryChapterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TLibraryChapter record);

    int insertSelective(TLibraryChapter record);

    List<TLibraryChapter> selectByExample(TLibraryChapterExample example);

    TLibraryChapter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TLibraryChapter record, @Param("example") TLibraryChapterExample example);

    int updateByExample(@Param("record") TLibraryChapter record, @Param("example") TLibraryChapterExample example);

    int updateByPrimaryKeySelective(TLibraryChapter record);

    int updateByPrimaryKey(TLibraryChapter record);

    void insertBatch(List<TLibraryChapter> addList);
}
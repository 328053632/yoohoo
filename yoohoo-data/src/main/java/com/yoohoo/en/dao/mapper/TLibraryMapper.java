package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLibraryMapper {
    int countByExample(TLibraryExample example);

    int deleteByExample(TLibraryExample example);

    int deleteByPrimaryKey(Integer fileId);

    int insert(TLibrary record);

    int insertSelective(TLibrary record);

    List<TLibrary> selectByExample(TLibraryExample example);

    TLibrary selectByPrimaryKey(Integer fileId);

    int updateByExampleSelective(@Param("record") TLibrary record, @Param("example") TLibraryExample example);

    int updateByExample(@Param("record") TLibrary record, @Param("example") TLibraryExample example);

    int updateByPrimaryKeySelective(TLibrary record);

    int updateByPrimaryKey(TLibrary record);

    List<TLibrary> queryAllFileList();
}
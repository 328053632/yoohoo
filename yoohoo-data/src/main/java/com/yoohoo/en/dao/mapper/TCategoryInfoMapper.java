package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TCategoryInfo;
import com.yoohoo.en.dao.model.TCategoryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCategoryInfoMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TCategoryInfoExample example);

    int deleteByExample(TCategoryInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCategoryInfo record);

    int insertSelective(TCategoryInfo record);

    List<TCategoryInfo> selectByExample(TCategoryInfoExample example);

    TCategoryInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCategoryInfo record, @Param("example") TCategoryInfoExample example);

    int updateByExample(@Param("record") TCategoryInfo record, @Param("example") TCategoryInfoExample example);

    int updateByPrimaryKeySelective(TCategoryInfo record);

    int updateByPrimaryKey(TCategoryInfo record);

    int insertBatch(List<TCategoryInfo> records);
}
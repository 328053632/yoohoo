package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMessageMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TMessageExample example);

    int deleteByExample(TMessageExample example);

    int deleteByPrimaryKey(Integer mId);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    List<TMessage> selectByExampleWithBLOBs(TMessageExample example);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(Integer mId);

    int updateByExampleSelective(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByExample(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKeyWithBLOBs(TMessage record);

    int updateByPrimaryKey(TMessage record);

    int insertBatch(List<TMessage> records);
}
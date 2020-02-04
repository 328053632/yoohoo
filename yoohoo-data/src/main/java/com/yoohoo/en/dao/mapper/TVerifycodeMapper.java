package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TVerifycode;
import com.yoohoo.en.dao.model.TVerifycodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TVerifycodeMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TVerifycodeExample example);

    int deleteByExample(TVerifycodeExample example);

    int insert(TVerifycode record);

    int insertSelective(TVerifycode record);

    List<TVerifycode> selectByExample(TVerifycodeExample example);

    int updateByExampleSelective(@Param("record") TVerifycode record, @Param("example") TVerifycodeExample example);

    int updateByExample(@Param("record") TVerifycode record, @Param("example") TVerifycodeExample example);

    int insertBatch(List<TVerifycode> records);
}
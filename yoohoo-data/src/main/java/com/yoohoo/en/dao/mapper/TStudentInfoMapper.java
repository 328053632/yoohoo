package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.common.mapper.MyMapper;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TStudentInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TStudentInfoMapper extends MyMapper<TStudentInfo> {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExampleXml(TStudentInfoExample example);

    int deleteByExampleXml(TStudentInfoExample example);

    int deleteByPrimaryKeyXml(Integer userId);

    int insertSelectiveXml(TStudentInfo record);

    List<TStudentInfo> selectByExampleXml(TStudentInfoExample example);

    TStudentInfo selectByPrimaryKeyXml(Long userId);

    int updateByExampleSelectiveXml(@Param("record") TStudentInfo record, @Param("example") TStudentInfoExample example);

    int updateByExampleXml(@Param("record") TStudentInfo record, @Param("example") TStudentInfoExample example);

    int updateByPrimaryKeySelectiveXml(TStudentInfo record);

    int updateByPrimaryKeyXml(TStudentInfo record);

    int insertBatchXml(List<TStudentInfo> records);
}
package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TTalkcloudEvent;
import com.yoohoo.en.dao.model.TTalkcloudEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTalkcloudEventMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TTalkcloudEventExample example);

    int deleteByExample(TTalkcloudEventExample example);

    int deleteByPrimaryKey(Integer eventId);

    int insert(TTalkcloudEvent record);

    int insertSelective(TTalkcloudEvent record);

    List<TTalkcloudEvent> selectByExample(TTalkcloudEventExample example);

    TTalkcloudEvent selectByPrimaryKey(Integer eventId);

    int updateByExampleSelective(@Param("record") TTalkcloudEvent record, @Param("example") TTalkcloudEventExample example);

    int updateByExample(@Param("record") TTalkcloudEvent record, @Param("example") TTalkcloudEventExample example);

    int updateByPrimaryKeySelective(TTalkcloudEvent record);

    int updateByPrimaryKey(TTalkcloudEvent record);

    int insertBatch(List<TTalkcloudEvent> records);
}
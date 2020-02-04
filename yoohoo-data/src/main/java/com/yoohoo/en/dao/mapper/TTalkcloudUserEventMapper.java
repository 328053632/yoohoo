package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TTalkcloudUserEvent;
import com.yoohoo.en.dao.model.TTalkcloudUserEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTalkcloudUserEventMapper {
    public static final String DATA_SOURCE_NAME = "null";

    int countByExample(TTalkcloudUserEventExample example);

    int deleteByExample(TTalkcloudUserEventExample example);

    int deleteByPrimaryKey(Integer eventId);

    int insert(TTalkcloudUserEvent record);

    int insertSelective(TTalkcloudUserEvent record);

    List<TTalkcloudUserEvent> selectByExample(TTalkcloudUserEventExample example);

    TTalkcloudUserEvent selectByPrimaryKey(Integer eventId);

    int updateByExampleSelective(@Param("record") TTalkcloudUserEvent record, @Param("example") TTalkcloudUserEventExample example);

    int updateByExample(@Param("record") TTalkcloudUserEvent record, @Param("example") TTalkcloudUserEventExample example);

    int updateByPrimaryKeySelective(TTalkcloudUserEvent record);

    int updateByPrimaryKey(TTalkcloudUserEvent record);

    int insertBatch(List<TTalkcloudUserEvent> records);
}
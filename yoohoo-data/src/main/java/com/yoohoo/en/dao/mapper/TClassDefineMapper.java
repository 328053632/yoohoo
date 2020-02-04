package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.TClassDefineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassDefineMapper {
    int countByExample(TClassDefineExample example);

    int deleteByExample(TClassDefineExample example);

    int deleteByPrimaryKey(Integer classItemId);

    int insert(TClassDefine record);

    int insertSelective(TClassDefine record);

    List<TClassDefine> selectByExample(TClassDefineExample example);

    TClassDefine selectByPrimaryKey(Integer classItemId);

    int updateByExampleSelective(@Param("record") TClassDefine record, @Param("example") TClassDefineExample example);

    int updateByExample(@Param("record") TClassDefine record, @Param("example") TClassDefineExample example);

    int updateByPrimaryKeySelective(TClassDefine record);

    int updateByPrimaryKey(TClassDefine record);

    int insertBatch(List<TClassDefine> records);
	}
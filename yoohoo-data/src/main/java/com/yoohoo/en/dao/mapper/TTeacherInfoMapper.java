package com.yoohoo.en.dao.mapper;

import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherInfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TTeacherInfoMapper {
	public static final String DATA_SOURCE_NAME = "null";
	 
    int countByExample(TTeacherInfoExample example);

    int deleteByExample(TTeacherInfoExample example);

    int deleteByPrimaryKey(Integer teacherId);

    int insert(TTeacherInfo record);
    
    int insertSelective(TTeacherInfo record);

    List<TTeacherInfo> selectByExample(TTeacherInfoExample example);

    TTeacherInfo selectByPrimaryKey(Integer teacherId);

    int updateByExampleSelective(@Param("record") TTeacherInfo record, @Param("example") TTeacherInfoExample example);

    int updateByExample(@Param("record") TTeacherInfo record, @Param("example") TTeacherInfoExample example);

    int updateByPrimaryKeySelective(TTeacherInfo record);

    int updateByPrimaryKey(TTeacherInfo record);
	
	int insertBatch(List<TTeacherInfo> records);

    String selectNameById(Integer assistantId);

   TTeacherInfo selectTeacherByPrimaryKey(Integer teacherId);

   List<Long> queryIdList(Map<String, Object> map);
}
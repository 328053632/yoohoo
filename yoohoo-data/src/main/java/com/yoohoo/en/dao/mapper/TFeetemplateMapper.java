package com.yoohoo.en.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yoohoo.en.common.mapper.MyMapper;
import com.yoohoo.en.dao.model.TFeetemplate;

@Mapper
@Repository
public interface TFeetemplateMapper extends MyMapper<TFeetemplate> {
	
}
package com.yoohoo.en.web.teacher.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TTeacherPositionRelationMapper;
import com.yoohoo.en.dao.model.TTeacherPositionRelation;
import com.yoohoo.en.dao.model.TTeacherPositionRelationExample;
import com.yoohoo.en.web.teacher.service.TTeacherPositionRelationService;

/**
 * Created By LiWenLong On 2018/9/14 17:43
 * E-Mail:it_lwl@163.com
 */
@Service
public class TTeacherPositionRelationServiceImpl implements TTeacherPositionRelationService {

    @Autowired
    TTeacherPositionRelationMapper tTeacherPositionRelationMapper;


    @Override
    public TTeacherPositionRelation selectTeacherByIdAndType(Integer teacherId, String teacherType) {

        TTeacherPositionRelationExample example=new TTeacherPositionRelationExample();

        example.createCriteria().andStatusEqualTo(1).andTeacherIdEqualTo(teacherId).andPositionTypeEqualTo(Integer.parseInt(teacherType));

        List<TTeacherPositionRelation> list = tTeacherPositionRelationMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }
}

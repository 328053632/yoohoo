package com.yoohoo.en.web.student.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TLessonTeacherRelationMapper;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.model.TLessonTeacherRelation;
import com.yoohoo.en.dao.model.TLessonTeacherRelationExample;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.web.student.service.TLessonTeacherRelationService;

/**
 * Created By LiWenLong On 2018/11/8 11:59
 * E-Mail:it_lwl@163.com
 */

@Service
public class TLessonTeacherRelationServiceImpl implements TLessonTeacherRelationService {


    @Autowired
    TLessonTeacherRelationMapper tLessonTeacherRelationMapper;

    @Autowired
    TTeacherInfoMapper tTeacherInfoMapper;


    @Override
   /* public  Map<Integer,  List< List<Map<String, String>>>> queryLessonTeacher(int lessonId) {
        TLessonTeacherRelationExample example=new TLessonTeacherRelationExample();
        example.createCriteria().andPositionTypeBetween(1,2).andLessonIdEqualTo(lessonId);
        List<TLessonTeacherRelation> tLessonTeacherRelationList = tLessonTeacherRelationMapper.selectByExample(example);
        //  Map<Integer,List<TTeacherInfo>> teacherMap=new HashMap<>();

        // List<TTeacherInfo> teacherList=new ArrayList<>();
        //   List<TTeacherInfo> masterList=new ArrayList<>();
        Map<Integer,  List< List<Map<String, String>>>> teacherMap=new HashMap<>();
        List< List<Map<String, String>>> teacherList=new ArrayList<>();
        List< List<Map<String, String>>> masterList=new ArrayList<>();
        if(null != tLessonTeacherRelationList && tLessonTeacherRelationList.size()>0){
            for(TLessonTeacherRelation t:tLessonTeacherRelationList){
                if(t.getPositionType()==1){
                    //上课老师
                    // teacherList.add(tTeacherInfoMapper.selectByPrimaryKey(t.getTeacherId()));
                    List<Map<String, String>> maps = tTeacherInfoMapper.selectTeacherByPrimaryKey(t.getTeacherId());
                    teacherList.add(maps);
                }
                if(t.getPositionType()==2){
                    //课长
                    /// masterList.add(tTeacherInfoMapper.selectByPrimaryKey(t.getTeacherId()));
                    List<Map<String, String>> maps = tTeacherInfoMapper.selectTeacherByPrimaryKey(t.getTeacherId());
                    masterList.add(maps);
                }
            }
            teacherMap.put(1,teacherList);
            teacherMap.put(2,masterList);
            return teacherMap;
        }
        return null;
    }*/


   
    public   Map<Integer,List<TTeacherInfo>> queryLessonTeacher(int lessonId) {
         TLessonTeacherRelationExample example=new TLessonTeacherRelationExample();
         example.createCriteria().andStatusEqualTo(1).andPositionTypeBetween(1,2).andLessonIdEqualTo(lessonId);
         List<TLessonTeacherRelation> tLessonTeacherRelationList = tLessonTeacherRelationMapper.selectByExample(example);
         Map<Integer,List<TTeacherInfo>> teacherMap=new HashMap<>();
         List<TTeacherInfo> teacherList=new ArrayList<>();
         List<TTeacherInfo> masterList=new ArrayList<>();
        if(null != tLessonTeacherRelationList && tLessonTeacherRelationList.size()>0){
            for(TLessonTeacherRelation t:tLessonTeacherRelationList){
                if(t.getPositionType()==1){
                    //上课老师
                     teacherList.add(tTeacherInfoMapper.selectTeacherByPrimaryKey(t.getTeacherId()));
                }
                if(t.getPositionType()==2){
                    //课长
                     masterList.add(tTeacherInfoMapper.selectTeacherByPrimaryKey(t.getTeacherId()));
                }
            }
            teacherMap.put(1,teacherList);
            teacherMap.put(2,masterList);
            return teacherMap;
        }
        return null;
    }
}

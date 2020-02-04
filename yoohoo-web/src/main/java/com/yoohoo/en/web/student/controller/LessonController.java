package com.yoohoo.en.web.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.ext.LessonDetailPack;
import com.yoohoo.en.service.ILessonChapterService;
import com.yoohoo.en.service.ILessonInfoService;
import com.yoohoo.en.web.spring.ConfigProper;
import com.yoohoo.en.web.student.service.TLessonTeacherRelationService;

@RestController
@RequestMapping(value = "/stu/lesson")
public class LessonController
{

    @Autowired
    private ILessonInfoService lessonInfoService;

    @Autowired
    private ILessonChapterService lessonChapterService;

    @Autowired
    private ConfigProper configProper;

    @Autowired
    private TLessonTeacherRelationService tLessonTeacherRelationService;

    /**
     * 获取教材详情
     *
     * @param lessonId
     * @return
     */
    @RequestMapping(value = "/detail/{lessonId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResp detail(@PathVariable("lessonId") Integer lessonId){
        TLessonInfo tLessonInfo = lessonInfoService.queryById(lessonId.longValue());
        int count = lessonChapterService.countChapter(lessonId.longValue());
        BaseResp resp = new BaseResp();
        resp.put("lessonInfo", tLessonInfo);
        resp.put("prefix", configProper.getCoverUrlPrefix());
        resp.put("count", count);
        return resp;
    }

    /**
     *
     * 根据课程id查询该课程的所有相关信息
     */
    @RequestMapping(value = "/chapterList/{lessonId}")
    @ResponseBody
    public BaseResp chapterList(@PathVariable("lessonId") Integer lessonId,Integer type){
        BaseResp resp=new BaseResp();

        LessonDetailPack lessonDetailPack=new LessonDetailPack();
        //根据lessonId获取当前课程的所有章节即可
        TLessonChapterExample example=new TLessonChapterExample();
        example.createCriteria().andLessonIdEqualTo(lessonId.longValue());
        if(null!=type && type==1){
            List<TLessonChapter>  chapterList=lessonChapterService.query(example);
            lessonDetailPack.setLessonChapterList(chapterList);
        }
        //根据LessonId获取当前课所关联的老师
        Map<Integer,List<TTeacherInfo>>  teacherInfoMap=  tLessonTeacherRelationService.queryLessonTeacher(lessonId);
        if(teacherInfoMap != null){
            List<TTeacherInfo> tTeacherList = teacherInfoMap.get(1);
            List<TTeacherInfo> tMasterTeacherList = teacherInfoMap.get(2);
            lessonDetailPack.setLessonTeacherList(tTeacherList);
            lessonDetailPack.setLessonMasterTeacherList(tMasterTeacherList);
        }
        resp.put("lessonDetailPack",lessonDetailPack);
        return resp;
    }


}

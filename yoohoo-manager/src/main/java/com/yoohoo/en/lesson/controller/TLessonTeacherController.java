package com.yoohoo.en.lesson.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.yoohoo.en.dao.model.TLessonTeacher;
import com.yoohoo.en.lesson.service.LessonTeacherService;
import com.yoohoo.en.mcore.utils.R;

/**
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-02 14:05:49
 */
@Controller
@RequestMapping("tlessonTeacher")
public class TLessonTeacherController
{


    @Autowired
    private LessonTeacherService lessonTeacherRefService;
    /**
     * 保存或修改老师关联课本信息
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody List<TLessonTeacher> list, HttpServletRequest request)
    {
    	if(CollectionUtils.isNotEmpty(list)){
    		lessonTeacherRefService.deleteTeacherLesson(list.get(0).getTeacherId());
        	lessonTeacherRefService.saveBatchLessonTeacher(list);
    	}
        R r=new R();
        r.put("code",0);
        return r;
    }
    
    /**
     * 课本关联的老师
     */
    @ResponseBody
    @RequestMapping("/lessonTeacherList/{lessonId}/{ifHasSoursewareLimit}")
    @RequiresPermissions("tlessoninfo:info")
    public R lessonTeacherList(@PathVariable("lessonId") Long lessonId, @PathVariable("ifHasSoursewareLimit") Integer ifHasSoursewareLimit)
    {
    	if(ifHasSoursewareLimit == 1){
            return R.ok().put("tLessonTeacherList", lessonTeacherRefService.queryHasFileListByLessonId(lessonId.intValue()));
    	}
        return R.ok().put("tLessonTeacherList", lessonTeacherRefService.queryListByLessonId(lessonId.intValue()));
    }
    
    /**
     * 老师关联的课本
     */
    @ResponseBody
    @RequestMapping("/teacherLessonList/{teacherId}")
    @RequiresPermissions("tlessoninfo:info")
    public R teacherLessonList(@PathVariable("teacherId") Long teacherId)
    {
        return R.ok().put("tTeacherLessonList", lessonTeacherRefService.queryListByTeacherId(teacherId));
    }
}

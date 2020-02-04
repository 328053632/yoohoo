package com.yoohoo.en.web.lesson.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.TLessonTeacher;
import com.yoohoo.en.dao.model.TLibraryChapter;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.service.ILessonChapterService;
import com.yoohoo.en.utils.SessionUtil;
import com.yoohoo.en.web.lesson.request.AddLessonChapterReq;
import com.yoohoo.en.web.lesson.service.LessonTeacherService;
import com.yoohoo.en.web.lesson.service.TLibraryChapterService;
import com.yoohoo.en.web.library.service.TLibraryService;
import com.yoohoo.en.web.reponse.R;

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

    @Autowired
    private ILessonChapterService lessonChapterService;

    @Autowired
    private TLibraryChapterService libraryChapterService;

    @Autowired
    private TLibraryService libraryService;
    
    /**
     * 保存老师关联课本信息
     */
    @ResponseBody
    @RequestMapping("/save")
    public R save(@RequestBody List<TLessonTeacher> list, HttpServletRequest request, HttpSession session)
    {
    	lessonTeacherRefService.saveBatchLessonTeacher(list, SessionUtil.INSTANCE.getLoginTeacher(session).getTeacherId());
        R r=new R();
        r.put("code",0);
        return r;
    }
    
    /**
     * 课本关联的老师
     */
    @ResponseBody
    @RequestMapping("/lessonTeacherList/{lessonId}/{ifHasSoursewareLimit}")
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
    public R teacherLessonList(@PathVariable("teacherId") Long teacherId)
    {
        return R.ok().put("tTeacherLessonList", lessonTeacherRefService.queryListByTeacherId(teacherId));
    }


    /**
     *
     * 课程库
     *
     * 上传文件,文件关联
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public R uploadFile(HttpServletRequest request, HttpSession session){
    	TTeacherInfo user = SessionUtil.INSTANCE.getLoginTeacher(session);
        MultipartHttpServletRequest multipartRequest= (MultipartHttpServletRequest) request;
        List<MultipartFile> file = multipartRequest.getFiles("file");
        /**
         *
         * 章节Id
         */
        String chapterId = request.getParameter("chapterId");
        /**
         *
         * 文件类型
         */
        String fileType = request.getParameter("fileType");
        List<Integer> fileId = libraryService.saveMultipartFile(file, fileType, user.getName());
        List<TLibraryChapter> libraryChapterList=new ArrayList<>();

        if(fileId!=null){
            for(Integer id:fileId)
            {
                TLibraryChapter tLibraryChapter=new TLibraryChapter();
                tLibraryChapter.setFileId(id);
                tLibraryChapter.setStatus(1);
                tLibraryChapter.setChapterId(Integer.parseInt(chapterId));
                tLibraryChapter.setFileType(Integer.parseInt(fileType));
                tLibraryChapter.setTeacherId(user.getTeacherId());
                libraryChapterList.add(tLibraryChapter);
            }
            libraryChapterService.saveBatchFile(libraryChapterList);
        }else{
            return R.error(500,"上传文件失败");
        }
        return R.ok();
    }


    /**
     *
     * 删除关联文件
     */

    @RequestMapping("/deleteChapterFile")
    @ResponseBody
    public R deleteChapterFile(Integer id){


        Integer integer = libraryChapterService.deleteById(id);
        if(integer==0){
            return R.ok();
        }else{
            return R.error(500,"删除课件失败");
        }

    }

    @ResponseBody
    @RequestMapping("/addLessonChapter")
    public R addLessonChapter(@RequestBody AddLessonChapterReq req, HttpSession session)
    {
    	Long userId = Long.parseLong(SessionUtil.INSTANCE.getLoginTeacher(session).getTeacherId().toString());
        List<TLessonChapter> chapters = req.getChapters();
        if (CollectionUtils.isEmpty(chapters))
        {
            return R.ok();
        }

        List<TLessonChapter> insertList = new ArrayList<>();
        List<TLessonChapter> updateList = new ArrayList<>();

        for (TLessonChapter chapter : chapters)
        {
            chapter.setLessonId(req.getLessonId());
            if (chapter.getChapterId() == null)
            {
                insertList.add(chapter);
            }
            else
            {	chapter.setAddBaseInfo(userId);
                updateList.add(chapter);
            }
        }
        lessonChapterService.inserBatch(insertList);
        lessonChapterService.updateBatch(updateList);

        if (CollectionUtils.isNotEmpty(req.getDelChapterIds()))
        {
            TLessonChapterExample example = new TLessonChapterExample();
            example.createCriteria().andChapterIdIn(req.getDelChapterIds());
            lessonChapterService.delete(example);
        }
        return R.ok();
    }
}

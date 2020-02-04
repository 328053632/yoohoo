package com.yoohoo.en.lesson.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;
import com.yoohoo.en.dao.model.TLessonTeacher;
import com.yoohoo.en.dao.model.TLessonInfoExample.Criteria;
import com.yoohoo.en.dao.model.TLessonTeacherRelation;
import com.yoohoo.en.dao.model.TLibraryChapter;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.lesson.request.AddLessonChapterReq;
import com.yoohoo.en.lesson.service.LessonTeacherService;
import com.yoohoo.en.lesson.service.TLibraryChapterService;
import com.yoohoo.en.library.service.TLibraryService;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.service.ILessonChapterService;
import com.yoohoo.en.service.ILessonInfoService;
import com.yoohoo.en.utils.FileUtil;

/**
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-02 14:05:49
 */
@Controller
@RequestMapping("tlessoninfo")
public class TLessonInfoController
{

    @Autowired
    private ILessonInfoService lessonInfoService;

    @Autowired
    private ILessonChapterService lessonChapterService;

    @Autowired
    private LessonTeacherService lessonTeacherService;

    @Autowired
    private TLibraryChapterService libraryChapterService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

    @Autowired
    private TLibraryService libraryService;
    @RequestMapping("/tlessoninfo.html")
    public String list()
    {
        return "lesson/tlessoninfo.html";
    }
    

    @Autowired
    private LessonTeacherService lessonTeacherRefService;
    /**
     * 所有课程列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("tlessoninfo:list")
    public R list(Integer page, Integer limit,String title)
    {
        TLessonInfoExample example = new TLessonInfoExample();
        example.setPageHelper(new PageHelper((page - 1) * limit, limit));
        Criteria criteria = example.createCriteria();
        if(title!=null && !"".equals(title)){
        	criteria.andTitleLike("%"+title+"%");
        }
//        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
//        	criteria.andUserIdEquals(ShiroUtils.getUserId()); 
        List<TLessonInfo> tLessonInfos = lessonInfoService.queryList(example);
        
        lessonTeacherRefService.queryListByLessonId(tLessonInfos.get(0).getLessonId().intValue());
        
        int total = lessonInfoService.count(example);
        PageUtils pageEntity = new PageUtils(tLessonInfos, total, limit, page);
        return R.ok().put("page", pageEntity);
    }
    
    /**
     * 所有可关联课本列表
     */
    @ResponseBody
    @RequestMapping("/relationToList")
    @RequiresPermissions("tlessoninfo:list")
    public R relationToList()
    {
    	Long userId = ShiroUtils.getUserId();
    	List<TLessonTeacher> tltList = lessonTeacherRefService.queryListByTeacherId(userId);
    	List<Integer> lessonIdList = tltList.stream().filter(e -> e.getLessonId() != null).map(TLessonTeacher::getLessonId).distinct().collect(Collectors.toList());
        TLessonInfoExample example = new TLessonInfoExample();
        Criteria criteria = example.createCriteria();
       	criteria.andLessonIdIn(lessonIdList); 
        List<TLessonInfo> tLessonInfos = lessonInfoService.queryList(example);
        return R.ok().put("list", tLessonInfos);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{lessonId}")
    @RequiresPermissions("tlessoninfo:info")
    public R info(@PathVariable("lessonId") Long lessonId)
    {
    	TLessonInfo tLessonInfo = lessonInfoService.queryById(lessonId);
    	tLessonInfo.setTeachers(lessonTeacherRefService.queryListByLessonId(lessonId.intValue()));
        return R.ok().put("tLessonInfo", tLessonInfo);
    }
    
    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("tlessoninfo:save")
    public R save(@RequestBody TLessonInfo lessonInfo, HttpServletRequest request) throws FastDFSException {

    	String msg=lessonInfoService.checkLessonInfo(lessonInfo);
    	if(StringUtils.isNotEmpty(msg))
    	{
    		return R.error(msg);
    	}
    	lessonInfo.setStatus(1);
    	lessonInfo.setAddBaseInfo(ShiroUtils.getUserId());
        Long lessonId = lessonInfoService.insert(lessonInfo);
        List<TLessonTeacher> teachers = lessonInfo.getTeachers();
        if(CollectionUtils.isNotEmpty(teachers)){
        	for(TLessonTeacher teacher:teachers){
        		teacher.setLessonId(lessonInfo.getLessonId().intValue());
        	}
        	lessonTeacherRefService.saveBatchLessonTeacher(teachers);
        }
        R r=new R();
        r.put("code",0);
        r.put("lessonId",lessonId);
        return r;
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("tlessoninfo:update")
    public R update(@RequestBody TLessonInfo lessonInfo, HttpServletRequest request) throws FastDFSException {
    	String msg=lessonInfoService.checkLessonInfo(lessonInfo);
    	if(StringUtils.isNotEmpty(msg))
    	{
    		return R.error(msg);
    	}
    	lessonInfo.setModifyBaseInfo(ShiroUtils.getUserId());
        lessonInfoService.update(lessonInfo);

        List<TLessonTeacher> teachers = lessonInfo.getTeachers();
        if(CollectionUtils.isNotEmpty(teachers)){
        	lessonTeacherRefService.deleteLessonTeacher(lessonInfo.getLessonId());
        	for(TLessonTeacher teacher:teachers){
        		teacher.setLessonId(lessonInfo.getLessonId().intValue());
        	}
        	lessonTeacherRefService.saveBatchLessonTeacher(teachers);
        }
        return R.ok();
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("tlessoninfo:delete")
    public R delete(@RequestBody Integer[] lessonIds)
    {
        lessonInfoService.deleteBatch(Arrays.asList(lessonIds));
        return R.ok();
    }


    @ResponseBody
    @RequestMapping("/chapterList/{lessonId}")
    public R chapterList(@PathVariable("lessonId") Long lessonId)
    {
        TLessonChapterExample example = new TLessonChapterExample();
        example.createCriteria().andLessonIdEqualTo(lessonId);
        example.setOrderByClause("order_num ASC");
        List<TLessonChapter> chapters = lessonChapterService.query(example);
        return R.ok().put("data", chapters);
    }


    @ResponseBody
    @RequestMapping("/chapterList/{lessonId}/{teacherId}")
    public R chapterList(@PathVariable("lessonId") Long lessonId, @PathVariable("teacherId") Long teacherId)
    {
        TLessonChapterExample example = new TLessonChapterExample();
        TLessonChapterExample.Criteria criteria = example.createCriteria();
        criteria.andLessonIdEqualTo(lessonId);
        criteria.andUserIdEqualTo(teacherId);
        example.setOrderByClause("order_num ASC");
        List<TLessonChapter> chapters = lessonChapterService.query(example);
        return R.ok().put("data", chapters);
    }


    @ResponseBody
    @RequestMapping("/addLessonChapter")
    public R addLessonChapter(@RequestBody AddLessonChapterReq req)
    {
    	Long userId = ShiroUtils.getUserId();
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

    @ResponseBody
    @RequestMapping("/saveBatchTeacher")
    public R saveBatchTeacher(@RequestBody List<TLessonTeacherRelation> list){

        for (TLessonTeacherRelation tLessonTeacherRelation: list) {
            tLessonTeacherRelation.setStatus(1);
        }
        lessonTeacherService.saveBatchTeacherSchedule(list);
        return  R.ok();
    }



    @ResponseBody
    @RequestMapping("/LessonTeahcerList")
    public R LessonTeahcerList(Integer lessonId,Integer positionType){
        System.out.println(lessonId);
        List<TLessonTeacherRelation>   list= lessonTeacherService.queryListByPositionType(lessonId,positionType);
        return  R.ok().put("list",list);
    }


    /**
     *
     * 课程下架
     * @param lessonId  课程ID
     * @return
     */
    @ResponseBody
    @RequestMapping("/LessonUnder")
    public R LessonUnder(Integer lessonId){
        lessonInfoService.LessonUnder(lessonId);
        return  R.ok();
    }


    /**
     * 课程上架
     * @param lessonId
     * @return
     */
    @ResponseBody
    @RequestMapping("/LessonUp")
    public R LessonUp(Integer lessonId){
        lessonInfoService.LessonUp(lessonId);
        return  R.ok();
    }


    /**
     *
     * 课节关联文件
     */
    @ResponseBody
    @RequestMapping("/saveBatchFile")

    public R saveBatchFile(@RequestBody List<TLibraryChapter> libraryChapterList){
            for(TLibraryChapter tLibraryChapter:libraryChapterList){
                tLibraryChapter.setStatus(1);
            }

        libraryChapterService.saveBatchFile(libraryChapterList);


        return R.ok();
    }

    /**
     *
     * 获取当前课节的关联文件
     */

    @ResponseBody
    @RequestMapping("/loadChapterFile")

    public R loadChapterFile(Integer chapterId){
        if(chapterId!=null){
            List<TLibraryChapter> list=  libraryChapterService.selectByChapterId(chapterId);
            return R.ok().put("LibraryChapterList",list);
        }
        return R.error(1,"没事");
    }


    /**
     *  获取跟当前章节名字相似的文件
     */
    @ResponseBody
    @RequestMapping("/loadLikeFile")

    public R loadLikeFile(Integer chapterId){



        return R.ok();
    }


    /**
     *
     * 课程库
     *
     * 上传文件,文件关联
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public R uploadFile(HttpServletRequest request){

        MultipartHttpServletRequest multipartRequest= (MultipartHttpServletRequest) request;
        List<MultipartFile> file = multipartRequest.getFiles("file");
        if(!FileUtil.checkFileSize(file, 20, "M")){
            return R.error(500,"上传文件失败，文件大小不能超过20M");
        }
        /**
         * 课程Id
         */
        String lessonId = request.getParameter("lessonId");
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
        List<Integer> fileId = libraryService.saveMultipartFile(file, fileType);
        List<TLibraryChapter> libraryChapterList=new ArrayList<>();

        if(fileId!=null){
            for(Integer id:fileId)
            {
                TLibraryChapter tLibraryChapter=new TLibraryChapter();
                tLibraryChapter.setFileId(id);
                tLibraryChapter.setStatus(1);
                tLibraryChapter.setChapterId(Integer.parseInt(chapterId));
                tLibraryChapter.setFileType(Integer.parseInt(fileType));
                tLibraryChapter.setTeacherId(ShiroUtils.getUserId().intValue());
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

    /*
    @ResponseBody
    @RequestMapping("/saveBatchFile")
    public R saveBatchFile(@RequestBody List<TLessonLibrary> tLessonLibraryList){

        for (TLessonLibrary tlessobLibrary: tLessonLibraryList){
            tlessobLibrary.setStatus("1");
        }
        lessonLibraryService.saveBatchFile(tLessonLibraryList);
        return R.ok();
    }



    @ResponseBody
    @RequestMapping("/loadLessonFile")
    public R loadLessonFile(Integer lessonId){

      List<TLessonLibrary> lessonLibraryList= lessonLibraryService.loadLessonFile(lessonId);
       return R.ok().put("list",lessonLibraryList);
    }*/
}

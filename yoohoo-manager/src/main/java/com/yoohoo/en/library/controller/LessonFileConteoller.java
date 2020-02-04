package com.yoohoo.en.library.controller;

import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.model.TLibrary;
import com.yoohoo.en.dao.model.TLibraryExample;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.lesson.service.TLibraryChapterService;
import com.yoohoo.en.library.service.TLibraryService;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.entity.SysUserEntity;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.utils.PathUtil;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created By LiWenLong On 2018/8/8 11:50
 * E-Mail:it_lwl@163.com
 * 文件库
 */

@Controller
@RequestMapping("file")
public class LessonFileConteoller {

    @Autowired
    TLibraryService tLibraryService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;


    @Autowired
    TLibraryChapterService tLibraryChapterService;
    @RequestMapping("/library.html")
    public String toPage(){
        return "file/library.html";
    }


    @ResponseBody
    @RequestMapping("/list")
    public R list(Integer page, Integer limit,Integer FileId,String FileName,Integer FileType){
        TLibraryExample example=new TLibraryExample();

        example.setPageHelper(new PageHelper((page - 1) * limit, limit));
        TLibraryExample.Criteria criteria = example.createCriteria();
        if(null!=FileId){
            criteria.andFileIdEqualTo(FileId);
        }
        if(null!=FileName && !"".equals(FileName)){
            criteria.andFileNameLike("%"+FileName+"%");
        }
        if(null!=FileType && FileType!=0){
            criteria.andFileTypeEqualTo(FileType);
        }
        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
        	criteria.andUserIdEquals(ShiroUtils.getUserId()); 
        example.setOrderByClause("file_name");
        List<TLibrary> libraryList= tLibraryService.queryList(example);
        int total=tLibraryService.count(example);
        PageUtils pageEntity=new PageUtils(libraryList,total,limit,page);
        return R.ok().put("page",pageEntity);
    }


    @ResponseBody
    @RequestMapping("/list/{teacherId}")
    public R list(@PathVariable("teacherId") Long teacherId){
        TLibraryExample example=new TLibraryExample();
        TLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEquals(teacherId); 
        List<TLibrary> libraryList= tLibraryService.queryList(example);
        return R.ok().put("list",libraryList);
    }


    @ResponseBody
    @RequestMapping("/all_list")
    public R getAllList(Integer fileType){
        List<TLibrary> libraryList= tLibraryService.queryAllListByFileType(fileType);
        return R.ok().put("FileList",libraryList);
    }


    @ResponseBody
    @RequestMapping("/save")
    public R save(@RequestBody TLibrary tLibrary, HttpServletRequest request) throws IOException {
        SysUserEntity principal = (SysUserEntity ) SecurityUtils.getSubject().getPrincipal();
        String fileUrl = tLibrary.getFileUrl();
        tLibrary.setUplaodUser(principal.getUsername());
        String realPath = PathUtil.INSTANCE.siteRootPath(request);
        tLibrary.setAddBaseInfo(ShiroUtils.getUserId());
        tLibraryService.save(tLibrary,realPath);
        //保存成功删除临时文件
        File file=new File(PathUtil.INSTANCE.siteRootPath(request)+fileUrl);
        if(file.exists()){
            file.delete();
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/getById")
    public R getById(Integer fileId){
        TLibrary tLibrary=   tLibraryService.findById(fileId);
        return R.ok().put("file",tLibrary);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public R delete(Integer fileid) throws FastDFSException {
        TLibrary library=  tLibraryService.queryById(fileid);
        String fileUrl = library.getFileUrl();

        Integer filecode= tLibraryService.deleteFile(fileUrl);
        if(filecode==0){
            tLibraryService.deleteByFileId(fileid);
            //删除依赖关系
            tLibraryChapterService.deleteByFileId(fileid);
        }
        return R.ok().put("FileCode",filecode);
    }


    @ResponseBody
    @RequestMapping("/update")
    public R update(@RequestBody TLibrary tLibrary){
        tLibraryService.updataeByFileId(tLibrary);
        return  R.ok();
    }
}

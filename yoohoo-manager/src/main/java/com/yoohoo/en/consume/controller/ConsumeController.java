package com.yoohoo.en.consume.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoohoo.en.bean.request.QueryConsumeRequest;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.consume.service.IConsumeService;
import com.yoohoo.en.dao.model.TFeetemplate;
import com.yoohoo.en.dao.model.ext.ConsumeExample;
import com.yoohoo.en.dao.model.ext.ConsumePackInfo;
import com.yoohoo.en.feetemplate.service.FeetemplateService;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.service.IStudentService;
import com.yoohoo.en.utils.DateUtil;

/**
 * 消费管理
 */
@Controller
@RequestMapping("consume")
public class ConsumeController {
	
	@Autowired
	private IConsumeService consumeService;
	
	@Autowired
    private IStudentService studentService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private FeetemplateService feetemplateService;
	
	@RequestMapping("/consume-list.html")
	public String listLesson(){
		return "consume/consume-list.html";
	}
	@RequestMapping("/tstudentbalancelog.html")
	public String list(){
		return "consume/tstudentbalancelog.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("consume:list")
	public R list(Integer page, Integer limit,String stime,String etime,Integer lessonId,Integer classItemId,String keyWord,Integer type){
		
		ConsumeExample example=new ConsumeExample();
    	example.setStime(DateUtil.parse(stime, "yyyy-MM-dd"));
    	example.setEtime(DateUtil.parse(etime, "yyyy-MM-dd"));
    	if(null != example.getEtime())
    	{
    		example.setEtime(DateUtils.addDays(example.getEtime(), 1));
    	}
    	example.setStart((page - 1) * limit);
    	example.setLimit(limit);
    	example.setLessonId(lessonId);
    	example.setClassItemId(classItemId);
    	example.setType(type);
    	if(StringUtils.isNotEmpty(keyWord))
    	{
    		Integer studentId=null;
    		try {
    			studentId=Integer.valueOf(keyWord);
			} catch (Exception e) {
			}
    		if(null == studentId)
    		{
    			example.setKeyWord("%"+keyWord.trim()+"%");
    		}else{
    			example.setStudentId(studentId);
    		}
    	}
    	if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
    		example.setAddUserId(ShiroUtils.getUserId());
        PageUtils pageUtil = new PageUtils(studentService.comsumeLog(example), studentService.countComsumeLog(example), limit, page);
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list-lesson")
	@RequiresPermissions("consume:list-lesson")
	public R listLesson(Integer lessonId,Integer classId,String dateStr,Integer classItemId){
		QueryConsumeRequest req=new QueryConsumeRequest();
		req.setClassId(classId);
		req.setDateStr(dateStr);
		req.setLessonId(lessonId);
		req.setClassItemId(classItemId);
//		String s = consumeService.queryTeacher(classId);
		List<ConsumePackInfo> datas=consumeService.queryCheckedConsumeList(req);
		PageUtils pageUtil = new PageUtils(datas, datas.size(), 1, datas.size());
		return R.ok().put("page", pageUtil);
	}
	@ResponseBody
	@RequestMapping("/getStuTypeItemList")
	public R getStuTypeItemList(){
		List<TFeetemplate> list = feetemplateService.getList(null);
		return R.ok().put("stuTypeItemArray", list);
	}
	
	
	@ResponseBody
	@RequestMapping("/saveSureResult")
	public R saveSureResult(@RequestBody ConsumePackInfo cpg){
		BaseResp resp = consumeService.sureClassLessonScheduleAndStudentConsume(cpg);
		if(ResponseCode.SUCCESS.eq(resp.getCode()))
		{
			return R.ok();
		}else{
			return R.error(resp.getMessage());
		}
	}
}

package com.yoohoo.en.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.yoohoo.en.bean.request.RechargeRequest;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.TStudentDynamicMapper;
import com.yoohoo.en.dao.model.TStudentDynamic;
import com.yoohoo.en.dao.model.ext.ConsumeExample;
import com.yoohoo.en.dao.model.ext.ConsumeLog;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.service.IStudentBlanceLogService;
import com.yoohoo.en.service.IStudentInfoService;
import com.yoohoo.en.service.IStudentService;
import com.yoohoo.en.student.entity.TStudentInfoEntity;
import com.yoohoo.en.student.service.TStudentInfoService;


/**
 * 学生信息表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:25:01
 */
@Controller
@RequestMapping("student")
public class TStudentInfoController {
	@Autowired
	private TStudentInfoService tStudentInfoService;
	@Autowired
	private IStudentBlanceLogService studentBlanceLogService;

	@Autowired
	private IStudentInfoService studentInfoService;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	TStudentDynamicMapper tStudentDynamicMapper;
	
	@RequestMapping("/tstudentinfo.html")
	public String list(){
		return "student/tstudentinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tstudentinfo:list")
	public R list(Integer page, Integer limit,Integer classItemId,String studentName){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		if(null != classItemId)
		{
			map.put("classItemId", classItemId);
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(studentName))
		{
			map.put("studentName", "%"+studentName+"%");
		}
		map.put("status", ApplicationConstant.UserStatus.NORMAL.getValue());

        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
    		map.put("addUserId", ShiroUtils.getUserId());
		//查询列表数据
		List<TStudentInfoEntity> tStudentInfoList = tStudentInfoService.queryList(map);
		int total = tStudentInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tStudentInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 动态信息
	 */
	@ResponseBody
	@RequestMapping("/dynamic")
	@RequiresPermissions("tstudentinfo:list")
	public R dynamic(Long studentId){
		TStudentDynamic tStudentDynamic = new TStudentDynamic();
		tStudentDynamic.setStudentId(studentId);
		return R.ok().put("dynamic", tStudentDynamicMapper.selectOne(tStudentDynamic));
	}
	
	
	/**
	 * 用户的账户余额变动记录列表
	 */
	@ResponseBody
	@RequestMapping("/blanceLogList")
	@RequiresPermissions("tstudentinfo:blanceLogList")
	public R blanceLogList(Integer page, Integer limit,Integer userId){
		ConsumeExample example=new ConsumeExample();
		example.setStudentId(userId);
		example.setStart((page - 1) * limit);
		//查询列表数据
		List<ConsumeLog> comsumeLog = studentService.comsumeLog(example);
		int total = studentService.countComsumeLog(example);
		
		PageUtils pageUtil = new PageUtils(comsumeLog, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("tstudentinfo:info")
	public R info(@PathVariable("userId") Integer userId){
		TStudentInfoEntity tStudentInfo = tStudentInfoService.queryObject(userId);
		
		return R.ok().put("tStudentInfo", tStudentInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tstudentinfo:save")
	public R save(@RequestBody TStudentInfoEntity tStudentInfo){
		String msg=tStudentInfoService.checkStudentInfo(tStudentInfo);
		if(org.apache.commons.lang.StringUtils.isNotEmpty(msg))
		{
			return R.error(msg);
		}
		
		
		tStudentInfoService.save(tStudentInfo);
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/recharge")
	@RequiresPermissions("tstudentinfo:recharge")
	public R recharge(@RequestBody RechargeRequest recharge){
		
		if(null == recharge.getAmount())
		{
			R.error("充值太阳花数不能为空");
		}
		
		if(StringUtils.isEmpty(recharge.getRemark()))
		{
			return R.error("备注不能为空");
		}
		
		if(tStudentInfoService.userIsExistsAndNomal(recharge.getUserId()))
		{
			tStudentInfoService.doRecharge(recharge);
		}else{
			return R.error("用户不存在");
		}
		
		return R.ok();
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tstudentinfo:update")
	public R update(@RequestBody TStudentInfoEntity tStudentInfo){
		String msg=tStudentInfoService.checkStudentInfo(tStudentInfo);
		if(org.apache.commons.lang.StringUtils.isNotEmpty(msg))
		{
			return R.error(msg);
		}
		tStudentInfo.setLastUpdateAdmin(ShiroUtils.getUserId().intValue());
		tStudentInfoService.update(tStudentInfo);
		
		return R.ok();
	}

	/**
	 * 修改绑定
	 */
	@ResponseBody
	@RequestMapping("/updateBind")
	@RequiresPermissions("tstudentinfo:updateBind")
	public R updateBind(@RequestBody TStudentInfoEntity tStudentInfo) throws FastDFSException {
		if(tStudentInfoService.checkStudentUnConfirm(tStudentInfo.getUserId(), tStudentInfo.getOldAddUserId())){
			tStudentInfo.setModifyBaseInfo(ShiroUtils.getUserId());
			tStudentInfoService.updateBind(tStudentInfo);
			return R.ok();
		}else{
			return R.error("存在正在进行中或未确认费用的课程，请先确认之后再操作！");
		}
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tstudentinfo:delete")
	public R delete(@RequestBody Long[] userIds){
		tStudentInfoService.deleteBatch(userIds);
		
		return R.ok();
	}

	/**
	 * 获取学生的学习路径
	 */
	@ResponseBody
	@RequestMapping("/queryStudentLearnPath")
	@RequiresPermissions("tstudentinfo:queryStudentLearnPath")
	public R queryStudentLearnPath(Integer studentId){
		return R.ok().put("datas",studentInfoService.queryStudentLearnPath(Long.valueOf(studentId)));
	}

	@ResponseBody
	@RequestMapping("/addStudentLearnPath")
	@RequiresPermissions("tstudentinfo:queryStudentLearnPath")
	public R addStudentLearnPath(Long studentId,Integer lessonId,Integer chapterId){
		tStudentInfoService.addLearnPath(studentId,lessonId,chapterId);
		return R.ok();
	}


	@ResponseBody
	@RequestMapping("/removeStudentLearnPath")
	@RequiresPermissions("tstudentinfo:queryStudentLearnPath")
	public R removeStudentLearnPath(Long studentId,Integer lessonId,Integer chapterId){
		tStudentInfoService.removeLearnPath(studentId,lessonId,chapterId);
		return R.ok();
	}
	
}

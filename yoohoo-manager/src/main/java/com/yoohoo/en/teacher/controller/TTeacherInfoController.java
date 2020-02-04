
package com.yoohoo.en.teacher.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.fastdfs.FastDFSClient;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.teacher.service.TClassScheduleService;
import com.yoohoo.en.teacher.service.TTeacherScheduleService;
import com.yoohoo.en.utils.PathUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.yoohoo.en.teacher.entity.TTeacherInfoEntity;
import com.yoohoo.en.teacher.service.TTeacherInfoService;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;

import javax.servlet.http.HttpServletRequest;


/**
 * 员工管理接口
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 21:24:37
 */
@Controller
@RequestMapping("teacher")
public class TTeacherInfoController {
	@Autowired
	private TTeacherInfoService tTeacherInfoService;

	@Autowired
	private TClassScheduleService tClassScheduleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;


	@Autowired
	private TTeacherScheduleService tTeacherScheduleService;
	@RequestMapping("/tteacherinfo.html")
	public String list(){
		return "teacher/tteacherinfo.html";
	}


	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tteacherinfo:list")
	public R list(Integer page, Integer limit,String LikeName){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("name",LikeName);
		map.put("status", ApplicationConstant.UserStatus.NORMAL.getValue());
		//查询所有列表数据
        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId()))
    		map.put("addUserId", ShiroUtils.getUserId());
		List<TTeacherInfoEntity> tTeacherInfoList = tTeacherInfoService.queryList(map);
		//遍历
		for (TTeacherInfoEntity t:tTeacherInfoList) {
			//根据每一个老师的Id来获取该老师的所有职位信息
			List<TTeacherPositionRelation> positionList=tTeacherInfoService.selectByTeacherId(t.getTeacherId(), t.getAddUserId());
			StringBuffer positionString = new StringBuffer();
			for ( TTeacherPositionRelation T: positionList) {
				if(T.getPositionName()!=null && !T.getPositionName().equals("")){
					positionString.append(T.getPositionName()+",");
				}else{
					continue;
				}
			}
			if(!String.valueOf(positionString).equals("")){
				int i = positionString.lastIndexOf(",");
				t.setPosition(String.valueOf(positionString.deleteCharAt(i)));
			}else{
				t.setPosition(String.valueOf(positionString));
			}
		}
		int total = tTeacherInfoService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(tTeacherInfoList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}


	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	@RequiresPermissions("tteacherinfo:listAll")
	public R listAll(){
		Map<String, Object> map = new HashMap<>();
		map.put("status", ApplicationConstant.UserStatus.NORMAL.getValue());
		//查询所有列表数据
		List<TTeacherInfoEntity> tTeacherInfoList = tTeacherInfoService.queryList(map);
		return R.ok().put("teacherList", tTeacherInfoList);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{teacherId}")
	@RequiresPermissions("tteacherinfo:info")
	public R info(@PathVariable("teacherId") Integer teacherId){
		TTeacherInfoEntity tTeacherInfo = tTeacherInfoService.queryObject(teacherId);

		/**
		 *
		 * 查询该老师的所有员工类型
		 */
		List<TTeacherPositionRelation> positionList=tTeacherInfoService.selectByTeacherId(teacherId, tTeacherInfo.getAddUserId());
		return R.ok().put("tTeacherInfo", tTeacherInfo).put("teacherPositionArray",positionList);
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tteacherinfo:save")
	public R save(@RequestBody TTeacherInfoEntity tTeacherInfo,HttpServletRequest request) throws FastDFSException {
		FastDFSClient fastDFSClient=new FastDFSClient();
		//绝对路径
		String realPath = PathUtil.INSTANCE.siteRootPath(request);
		if(StringUtils.isEmpty(tTeacherInfo.getAccount()) || StringUtils.isEmpty(tTeacherInfo.getPassword()))
		{
			return R.error("账号密码不能为空");
		}
		if(tTeacherInfoService.accountIsExists(tTeacherInfo.getAccount()))
		{
			return R.error("此登陆账号已被其它员工使用，请更换其他账号注册");
		}
		if(tTeacherInfo.getTeacherImage()!=null && !tTeacherInfo.getTeacherImage().equals("")){
				String uploadPath = fastDFSClient.upload(realPath + tTeacherInfo.getTeacherImage(), null);
				tTeacherInfo.setTeacherImage(uploadPath);
		}
		if(tTeacherInfo.getTeacherVideo()!=null && !tTeacherInfo.getTeacherImage().equals("")){
			String uploadPath = fastDFSClient.upload(realPath + tTeacherInfo.getTeacherVideo(), null);
			tTeacherInfo.setTeacherVideo(uploadPath);
		}
		tTeacherInfo.setAddBaseInfo(ShiroUtils.getUserId());
		tTeacherInfoService.save(tTeacherInfo);
        Integer teacherId =tTeacherInfo.getTeacherId();
        R r=new R();
        r.put("teacherId",teacherId);
        r.put("code",0);
        return r;
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tteacherinfo:update")
	public R update(@RequestBody TTeacherInfoEntity tTeacherInfo,HttpServletRequest request) throws FastDFSException {
		FastDFSClient f=new FastDFSClient();
		String realPath = PathUtil.INSTANCE.siteRootPath(request);
		//使用indexOf比较 是否有相同的字符串  比较是否有 临时路径如果有就更改为真是路径
		if(null!=tTeacherInfo.getTeacherImage()&& !tTeacherInfo.getTeacherImage().equals("")){
			if(tTeacherInfo.getTeacherImage().indexOf(PathUtil.CAVER_TEMP_SAVE_HOME)>-1){
					String uploadPath = f.upload(realPath + tTeacherInfo.getTeacherImage(), null);
					tTeacherInfo.setTeacherImage(uploadPath);
					TTeacherInfo teacherInfo = tTeacherInfoService.queryById(tTeacherInfo.getTeacherId());
					String teacherImage = teacherInfo.getTeacherImage();
					if(teacherImage!=null &&!teacherImage.contains("cover") && !teacherImage.equals("")) {
						f.deleteFile(teacherImage);
					}
			}
		}
		if(null!=tTeacherInfo.getTeacherVideo()&& !tTeacherInfo.getTeacherVideo().equals("")){
			if(tTeacherInfo.getTeacherVideo().indexOf(PathUtil.CAVER_TEMP_SAVE_HOME)>-1){
				String uploadPath = f.upload(realPath + tTeacherInfo.getTeacherVideo(), null);
				tTeacherInfo.setTeacherVideo(uploadPath);
				TTeacherInfo teacherInfo = tTeacherInfoService.queryById(tTeacherInfo.getTeacherId());
				String teacherVieo = teacherInfo.getTeacherVideo();
				if(teacherVieo!=null && !teacherVieo.contains("cover") && !teacherVieo.equals("")) {
					f.deleteFile(teacherVieo);
				}
			}
		}
		//否则不保存
		tTeacherInfo.setModifyBaseInfo(ShiroUtils.getUserId());
		tTeacherInfoService.update(tTeacherInfo);
		return R.ok();
	}

	/**
	 * 修改绑定
	 */
	@ResponseBody
	@RequestMapping("/updateBind")
	@RequiresPermissions("tteacherinfo:updateBind")
	public R updateBind(@RequestBody TTeacherInfoEntity tTeacherInfo) throws FastDFSException {
		tTeacherInfo.setModifyBaseInfo(ShiroUtils.getUserId());
		tTeacherInfo.setAccount(null);
		tTeacherInfoService.update(tTeacherInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tteacherinfo:delete")
	public R delete(@RequestBody Integer[] teacherIds){
		tTeacherInfoService.deleteBatch(teacherIds);
		return R.ok();
	}
	/**
	 * 批量保存老师类型
	 */
	@ResponseBody
	@RequestMapping("/saveBatchPositonType")
	public R saveBatchPositonType(@RequestBody List<TTeacherPositionRelation> list){

		if(null!=list && list.size()>0){
			for (TTeacherPositionRelation t:list) {
				t.setStatus(1);
			}
			tTeacherInfoService.insertBatchPositonType(list);
		}
		return  R.ok();
	}

	/**
	* 	根据老师类型进行查询
	*/
	@ResponseBody
	@RequestMapping("/getListByType")
	public R getListByType(Integer positionType){
		List<TTeacherInfo>  list=	tTeacherInfoService.queryByPositionType(positionType);
		return R.ok().put("list",list);
	}

	/**
	 * 获取当前老师时刻表
	 * @param  teacherId 老师Id
	 * @param  dateTime 上课时间格式：2018-11-16
	 */
	@ResponseBody
	@RequestMapping("/getTeacherSchedule")
	public R getTeacherSchedule(Integer teacherId,String dateTime) throws ParseException {
		R r=new R();
		//1.当前老师ID和日期获取老师上课时间 截取字符串
		List<String> TeacherScheduled = tClassScheduleService.queryTeacherSchedule(teacherId, dateTime);
		//2.获取当前老师已选择时间段
		List<String> TeacherSchedule = tTeacherScheduleService.queryTeacherSchedule(teacherId, dateTime);
		//3.查看是否被排课和是否还未排课，进行记录 返回已经排课的时间 和可选择的时间
		if(TeacherScheduled!=null &&TeacherSchedule!=null){
			//被排课，已选择
			List<String> tempList=new ArrayList<>();
			for(String s:TeacherSchedule){
				for(String sd:TeacherScheduled){
					if(s.equals(sd)){
						tempList.add(s);
					}
				}
			}
			for(String t:tempList){
				TeacherSchedule.remove(t);
			}
			r.put("currentDateLessonList",TeacherScheduled);
			r.put("TeacherScheduleList",TeacherSchedule);
			r.put("code",200);
			return r;
		}
		if(TeacherSchedule!=null && TeacherScheduled==null){
			//说明没有排课但已经选择
			r.put("TeacherScheduleList",TeacherSchedule);
			r.put("code",201);
			return r;
		}
		if(TeacherSchedule==null && TeacherScheduled!=null){
			//说明没有排课但已经选择
			r.put("currentDateLessonList",TeacherScheduled);
			r.put("code",202);
			return r;
		}
		if(TeacherSchedule==null && TeacherScheduled==null){
			//说明没有排课但已经选择
			r.put("code",203);
			return r;
		}
		return R.error();
	}


	/**
	 * 查询名字模糊查询
	 */
	@RequestMapping("/getTeacherByName")
	@ResponseBody
	public R getTeacherByName(String LikeName){
		List<TTeacherInfo> teacherInfo=tTeacherInfoService.getTeacherByName(LikeName);
		return R.ok().put("teacherInfoList",teacherInfo);
	}

}

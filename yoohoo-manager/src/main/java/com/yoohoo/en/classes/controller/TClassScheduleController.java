package com.yoohoo.en.classes.controller;

import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.ext.TClassScheduleExt;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.Query;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.service.IClassScheduleService;
import com.yoohoo.en.service.IClassService;
import com.yoohoo.en.teacher.service.TTeacherScheduleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 班级课程表接口
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
@RestController
@RequestMapping("tclassschedule")
public class TClassScheduleController {
	@Autowired
	private IClassScheduleService tClassScheduleService;

	@Autowired
	private IClassService tClassService;


	@Autowired
	private TTeacherScheduleService tTeacherScheduleService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tclassschedule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TClassScheduleExt> tClassScheduleList = tClassScheduleService.queryListExt(query);
		int total = tClassScheduleService.queryTotal(query);
		if(null  !=params.get("classId")) {
			String assistName = tClassService.findAssiantNameByClassId(Long.parseLong((String) params.get("classId")));
			for(int i=0;i<tClassScheduleList.size();i++){
				tClassScheduleList.get(i).setAssistantName(assistName);
			}
		}
		PageUtils pageUtil = new PageUtils(tClassScheduleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{scheduleId}")
	@RequiresPermissions("tclassschedule:info")
	public R info(@PathVariable("scheduleId") Long scheduleId){
		TClassSchedule tClassSchedule = tClassScheduleService.queryObject(scheduleId);
		
		return R.ok().put("tClassSchedule", tClassSchedule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tclassschedule:save")
	public R save(@RequestBody TClassSchedule tClassSchedule){
		tClassScheduleService.save(tClassSchedule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tclassschedule:update")
	public R update(@RequestBody TClassSchedule tClassSchedule){
		tClassScheduleService.update(tClassSchedule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tclassschedule:delete")
	public R delete(@RequestBody Integer[] scheduleIds){
		tClassScheduleService.deleteBatch(Arrays.asList(scheduleIds));
		return R.ok();
	}


	/**
	 * 根据时间和课时选择当前可以上课的老师
	 * @param dateTime 当天时间 格式:2018-11-16
	 * @param timeSchedule 时刻 格式:07:00-07:25
	 */
	@RequestMapping("/getTeacherListByTime")
	@ResponseBody
	public R getTeacherListByTime(String dateTime,String timeSchedule) throws ParseException {
		//根据当前时间和时刻返回可选择的老师集合
		R r=new R();
		List<TTeacherInfo>  teacherList=tTeacherScheduleService.getTeacherListByTime(dateTime,timeSchedule);
		r.put("teacherList",teacherList);
		return r;
	}
}

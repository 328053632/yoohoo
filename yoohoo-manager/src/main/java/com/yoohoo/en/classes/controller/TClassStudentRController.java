package com.yoohoo.en.classes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.yoohoo.en.dao.model.TClassStudentR;
import com.yoohoo.en.service.IClassStudentRService;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.Query;
import com.yoohoo.en.mcore.utils.R;


/**
 * 班级学生关系表
 * 
 * @author YuanzongInfo-HYL
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:22:14
 */
@Controller
@RequestMapping("tclassstudentr")
public class 	TClassStudentRController {
	@Autowired
	private IClassStudentRService tClassStudentRService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tclassstudentr:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		query.put("status",0);

		List<TClassStudentR> tClassStudentRList = tClassStudentRService.queryList(query);
		int total = tClassStudentRService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tClassStudentRList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
//	@ResponseBody
//	@RequestMapping("/info/{classId}")
//	@RequiresPermissions("tclassstudentr:info")
//	public R info(@PathVariable("classId") Integer classId){
//		TClassStudentR tClassStudentR = tClassStudentRService.queryObject(classId);
//
//		return R.ok().put("tClassStudentR", tClassStudentR);
//	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tclassstudentr:save")
	public R save(@RequestBody TClassStudentR tClassStudentR){
		tClassStudentRService.save(tClassStudentR);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tclassstudentr:update")
	public R update(@RequestBody TClassStudentR tClassStudentR){
		tClassStudentRService.update(tClassStudentR);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tclassstudentr:delete")
	public R delete(@RequestBody Integer[] classIds){
		tClassStudentRService.deleteBatch(Arrays.asList(classIds));
		
		return R.ok();
	}
}

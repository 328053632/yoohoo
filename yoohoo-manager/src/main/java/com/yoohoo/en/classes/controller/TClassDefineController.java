package com.yoohoo.en.classes.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoohoo.en.classes.service.TClassDefineService;
import com.yoohoo.en.dao.mapper.TClassMapper;
import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.TClassExample;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;


/**
 * 班级定义表
 * 
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-01 19:29:30
 */
@Controller
@RequestMapping("class")
public class TClassDefineController {
	@Autowired
	private TClassDefineService tClassDefineService;
	@Autowired
	private TClassMapper classMapper;
	
	@RequestMapping("/tclassdefine.html")
	public String list(){
		return "class/tclassdefine.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tclassdefine:list")
	public R list(Integer page, Integer limit){
			Map<String, Object> map = new HashMap<>();
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);

		//查询列表数据
		List<TClassDefine> tClassDefineList = tClassDefineService.queryList(map);
		int total = tClassDefineService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tClassDefineList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{classItemId}")
	@RequiresPermissions("tclassdefine:info")
	public R info(@PathVariable("classItemId") Integer classItemId){
		TClassDefine tClassDefine = tClassDefineService.queryObject(classItemId);
		
		return R.ok().put("tClassDefine", tClassDefine);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tclassdefine:save")
	public R save(@RequestBody TClassDefine tClassDefine){
		String msg =tClassDefineService.checkClassDefine(tClassDefine);
		if(StringUtils.isNotEmpty(msg))
		{
			return R.error(msg);
		}
		tClassDefineService.save(tClassDefine);
		
		return R.ok();
	}

	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tclassdefine:update")
	public R update(@RequestBody TClassDefine tClassDefine){
		String msg =tClassDefineService.checkClassDefine(tClassDefine);
		if(StringUtils.isNotEmpty(msg))
		{
			return R.error(msg);
		}
		tClassDefineService.update(tClassDefine);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tclassdefine:delete")
	public R delete(@RequestBody Integer[] classItemIds){
		
		if(null != classItemIds && classItemIds.length >0)
		{
			TClassExample example=new TClassExample();
			example.createCriteria().andClassItemIdIn(Arrays.asList(classItemIds));
			if(classMapper.countByExample(example) >0)
			{
				return R.error("已创建班级，不能删除！");
			}else{
				tClassDefineService.deleteBatch(classItemIds);
			}
		}
		return R.ok();
	}


	/**
	 *
	 * 查询所有消费信息
	 */

	@RequestMapping("/allList")
	@ResponseBody

	public R allList(){

		List<TClassDefine> tClassDefines = tClassDefineService.queryList();


		return R.ok().put("comunseList",tClassDefines);
	}
	
}

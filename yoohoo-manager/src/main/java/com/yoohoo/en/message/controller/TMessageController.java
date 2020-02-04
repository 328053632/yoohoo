package com.yoohoo.en.message.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TMessageExample;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.service.IMessageService;
import com.yoohoo.en.student.service.TStudentInfoService;
import com.yoohoo.en.teacher.service.TTeacherInfoService;
import com.yoohoo.en.utils.DateUtil;

/**
 * 消息管理
 *
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-03 14:50:37
 */
@Controller
@RequestMapping("tmessage")
public class TMessageController
{

    @Autowired
    private IMessageService messageService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private TTeacherInfoService tTeacherInfoService;
	@Autowired
	private TStudentInfoService tStudentInfoService;

    @RequestMapping("/tmessage.html")
    public String list()
    {
        return "message/tmessage.html";
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("tmessage:list")
    public R list(Integer page, Integer limit, Integer type, Integer status, String startTime, String endTime)
    {
        TMessageExample example = new TMessageExample();
        TMessageExample.Criteria criteria = example.createCriteria();
        criteria.andStatusNotEqualTo(ApplicationConstant.MESSAGE_STATUS_DELETE);
        if (type != null)
        {
            criteria.andMTypeEqualTo(type);
        }
        if (status != null)
        {
            criteria.andStatusEqualTo(status);
        }
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime))
        {
            Date sTime = DateUtil.parse(startTime, "yyyy-MM-dd");
            Date eTime = DateUtil.parse(endTime, "yyyy-MM-dd");
            if(null != eTime)
        	{
                eTime = DateUtils.addDays(eTime,1);
                //eTime = DateUtils.addDays(eTime,1);
        	}
            criteria.andAddTimeGreaterThan(sTime);
            criteria.andAddTimeLessThan(eTime);
        }
		//查询所有列表数据
        if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId())){
    		Map<String, Object> map = new HashMap<>();
    		map.put("addUserId", ShiroUtils.getUserId());
    		List<Long> tTeacherIdList = tTeacherInfoService.queryIdList(map);
    		List<Long> tStudentList = tStudentInfoService.queryIdList(map);
    		tTeacherIdList.addAll(tStudentList);
        	criteria.andMsgUserIn(tTeacherIdList);
        }
        
        
        
        example.setPageHelper(new PageHelper((page - 1) * limit, limit));
        example.setOrderByClause("status ASC, add_time DESC");
        List<TMessage> messages = messageService.query(example);
        int total = messageService.count(example);
        PageUtils pageEntity = new PageUtils(messages, total, limit, page);
        return R.ok().put("page", pageEntity);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{mId}")
    @RequiresPermissions("tmessage:info")
    public R info(@PathVariable("mId") Integer mId)
    {
        return R.ok().put("tMessage", messageService.query(mId));
    }

    /**
     * 修改, 标记为已读
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("tmessage:update")
    public R update(@RequestBody Integer[] mIds)
    {
        TMessage message = new TMessage();
        message.setStatus(ApplicationConstant.MESSAGE_STATUS_READ);

        TMessageExample example = new TMessageExample();
        example.createCriteria().andMIdIn(Arrays.asList(mIds));
        messageService.update(message, example);
        return R.ok();
    }

    /**
     * 删除: 标记为删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("tmessage:delete")
    public R delete(@RequestBody Integer[] mIds)
    {
        TMessage message = new TMessage();
        message.setStatus(ApplicationConstant.MESSAGE_STATUS_DELETE);

        TMessageExample example = new TMessageExample();
        example.createCriteria().andMIdIn(Arrays.asList(mIds));
        messageService.update(message, example);
        return R.ok();
    }

}

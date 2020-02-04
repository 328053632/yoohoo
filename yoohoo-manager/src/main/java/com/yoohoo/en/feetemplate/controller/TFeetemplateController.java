package com.yoohoo.en.feetemplate.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoohoo.en.dao.model.TFeetemplate;
import com.yoohoo.en.fastdfs.FastDFSException;
import com.yoohoo.en.feetemplate.service.FeetemplateService;
import com.yoohoo.en.mcore.utils.PageUtils;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.mcore.utils.ShiroUtils;

/**
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-02-02 14:05:49
 */
@Controller
@RequestMapping("feetemplate")
public class TFeetemplateController
{

    @Autowired
    private FeetemplateService feetemplateService;
    @RequestMapping("/templateList.html")
    public String list()
    {
        return "feetemplate/templateList.html";
    }
    /**
     * 所有模板列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("feetemplate:list")
    public R list(Integer page, Integer limit)
    { 
    	com.github.pagehelper.PageHelper.startPage(page, limit);
    	List<TFeetemplate> list = feetemplateService.getList(null);
        PageUtils pageEntity = new PageUtils(list, feetemplateService.count(null), limit, page);
        return R.ok().put("page", pageEntity);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{templateId}")
    @RequiresPermissions("feetemplate:info")
    public R info(@PathVariable("templateId") Long templateId)
    {
    	TFeetemplate model = new TFeetemplate();
    	model.setId(templateId);
        return R.ok().put("feetemplateInfo", feetemplateService.getOne(model));
    }
    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("feetemplate:save")
    public R save(@RequestBody TFeetemplate model) throws FastDFSException {
    	model.setAddBaseInfo(ShiroUtils.getUserId());
    	feetemplateService.save(model);
        R r=new R();
        r.put("code",0);
        r.put("templateId",model.getId());
        return r;
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("feetemplate:update")
    public R update(@RequestBody TFeetemplate model) throws FastDFSException {
    	model.setModifyBaseInfo(ShiroUtils.getUserId());
    	feetemplateService.update(model);
        return R.ok();
    }


    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("feetemplate:delete")
    public R delete(@RequestBody Long[] templateIds)
    {
    	TFeetemplate model = null;
    	for(long id:templateIds){
    		model = new TFeetemplate();
    		model.setId(id);
    		model = feetemplateService.getOne(model);
    		if(model != null)
    			model.setStatus(0);
    		feetemplateService.update(model);
    	}
        return R.ok();
    }
}

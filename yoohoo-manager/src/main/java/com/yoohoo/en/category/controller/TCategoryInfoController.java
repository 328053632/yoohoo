package com.yoohoo.en.category.controller;

import com.yoohoo.en.dao.model.TCategoryInfo;
import com.yoohoo.en.mcore.utils.R;
import com.yoohoo.en.service.ICategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 分类表
 *
 * @author dev-group
 * @email admin@yuanzonginfo.com
 * @date 2018-03-05 10:46:49
 */
@Controller
@RequestMapping("tcategoryinfo")
public class TCategoryInfoController
{

    @Autowired
    private ICategoryService categoryService;
    @RequestMapping("/tcategoryinfo.html")
    public String list()
    {
        return "category/tcategoryinfo.html";
    }


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("tcategoryinfo:list")
    public R list(Integer page, Integer limit)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //PageUtils pageUtil = new PageUtils(tCategoryInfoList, total, limit, page);

        return R.ok().put("page", null);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcategoryinfo:info")
    public R info(@PathVariable("id") Integer id)
    {

        return R.ok().put("tCategoryInfo", null);
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("tcategoryinfo:save")
    public R save(@RequestBody TCategoryInfo tCategoryInfo)
    {
        return R.ok().put("treeModel", categoryService.insert(tCategoryInfo));
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("tcategoryinfo:update")
    public R update(@RequestBody TCategoryInfo tCategoryInfo)
    {
        return R.ok().put("treeModel", categoryService.update(tCategoryInfo));
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    @RequiresPermissions("tcategoryinfo:delete")
    public R delete(@RequestBody Integer[] ids)
    {
        for (Integer id : ids)
        {
            categoryService.delete(id);
        }
        return R.ok();
    }

    /**
     * 查询分类树结构数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/categoryTreeData")
    public R categoryTreeData(@RequestParam(value = "expand", required = false, defaultValue = "false") boolean expand)
    {
        return R.ok().put("tree", categoryService.treeData(expand));
    }
}

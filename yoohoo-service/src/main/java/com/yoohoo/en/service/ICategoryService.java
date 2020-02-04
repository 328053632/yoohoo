package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TCategoryInfo;
import com.yoohoo.en.model.MenuModel;
import com.yoohoo.en.model.TreeModel;

import java.util.List;
import java.util.Map;

public interface ICategoryService
{
    List<TreeModel> treeData(boolean expand);

    TreeModel insert(TCategoryInfo categoryInfo);

    int delete(int id);

    TreeModel update(TCategoryInfo categoryInfo);

    List<MenuModel> menuData();
    
    List<Map<String, String>> getParentCategory();
}

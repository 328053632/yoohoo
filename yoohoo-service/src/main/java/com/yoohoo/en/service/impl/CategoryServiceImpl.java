package com.yoohoo.en.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TCategoryInfoMapper;
import com.yoohoo.en.dao.mapper.TLessonInfoMapper;
import com.yoohoo.en.dao.model.TCategoryInfo;
import com.yoohoo.en.dao.model.TCategoryInfoExample;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;
import com.yoohoo.en.model.MenuModel;
import com.yoohoo.en.model.TreeModel;
import com.yoohoo.en.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private TCategoryInfoMapper categoryInfoMapper;

    @Autowired
    private TLessonInfoMapper lessonInfoMapper;

    @Override
    public List<TreeModel> treeData(boolean expand)
    {
        return rootTreeData(expand);
    }

    private List<TreeModel> rootTreeData(boolean expand)
    {
        List<TreeModel> models = new ArrayList<>();
        TCategoryInfoExample example = new TCategoryInfoExample();
        example.createCriteria().andLevelEqualTo(0);
        List<TCategoryInfo> tCategoryInfos = categoryInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tCategoryInfos))
        {
            return models;
        }

        TreeModel.Builder builder = null;
        for (TCategoryInfo tCategoryInfo : tCategoryInfos)
        {
            TreeModel treeModel = category2TreeModel(tCategoryInfo);
            treeModel.setExpand(expand);
            models.add(treeModel);
        }
        return models;
    }

    private List<TreeModel> treeNodeData(Integer id)
    {
        List<TreeModel> models = new ArrayList<>();
        TCategoryInfoExample example = new TCategoryInfoExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TCategoryInfo> tCategoryInfos = categoryInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tCategoryInfos))
        {
            return models;
        }

        TreeModel.Builder builder = null;
        for (TCategoryInfo tCategoryInfo : tCategoryInfos)
        {
            models.add(category2TreeModel(tCategoryInfo));
        }
        return models;
    }

    private TreeModel category2TreeModel(TCategoryInfo categoryInfo)
    {
        TreeModel.Builder builder = new TreeModel.Builder();
        builder.id(categoryInfo.getId())
            .title(categoryInfo.getCategoryName())
            .level(categoryInfo.getLevel())
            .parentId(categoryInfo.getParentId())
            .children(treeNodeData(categoryInfo.getId()));
        return builder.build();
    }

    @Override
    public TreeModel insert(TCategoryInfo categoryInfo)
    {
        categoryInfoMapper.insert(categoryInfo);
        return category2TreeModel(categoryInfo);
    }

    @Override
    public int delete(int id)
    {
        return  categoryInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TreeModel update(TCategoryInfo categoryInfo)
    {
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);

        TLessonInfo record = new TLessonInfo();
        record.setCategoryName(categoryInfo.getCategoryName());
        TLessonInfoExample example = new TLessonInfoExample();
        example.createCriteria().andCategoryIdEqualTo(categoryInfo.getId());
        lessonInfoMapper.updateByExampleSelective(record, example);
        return category2TreeModel(categoryInfo);
    }

   @Override
   public List<MenuModel> menuData()
   {
       List<MenuModel> models = new ArrayList<>();
       TCategoryInfoExample example = new TCategoryInfoExample();
       example.createCriteria().andLevelEqualTo(0);
       List<TCategoryInfo> tCategoryInfos = categoryInfoMapper.selectByExample(example);
       if (CollectionUtils.isEmpty(tCategoryInfos))
       {
           return models;
       }

       for (TCategoryInfo tCategoryInfo : tCategoryInfos)
       {
           models.add(category2MenuModel(tCategoryInfo));
       }
       return models;
   }

    private MenuModel category2MenuModel(TCategoryInfo tCategoryInfo)
    {
        MenuModel model = new MenuModel();
        model.setId(tCategoryInfo.getId());
        model.setName(tCategoryInfo.getCategoryName());
        model.setItems(menuItems(tCategoryInfo.getId()));
        return model;
    }



    private List<MenuModel> menuItems(Integer id)
    {
        List<MenuModel> models = new ArrayList<>();
        TCategoryInfoExample example = new TCategoryInfoExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TCategoryInfo> tCategoryInfos = categoryInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tCategoryInfos))
        {
            return models;
        }

        for (TCategoryInfo tCategoryInfo : tCategoryInfos)
        {
            models.add(category2MenuModel(tCategoryInfo));
        }

        return models;
    }
    
    @Override
    public List<Map<String, String>> getParentCategory() {
      TCategoryInfoExample example = new TCategoryInfoExample();
      example.createCriteria().andParentIdEqualTo(Integer.valueOf(-1)).andLevelEqualTo(Integer.valueOf(0));
      List<Map<String, String>> list = new ArrayList<Map<String, String>>();
      List<TCategoryInfo> tCategoryInfos = this.categoryInfoMapper.selectByExample(example);
      if (null != tCategoryInfos && tCategoryInfos.size() > 0) {
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("key", "0");
        map1.put("name", "全部课程");
        list.add(map1);
        for (TCategoryInfo t : tCategoryInfos) {
          Map<String, String> map = new HashMap<String, String>();
          map.put("key", t.getId() + "");
          map.put("name", t.getCategoryName());
          list.add(map);
        } 
        
        return list;
      } 
      return null;
    }
}

package com.yoohoo.en.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yoohoo.en.dao.mapper.TClassStudentRMapper;
import com.yoohoo.en.dao.model.TClassStudentR;
import com.yoohoo.en.dao.model.TClassStudentRExample;
import com.yoohoo.en.service.IClassStudentRService;

@Service("tClassStudentRService")
public class ClassStudentRServiceImpl implements IClassStudentRService
{
    @Autowired
    private TClassStudentRMapper tClassStudentRMapper;

    @Override
    public TClassStudentR queryObject(Long classId, Long studentId)
    {
         TClassStudentRExample example = new TClassStudentRExample();
         example.createCriteria().andClassIdEqualTo(classId).andStudentIdEqualTo(studentId);
         List<TClassStudentR> list = tClassStudentRMapper.selectByExample(example);
         if(CollectionUtils.isEmpty(list)) {
             return null;
         }
         return list.get(0);
    }

    @Override
    public List<TClassStudentR> queryList(Map<String, Object> map)
    {
        TClassStudentRExample example = this.getExample(map);
        return tClassStudentRMapper.selectByExample(example);
    }

    @Override
    public int queryTotal(Map<String, Object> map)
    {
        TClassStudentRExample example = this.getExample(map);
        return tClassStudentRMapper.countByExample(example);
    }

    private TClassStudentRExample getExample(Map<String, Object> map){
        TClassStudentRExample example = new TClassStudentRExample();
        TClassStudentRExample.Criteria criteria = example.createCriteria();
        if (null != map.get("classId"))
        {
        	Long classId = Long.valueOf(map.get("classId").toString());
            if (classId != 0)
            {
                criteria.andClassIdEqualTo(classId);
            }
        }
        if (null != map.get("status"))
        {
            criteria.andStatusNotEqualTo((Integer)map.get("status"));
        }

        return example;
    }

    @Override
    public void save(TClassStudentR tClassStudentR)
    {
        tClassStudentRMapper.insertSelective(tClassStudentR);
    }

    @Override
    public void update(TClassStudentR tClassStudentR)
    {
        //tClassStudentRMapper.updateByPrimaryKeySelective(tClassStudentR);
    }

    @Override
    public void delete(Integer classId)
    {
        //tClassStudentRMapper.deleteByPrimaryKey(classId);
    }

    @Override
    public void deleteBatch(List<Integer> classIds)
    {
        TClassStudentRExample example = new TClassStudentRExample();
        example.createCriteria().andClassIdIn(classIds);
        tClassStudentRMapper.deleteByExample(example);
    }

}

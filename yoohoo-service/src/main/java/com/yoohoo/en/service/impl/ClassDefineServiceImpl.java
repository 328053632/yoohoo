package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.mapper.TClassDefineMapper;
import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.TClassDefineExample;
import com.yoohoo.en.service.IClassDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassDefineServiceImpl implements IClassDefineService
{
    @Autowired
    private TClassDefineMapper classDefineMapper;

    @Override
    public List<TClassDefine> list()
    {
        return classDefineMapper.selectByExample(new TClassDefineExample());
    }
}

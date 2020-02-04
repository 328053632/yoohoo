package com.yoohoo.en.service.impl;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.bean.SmsConfig;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.SysConfigMapper;
import com.yoohoo.en.dao.model.SysConfig;
import com.yoohoo.en.dao.model.SysConfigExample;
import com.yoohoo.en.service.ISystemConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemConfigServiceImpl implements ISystemConfigService
{
    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public SysConfig query(String key)
    {
        SysConfigExample example = new SysConfigExample();
        example.createCriteria().andKeyEqualTo(key);
        List<SysConfig> sysConfigs = configMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysConfigs))
        {
            return sysConfigs.get(0);
        }
        return null;
    }

    @Override
    public SmsConfig getSmsConfig()
    {
        SysConfig config = query(ApplicationConstant.SMS_CONFIG_KEY);
        if (config == null || StringUtils.isEmpty(config.getValue()))
        {
            return null;
        }
        return JSON.parseObject(config.getValue(), SmsConfig.class);
    }

    @Override
    public List<String> lessonTimeList()
    {
        SysConfig config = query(ApplicationConstant.LESSON_TIME_KEY);
        if (config == null || StringUtils.isEmpty(config.getValue()))
        {
            return new ArrayList<>();
        }
        return JSON.parseArray(config.getValue(), String.class);
    }
}

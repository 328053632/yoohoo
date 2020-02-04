package com.yoohoo.en.service;

import com.yoohoo.en.bean.SmsConfig;
import com.yoohoo.en.dao.model.SysConfig;

import java.util.List;

public interface ISystemConfigService
{
    SysConfig query(String key);

    /**
     * 查询短信配置
     * @return
     */
    SmsConfig getSmsConfig();

    /**
     * 查询试听课申请时间段
     * @return
     */
    List<String> lessonTimeList();
}

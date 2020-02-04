package com.yoohoo.en.web.spring;

import com.yoohoo.en.service.ISystemConfigService;
import com.yoohoo.en.utils.SendSmsUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent)
    {
        Application.applicationContext = applicationEvent.getApplicationContext();
        ISystemConfigService sysConfigService = Application.applicationContext.getBean(ISystemConfigService.class);
        SendSmsUtil.smsConfig = sysConfigService.getSmsConfig();
    }
}

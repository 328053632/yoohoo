package com.yoohoo.en.web.spring;

import org.springframework.beans.factory.annotation.Value;

public class ConfigProper
{

    @Value("#{configProperties['lesson.cover.url.prefix']}")
    private String coverUrlPrefix;

    @Value("#{configProperties['class.in.url.prefix']}")
    private String classInUrlPrefix;

    public String getClassInUrlPrefix() {
        return classInUrlPrefix;
    }

    public void setClassInUrlPrefix(String classInUrlPrefix) {
        this.classInUrlPrefix = classInUrlPrefix;
    }

    public String getCoverUrlPrefix()
    {
        return coverUrlPrefix;
    }

    public void setCoverUrlPrefix(String coverUrlPrefix)
    {
        this.coverUrlPrefix = coverUrlPrefix;
    }


}

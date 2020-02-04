package com.yoohoo.en.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 日志管理类
 * 
 * @author 杨邦山
 * 
 */
public final class LogMgr
{
    
    /**
     * 日志对象
     */
    private static LogMgr logMgr;
    
    private LogMgr()
    {
    }
    
    /**
     * 获取日志管理对象。
     * 
     * @return
     */
    public static LogMgr getInstance()
    {
        if (null == logMgr)
        {
            logMgr = new LogMgr();
        }
        return logMgr;
    }
    
    /**
     * 输出提示级别的日志。
     * 
     * @param className
     * @param str
     */
    public void infoLog(Class<?> className, String str)
    {
        Logger logger = LogManager.getLogger(className);
        logger.info(str);
    }
    
    /**
     * 输出错误级别日志信息
     * 
     * @param className
     * @param e
     */
    public void errorLog(Class<?> className, Throwable e)
    {
        Logger logger = LogManager.getLogger(className);
        logger.error(e.getMessage(), e);
    }
    
    /**
     * 输出调式级别日志信息
     * 
     * @param className
     * @param str
     */
    public void debugLog(Class<?> className, String str)
    {
        Logger logger = LogManager.getLogger(className);
        logger.debug(str);
    }
}

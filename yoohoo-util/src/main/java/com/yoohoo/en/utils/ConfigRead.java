package com.yoohoo.en.utils;

import java.net.URISyntaxException;
import java.util.Properties;

/**
 * 动态读取配置文件类
 * 
 * @author 何云良 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ConfigRead
{
    /**
     * 属性文件全名,需要的时候请重新配置PFILE
     */
    private final static String PFILE = "public-config.properties";
    
    /**
     * 属性文件所对应的属性对象变量
     */
    private Properties m_props = null;
    
    /**
     * 唯一实例
     */
    private static ConfigRead m_instance = new ConfigRead();
    
    /**
     * 私有构造函数
     * 
     * @throws URISyntaxException
     */
    private ConfigRead()
    {
        m_props = new Properties();
        
        try
        {
            m_props.load(this.getClass().getClassLoader().getResourceAsStream(PFILE));
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
            try
            {
                m_props.load(this.getClass().getResourceAsStream("/" + PFILE));
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * 静态工厂方法
     * 
     * @return 返回ConfigurationRead的单一实例
     */
    public synchronized static ConfigRead getInstance()
    {
        return m_instance;
    }
    
    /**
     * 读取一特定的属性项
     */
    public String getConfigItem(String name, String defaultVal)
    {
        String val = m_props.getProperty(name);
        if (val == null)
        {
            return defaultVal;
        }
        else
        {
            return val;
        }
    }
    
    /**
     * 读取一特定的属性项
     * 
     * @param name 属性项的项名
     * @return 属性项的值（如此项存在）， 空（如此项不存在）
     */
    public String getConfigItem(String name)
    {
        return getConfigItem(name, "");
    }
    
    public boolean getConfigItem(String name, boolean defalutVal)
    {
        String val = getConfigItem(name, "");
        try
        {
            return Boolean.valueOf(val);
        }
        catch (Exception e)
        {
            return defalutVal;
        }
    }
    
}

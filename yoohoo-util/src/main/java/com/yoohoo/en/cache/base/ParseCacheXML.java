package com.yoohoo.en.cache.base;

import com.yoohoo.en.cache.bean.CacheConfig;
import com.yoohoo.en.cache.bean.InvalidCacheBean;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ParseCacheXML
{
    private static final int ONE_MINUTE = 60;
    
    private static final Logger logger = Logger.getLogger(ParseCacheXML.class);
    
    /***
     * 解析缓存配置文件信息
     * 
     * @param url
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map<String, CacheConfig> loadMemcacheXML(URL url)
    {
        Map<String, CacheConfig> cacheMap = new HashMap<String, CacheConfig>();
        
        SAXReader reader = new SAXReader();
        Document doc = null;
        
        try
        {
            doc = reader.read(url);
        }
        catch (DocumentException e)
        {
            logger.error("ParseCacheXML.loadMemcacheXML", e);
            throw new RuntimeException("cache.xml is not exist!!!  path "
                + (null ==url?"":url.getPath()));
        }
        
        Element root = doc.getRootElement();
        
        for (Iterator<Element> elment = root.elementIterator(); elment.hasNext();)
        {
            Element el = elment.next();
            
            if ("cache".equals(el.getName()))
            {
                String cacheName = el.attributeValue("name");
                CacheConfig config = new CacheConfig();
                config.setCacheName(cacheName);
                
                for (Iterator<Element> elment2 = el.elementIterator(); elment2.hasNext();)
                {
                    Element el2 = elment2.next();
                    
                    if ("getcaheModel".equals(el2.getName()))
                    {
                        config.setGetcaheModel(el2.getTextTrim());
                    }
                    else if ("deleteCacheModel".equals(el2.getName()))
                    {
                        config.setDeleteCacheModel(el2.getTextTrim());
                    }
                    else if ("updateCacheModel".equals(el2.getName()))
                    {
                        config.setUpdateCacheModel(el2.getTextTrim());
                    }
                    else if ("needLock".equals(el2.getName()))
                    {
                        config.setNeedLock(Boolean.parseBoolean(el2.getTextTrim()));
                    }
                    else if ("maxThread".equals(el2.getName()))
                    {
                        config.setMaxThread(Integer.parseInt(el2.getTextTrim()));
                    }
                    else if ("InvalidDataCache".equals(el2.getName()))
                    {
                        InvalidCacheBean invalidBean = new InvalidCacheBean();
                        boolean flg = false;
                        for (Iterator<Element> elment3 = el2.elementIterator(); elment3.hasNext();)
                        {
                            Element el3 = elment3.next();
                            if ("switchs".equals(el3.getName()))
                            {
                                invalidBean.setSwitchs(el3.getTextTrim());
                                if ("on".equals(invalidBean.getSwitchs()))
                                {
                                    flg = true;
                                }
                            }
                            else if ("invalidTime".equals(el3.getName()))
                            {
                                invalidBean.setInvalidTime(Integer.parseInt(el3.getTextTrim()) * ONE_MINUTE);
                            }
                            else if ("supported".equals(el3.getName()))
                            {
                                invalidBean.setSupported(Boolean.parseBoolean(el3.getTextTrim()));
                            }
                        }
                        if (flg)
                        {
                            config.setInvalidBean(invalidBean);
                        }
                    }
                    else if ("oscache-file".equals(el2.getName()))
                    {
                        config.setOscacheFile(el2.getTextTrim());
                    }
                    else if ("memcache-name".equals(el2.getName()))
                    {
                        config.setMemcacheName(el2.getTextTrim());
                    }
                    else if ("refreshMode".equals(el2.getName()))
                    {
                        config.setRefreshMode(Integer.parseInt(el2.getTextTrim()));
                    }
                    else if ("oscache-invalid-time".equals(el2.getName()))
                    {
                        config.setOscacheInvalidTime(Integer.parseInt(el2.getTextTrim()));
                    }
                    else if ("memcache-invalid-time".equals(el2.getName()))
                    {
                        config.setMemcacheInvalidTime(Integer.parseInt(el2.getTextTrim()));
                    }
                }
                
                cacheMap.put(cacheName, config);
            }
        }
        
        return cacheMap;
    }
    
    
}
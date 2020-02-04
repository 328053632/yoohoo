package com.yoohoo.en.cache.util;

import com.alibaba.fastjson.JSON;
import com.yoohoo.en.cache.bean.MemcachePoolBean;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MemcacheConfigPath
{
    private static Logger logger = Logger.getLogger(MemcacheConfigPath.class);
    
    //存放memcached配置属性的map服务器地址
    private static Map<String,MemcachePoolBean> poolMap = new HashMap<String,MemcachePoolBean>();
    
    private static Map<String,String> memcaheMap = new HashMap<String,String>();
    
    public MemcacheConfigPath(){
        
    }
    //解析memcahed配置的服务器地址
    @SuppressWarnings("unchecked")
	public static void load(URL url){
        SAXReader read = new SAXReader();
        Document doc = null;
        try {
            doc = read.read(url);
        } catch (DocumentException e) {
            logger.error("MemcacheConfigPath.Load ERROR", e);
            return;
        }
        
        Element root = doc.getRootElement();
        Iterator<Element> elements = root.elementIterator();
        while(elements.hasNext()){
            Element element = (Element) elements.next();
            if("pool".equals(element.getName())){
                String poolId = element.attributeValue("id");
                MemcachePoolBean pool = readPool(element);
                poolMap.put(poolId, pool);
            }
            else if("cache-item".equals(element.getName())){
                Iterator<Element> elements2 = element.elementIterator();
                while(elements2.hasNext()){
                    Element element2 = (Element) elements2.next();
                    if("cache".equals(element2.getName())){
                        String cacheName = element2.attributeValue("cachename");
                        String master = element2.attributeValue("master");
                        memcaheMap.put(cacheName, master);
                    }
                }
            }
        }
        if(logger.isDebugEnabled())
        {
        	logger.debug("Memcached Pool Config:" +JSON.toJSONString(poolMap)+", Cached Config:"+JSON.toJSONString(memcaheMap));
        }
    }
    
    //解析xml二级节点，将xml中的配置属性载入实体类
    public static MemcachePoolBean readPool(Element el){
        
        MemcachePoolBean pool = new MemcachePoolBean();
        @SuppressWarnings("unchecked")
        Iterator<Element> elements = el.elementIterator();
        while(elements.hasNext()){
            Element element = elements.next();
            if("addressList".equals(element.getName())){
                pool.setAddressList(element.getTextTrim());
            }
            else if("connectSize".equals(element.getName())){
                pool.setConnectSize(Integer.parseInt(element.getTextTrim()));
            }
            else if("mergeFactor".equals(element.getName())){
                pool.setMergeFactor(Integer.parseInt(element.getTextTrim()));
            }
            else if("initBufSize".equals(element.getName())){
                pool.setInitBufSize(Integer.parseInt(element.getTextTrim()));
            }
            else if("maxBufSize".equals(element.getName())){
                pool.setMaxBufSize(Integer.parseInt(element.getTextTrim()));
            }
            else if("compressionThreshold".equals(element.getName())){
                pool.setCompressionThreshold(Integer.parseInt(element.getTextTrim()));
            }
            else if("connectTimeout".equals(element.getName())){
                pool.setConnectTimeout(Integer.parseInt(element.getTextTrim()));
            }
            else if("isDefaultTranscode".equals(element.getName())){
                pool.setDefaultTranscode(Boolean.parseBoolean(element.getTextTrim()));
            }
            else if("failureMode".equals(element.getName())){
                pool.setFailureMode(Boolean.parseBoolean(element.getTextTrim()));
            }
        }
        return pool;
    }
    
    public static Map<String,MemcachePoolBean> getPoolMap(){
        return poolMap;
    }
    
    public static Map<String,String> getMemcaheMap(){
        return memcaheMap;
    }
}
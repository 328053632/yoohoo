package com.yoohoo.en.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

public class XmlUtils
{
    private static Logger logger = Logger.getLogger(XmlUtils.class);
    /**
     * JavaBean转换成xml 默认编码UTF-8
     * 
     * @param obj
     * @param writer
     * @return
     */
    public static String convertToXml(Object obj)
    {
        return convertToXml(obj, "UTF-8");
    }
    
    /**
     * JavaBean转换成xml
     * 
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding)
    {
        String result = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * xml转换成JavaBean
     * 
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c)
    {
        T t = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T)unmarshaller.unmarshal(new StringReader(xml));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return t;
    }
    
}



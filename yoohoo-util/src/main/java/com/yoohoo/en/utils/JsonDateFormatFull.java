package com.yoohoo.en.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/** 
* @author YiMing Zhang 
* @version 2016年6月13日 下午12:29:46
*/
public class JsonDateFormatFull extends JsonSerializer<Date>{

	@Override
    public void serialize(Date value, JsonGenerator jgen,
            SerializerProvider provider) throws
 
    IOException, JsonProcessingException {
        SimpleDateFormat formatter =   new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
 
    }
}

package com.yoohoo.en.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/9/10 11:12
 * E-Mail:it_lwl@163.com
 */


public class FileTest {


    @Test

    public void test1() throws IOException, URISyntaxException {

     /*   URL url = new URL("http:\\file.yoohooabc.com\\group1\\M00\\00\\00\\rBOmwFuV4jaAQ3mIAAHAaeE1q90656.doc");
        HttpURLConnection httpURLConnection = null;
        httpURLConnection = (HttpURLConnection) url.openConnection();
        Long size=Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
        if (size>3000){
            System.out.println("存在");
        }
    */

        URL uri = new URL("http://file.yoohooabc.com/group1/M00/00/00/rBOmwFuV4jaAQ3mIAAHAaeE1q90656.doc");
        File file=new File(uri.toURI());
        long length = file.length();
        System.out.println("文件大小:"+length);
        boolean newFile = file.createNewFile();
        System.out.println("创建文件是否成功:"+newFile);
    }

}
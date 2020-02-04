package com.yoohoo.en.fastdfs;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/9/10 13:48
 * E-Mail:it_lwl@163.com
 */
public class FastDFSClientTest {

    @Test
    public void uploadTest() throws FastDFSException, IOException {
        FastDFSClient fastDFSClient=new FastDFSClient();

        byte[] download = fastDFSClient.download("group1/M00/00/00/rBOmwFuV4jaAQ3mIAAHAaeE1q90656.doc");

        InputStream is=new ByteArrayInputStream(download);
        byte[] buffer = new byte[1024 * 5];
        OutputStream os=new FileOutputStream("D:\\1.doc");
        int len = 0;
        while((len=is.read(buffer))>0){
            os.write(buffer);
        }
    }

}
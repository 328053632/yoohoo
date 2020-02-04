package com.yoohoo.en.classes.controller;

import com.yoohoo.en.utils.StringUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/9/11 10:51
 * E-Mail:it_lwl@163.com
 */
public class TClassControllerTest {

    @Test

    public void Rte(){
        String s="1-liwenlong.jpg";

        String fileName = s.substring( s.lastIndexOf("-")+1);
        System.out.println(fileName);

    }

}
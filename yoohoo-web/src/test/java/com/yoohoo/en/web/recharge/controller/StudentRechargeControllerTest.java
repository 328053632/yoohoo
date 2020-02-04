package com.yoohoo.en.web.recharge.controller;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/8/18 15:08
 * E-Mail:it_lwl@163.com
 */
public class StudentRechargeControllerTest {


    @Test
    public void TestMoneyChange(){


        String money="0.01";
        double v = Double.parseDouble(money);
        System.out.println(v+"");
    }

    @Test
    public void DateTest() throws ParseException {

        String date="20180821171607";


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        Date format = simpleDateFormat.parse(date);
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(format);
        Date format1 = simpleDateFormat2.parse(format2);
        System.out.println(format1);
    }

}
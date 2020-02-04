package com.yoohoo.en.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/10/30 15:05
 * E-Mail:it_lwl@163.com
 */

public class StudentLessonScheduleServiceImplTest {


    @Test
    public void Tes1(){


        Set<Integer> testList=new HashSet<>();

        testList.add(1861);
        testList.add(1862);
        testList.add(1863);
        testList.add(1864);
        testList.add(1865);
        testList.add(1866);
        testList.add(1867);
        testList.add(1868);
        testList.add(1869);
        System.out.println(testList.toString());
        testList.remove(1867);
        System.out.println(testList.toString());

    }
}

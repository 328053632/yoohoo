package com.yoohoo.en.lesson.service.impl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/8/23 11:15
 * E-Mail:it_lwl@163.com
 */
public class LessonTeacherServiceImplTest {

    @Test
    public void saveBatchTeacherSchedule() {

        List<Integer> list=new ArrayList<>();

        list.add(3);
        list.add(32);
        list.add(17);
        list.add(32);
        list.add(3);
        list.add(32);
        System.out.println(list);
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}
package com.yoohoo.en.teacher.controller;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created By LiWenLong On 2018/11/15 14:10
 * E-Mail:it_lwl@163.com
 */
public class TTeacherInfoControllerTest {



    @Test
    public  void test1(){
        List<String> TeacherScheduled=new ArrayList<>();

        List<String> TeacherSchedule=new ArrayList<>();

        List<String> tempList=new ArrayList<>();

        for(String s:TeacherSchedule){
            for(String sd:TeacherScheduled){
                if(s.equals(sd)){
                    tempList.add(s);
                }
            }
        }
        for(String t:tempList){
            TeacherSchedule.remove(t);
        }

        System.out.println(TeacherScheduled);
        System.out.println(TeacherSchedule);
    }






    @Test
    public void test_list(){
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        for(String s:list){
            if("2".equals(s)){
                list.remove(s);
            }
        }
        System.out.println(list);
    }




    @Test
    public void test_iterator(){
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        Iterator<String> iterator = list1.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if("2".equals(next)){
                iterator.remove();
            }
        }
        System.out.println(list1);
    }


    @Test
    public void testMapEntry(){
        Map<Integer,String> mapEntry=new HashMap<>(8);
        mapEntry.put(1,"李文龙");
        mapEntry.put(2,"王丹琪");
        mapEntry.put(3,"啦啦啦");
        mapEntry.put(4,"嘻嘻嘻");
        mapEntry.put(5,"呜呜呜");
        Iterator<Map.Entry<Integer, String>> iterator = mapEntry.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println("Key:"+next.getKey()+"--"+"Value:"+next.getValue());
        }
    }

    @Test
    public void testMapKeySet(){
        Map<Integer,String> mapKeySet=new HashMap<>(5);
        mapKeySet.put(1,"李文龙");
        mapKeySet.put(2,"王丹琪");
        mapKeySet.put(3,"啦啦啦");
        mapKeySet.put(4,"嘻嘻嘻");
        mapKeySet.put(5,"呜呜呜");
          for(Integer key:mapKeySet.keySet()){
              System.out.println("i="+mapKeySet.get(key));
          }
    }

    @Test
    public void testMaoForeache(){
        Map<Integer,String> mapForeach=new HashMap<>(5);
        mapForeach.put(1,"李文龙");
        mapForeach.put(2,"王丹琪");
        mapForeach.put(3,"啦啦啦");
        mapForeach.put(4,"嘻嘻嘻");
        mapForeach.put(5,"呜呜呜");
        mapForeach.forEach((k,v)->System.out.println("key:value="+k
        +":"+v));
    }


    @Test
    public void testTime(){

        String timeStr="1542767400000";


        Date d=new Date();
        d.setTime(Long.valueOf(timeStr));


        System.out.println(d);
    }
}


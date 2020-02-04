package com.yoohoo.en.service;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ILessonInfoService
{
    /**
     * 查询课程列表
     * @param example
     * @return
     */
    List<TLessonInfo> queryList(TLessonInfoExample example);

    /**
     * 查询总数
     * @param example
     * @return
     */
    int count(TLessonInfoExample example);

    /**
     * 根据id查询信息
     * @param lessonId
     * @return
     */
    TLessonInfo queryById(Long lessonId);

    /**
     * 插入数据
     * @param lessonInfo
     * @return
     */
    Long insert(TLessonInfo lessonInfo);

    /**
     *更新数据
     * @param lessonInfo
     * @return
     */
    int update(TLessonInfo lessonInfo);

    /**
     * 根据主键删除数据
     * @param lessonId
     * @return
     */
    int delete(int lessonId);

    /**
     * 批量删除数据
     * @param lessonIds
     * @return
     */
    int deleteBatch(List<Integer> lessonIds);
    
    /**
     * 查询现有课程
     * @param lessonName
     * @param lessonType
     * @param studentId
     * @return
     */
    BaseResp queryLessons2(String lessonName,String lessonType,Integer studentId) throws InvocationTargetException, IllegalAccessException;

    BaseResp queryLessons(String lessonName,int categoryId, int pageNo, int limit, Integer type) throws InvocationTargetException, IllegalAccessException;

    String checkLessonInfo(TLessonInfo l);

    BaseResp applyStatus(Long userId, int lessonId, int chapterId);

    BaseResp queryChapters(Long lessonId);

    void LessonUnder(Integer lessonId);

    void LessonUp(Integer lessonId);


}

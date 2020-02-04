package com.yoohoo.en.web.student.service.impl;

import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.web.student.service.TLessonChapterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By LiWenLong On 2018/11/22 10:10
 * E-Mail:it_lwl@163.com
 */
@Service
public class TLessonChapterSeviceImpl   implements TLessonChapterSevice {

    @Autowired
    private TLessonChapterMapper tLessonChapterMapper;

    @Override
    public List<TLessonChapter> getChapterListByLessonId(String lessonId) {
        TLessonChapterExample example=new TLessonChapterExample();
        example.createCriteria().andLessonIdEqualTo(Long.parseLong(lessonId));
        List<TLessonChapter> chapterList = tLessonChapterMapper.selectByExample(example);

        if(null!=chapterList && chapterList.size()>0){
            return chapterList;
        }
        return null;
    }
}

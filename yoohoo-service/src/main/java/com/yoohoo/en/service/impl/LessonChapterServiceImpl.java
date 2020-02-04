package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.mapper.LessonDao;
import com.yoohoo.en.dao.mapper.TClassMapper;
import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.ext.TeacherLessonPeriodInfo;
import com.yoohoo.en.service.IClassScheduleService;
import com.yoohoo.en.service.ILessonChapterService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yoohoo.en.constant.ApplicationConstant.CheckStatus;

@Service
public class LessonChapterServiceImpl implements ILessonChapterService
{
    @Autowired
    private TLessonChapterMapper lessonChapterMapper;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private TClassMapper tClassMapper;

    @Autowired
    private TClassScheduleMapper tClassScheduleMapper;

    @Autowired
    private IClassScheduleService classScheduleService;

    @Override
    public int insert(TLessonChapter chapter)
    {
        lessonChapterMapper.insert(chapter);
        return chapter.getChapterId();
    }

    @Override
    public int inserBatch(List<TLessonChapter> chapters)
    {
        if (CollectionUtils.isEmpty(chapters))
        {
            return 0;
        }
        int count = lessonChapterMapper.insertBatch(chapters);

//        List<TeacherLessonPeriodInfo> scheduleIdList = lessonDao.queryLessonChapterListNotClassSchedule(chapters.get(0).getLessonId());
//        if(CollectionUtils.isNotEmpty(scheduleIdList))
//        {
//            for(TeacherLessonPeriodInfo periodInfo:scheduleIdList)
//            {
//                TClassSchedule classSchedule=new TClassSchedule();
//                classSchedule.setTeacherId(periodInfo.getTeacherId());
//                classSchedule.setClassId(periodInfo.getClassId());
//                classSchedule.setLessonId(periodInfo.getLessonId());
//                classSchedule.setEteacherId(periodInfo.getEteacherId());
//                classSchedule.setMasterteacherId(periodInfo.getMasterteacherId());
//                classSchedule.setChapterId(periodInfo.getChapterId());
//                classSchedule.setStatus(0);
//                classSchedule.setCheckStatus(CheckStatus.NOTCHECK.getValue());
//                tClassScheduleMapper.insertSelective(classSchedule);
//                classScheduleService.syncStuSchedule(classSchedule);
//            }
//        }
        return count;
    }

    @Override
    public int update(TLessonChapter chapter)
    {
        return lessonChapterMapper.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public List<TLessonChapter> query(TLessonChapterExample example)
    {
        return lessonChapterMapper.selectByExample(example);
    }

    @Override
    public int updateBatch(List<TLessonChapter> chapters)
    {
        if (CollectionUtils.isEmpty(chapters))
        {
            return 0;
        }

        for (TLessonChapter chapter : chapters)
        {
            update(chapter);
        }
        return chapters.size();
    }

    @Override
    public int delete(TLessonChapterExample example)
    {
        return lessonChapterMapper.deleteByExample(example);
    }

    @Override
    public int countChapter(Long lessonId)
    {
        TLessonChapterExample example = new TLessonChapterExample();
        example.createCriteria().andLessonIdEqualTo(lessonId);
        return lessonChapterMapper.countByExample(example);
    }

}

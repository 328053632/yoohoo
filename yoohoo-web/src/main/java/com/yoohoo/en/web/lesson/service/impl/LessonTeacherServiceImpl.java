package com.yoohoo.en.web.lesson.service.impl;

import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.mapper.TLessonInfoMapper;
import com.yoohoo.en.dao.mapper.TLessonTeacherMapper;
import com.yoohoo.en.dao.mapper.TLessonTeacherRelationMapper;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;
import com.yoohoo.en.dao.model.TLessonTeacher;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherInfoExample;
import com.yoohoo.en.web.lesson.service.LessonTeacherService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created By LiWenLong On 2018/8/10 14:06
 * E-Mail:it_lwl@163.com
 */
@Service
public class LessonTeacherServiceImpl implements LessonTeacherService {
    @Autowired
    TLessonTeacherRelationMapper tLessonTeacherRelationMapper;

    @Autowired
    private TLessonTeacherMapper tLessonTeacherMapper;
    @Autowired
    private TTeacherInfoMapper tTeacherInfoMapper;
    @Autowired
    private TLessonInfoMapper tLessonInfoMapper;
    @Autowired
    private TLessonChapterMapper tLessonChapterMapper;
	@Override
	public List<TLessonTeacher> queryListByLessonId(Integer lessonId) {
		TLessonTeacher query = new TLessonTeacher();
		query.setLessonId(lessonId);
		query.setStatus(1);
		List<TLessonTeacher> list = tLessonTeacherMapper.select(query);
		if(CollectionUtils.isNotEmpty(list)){
			TTeacherInfoExample example=new TTeacherInfoExample();
			example.createCriteria().andTeacherIdIn(list.stream().map(TLessonTeacher::getTeacherId).collect(Collectors.toList()));
	        List<TTeacherInfo> tTeacherInfoList = tTeacherInfoMapper.selectByExample(example);
	        if(CollectionUtils.isNotEmpty(tTeacherInfoList)){
        		Map<Integer, TTeacherInfo> teacherMap = tTeacherInfoList.stream().collect(Collectors.toMap(TTeacherInfo::getTeacherId, Function.identity()));
        		for(TLessonTeacher e:list){
        			TTeacherInfo t = teacherMap.get(e.getTeacherId().intValue());
        			if(t == null){
        				continue;
        			}
        			e.setTeacherName(t.getName());
        		}
	        }
		}
		return list;
	}
	
	@Override
	public List<TLessonTeacher> queryHasFileListByLessonId(Integer lessonId) {
		List<TLessonTeacher> list = queryListByLessonId(lessonId);
		List<Long> teacherIdList = list.stream().map(TLessonTeacher::getTeacherId).distinct().collect(Collectors.toList());
		TLessonChapterExample tLessonChapterExample = new TLessonChapterExample();
		TLessonChapterExample.Criteria criteria = tLessonChapterExample.createCriteria();
        criteria.andUserIdIn(teacherIdList);
        List<TLessonChapter> tlcList = tLessonChapterMapper.selectByExample(tLessonChapterExample);
        if(CollectionUtils.isEmpty(tlcList)){
        	return null;
        }
        List<TLessonTeacher> listNew = new ArrayList<>();
        Set<Long> hasFileUserIdSet = tlcList.stream().map(TLessonChapter::getAddUserId).collect(Collectors.toSet());
        for(TLessonTeacher e:list){
        	if(hasFileUserIdSet.contains(e.getTeacherId())){
            	listNew.add(e);
        	}
        }
		return listNew;
	}


	@Override
	public List<TLessonTeacher> queryListByTeacherId(Long teacherId) {
		TLessonTeacher query = new TLessonTeacher();
		query.setTeacherId(teacherId);
		query.setStatus(1);
		List<TLessonTeacher> list = tLessonTeacherMapper.select(query);
		if(CollectionUtils.isNotEmpty(list)){
			TLessonInfoExample example=new TLessonInfoExample();
			example.createCriteria().andLessonIdIn(list.stream().map(TLessonTeacher::getLessonId).collect(Collectors.toList()));
	        List<TLessonInfo> tLessonInfoList = tLessonInfoMapper.selectByExample(example);
	        if(CollectionUtils.isNotEmpty(tLessonInfoList)){
        		Map<Long, TLessonInfo> lessonMap = tLessonInfoList.stream().collect(Collectors.toMap(TLessonInfo::getLessonId, Function.identity()));
        		for(TLessonTeacher e:list){
        			TLessonInfo l = lessonMap.get(e.getLessonId());
        			if(l == null){
        				continue;
        			}
        			e.setTeacherName(l.getTitle());
        		}
	        }
		}
		return list;
	}


	@Override
	public void saveBatchLessonTeacher(List<TLessonTeacher> list, Integer userId) {
		for (TLessonTeacher e:list) {
			e.setAddBaseInfo(userId);
		}
		tLessonTeacherMapper.insertList(list);
	}
}

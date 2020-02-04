package com.yoohoo.en.lesson.service.impl;

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
import com.yoohoo.en.dao.model.TLessonTeacherRelation;
import com.yoohoo.en.dao.model.TLessonTeacherRelationExample;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherInfoExample;
import com.yoohoo.en.lesson.service.LessonTeacherService;
import com.yoohoo.en.mcore.utils.ShiroUtils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
    @Override
        public void saveBatchTeacherSchedule(List<TLessonTeacherRelation> list) {
        if(null !=list && list.size()>0){
            //进行删除老师，当前存储的集合中没有但是数据库中有的进行删除操作
            deleteBatchTeacher(list.get(0).getLessonId(),list.get(0).getPositionType());
            List<TLessonTeacherRelation>  tLessonTeacherRelationList=new ArrayList<>();
            Integer LessonId= list.get(0).getLessonId();
            //1.创建一个用于存储 当前要存储老师的id的集合
            List<Integer> teacherIds=new ArrayList<>();
            //2.创建一个如果需要添加用户的 用于存储关系的集合
            List<TLessonTeacherRelation> addList=new ArrayList<>();
            //3.遍历现在集合
            for (TLessonTeacherRelation tLessonTeacherRelation:list) {
            	tLessonTeacherRelation.setAddBaseInfo(ShiroUtils.getUserId());
                //1.存储id到存储老师的集合中
                teacherIds.add(tLessonTeacherRelation.getTeacherId());
                tLessonTeacherRelationList.add(tLessonTeacherRelation);
                //2.创建一个查询条件 使用lessonId 和 teacherId来进行关联
                TLessonTeacherRelationExample  example=new TLessonTeacherRelationExample();
                example.createCriteria().andLessonIdEqualTo(LessonId).andTeacherIdEqualTo(tLessonTeacherRelation.getTeacherId()).andPositionTypeEqualTo(tLessonTeacherRelation.getPositionType());
                //3.进行条件查询
                List<TLessonTeacherRelation> tLessonTeacherRelations = tLessonTeacherRelationMapper.selectByExample(example);
                //4.如果存在,修改状态即可
                if(null!=tLessonTeacherRelations && tLessonTeacherRelations.size()>0){
                    TLessonTeacherRelation bean=tLessonTeacherRelations.get(0);
                    //修改数据库中已经存在的状态
                    bean.setStatus(1);
                    //更新数据库
                    tLessonTeacherRelationMapper.updateByExampleSelective(bean,example);
                }else{
                    //不存在那么说明是要新添加的
                    addList.add(tLessonTeacherRelation);
                }
            }
            if(addList.size()>0){
                tLessonTeacherRelationMapper.insertBatch(addList);
            }
        }
    }

    //删除数组中不存在但是数据库中存在的数据
    private void deleteBatchTeacher(Integer lessonId, Integer positionType) {
        TLessonTeacherRelationExample e=new TLessonTeacherRelationExample();
        TLessonTeacherRelationExample.Criteria c= e.createCriteria();
        c.andLessonIdEqualTo(lessonId).andPositionTypeEqualTo(positionType);
        TLessonTeacherRelation r = new TLessonTeacherRelation();
        r.setStatus(0);
        tLessonTeacherRelationMapper.updateByExampleSelective(r,e);
    }


        //删除数组中不存在但是数据库中存在的数据
        private void deleteBatchTeacher(Integer lessonId, List<Integer> notInTeacherList,List<TLessonTeacherRelation> tLessonTeacherRelationList) {
            List<Integer> collect = notInTeacherList.stream().distinct().collect(Collectors.toList());
            List<Integer> positionType=new ArrayList<>();

            /**
             * 先根据老师ID进行删除
             *
             */
            TLessonTeacherRelationExample example=new TLessonTeacherRelationExample();
            TLessonTeacherRelationExample.Criteria criteria = example.createCriteria();
            criteria.andLessonIdEqualTo(lessonId);
            //如果新增加的老师不为null
            if(CollectionUtils.isNotEmpty(notInTeacherList)){
                //增加not in 条件
                criteria.andTeacherIdNotIn(notInTeacherList);
            }

            //增加完条件直接进行更新操作将不属于这个lesson 的id  和课程老师状态改为0
            TLessonTeacherRelation record=new TLessonTeacherRelation();
            record.setStatus(0);
            tLessonTeacherRelationMapper.updateByExampleSelective(record,example);
            /**
             *
             * 根据老师id 和老师类型 来删除当前老师id的要删除其他的类型
             */
            for(Integer teacherId:collect){
                positionType.clear();
                for(TLessonTeacherRelation tLessonTeacherRelation:tLessonTeacherRelationList){
                    if(teacherId.equals(tLessonTeacherRelation.getTeacherId())){
                        System.out.println(tLessonTeacherRelation);
                        positionType.add(tLessonTeacherRelation.getPositionType());
                    }
                }
                /**
                 *
                 * 在这里进行删除操作
                 */
                TLessonTeacherRelationExample e=new TLessonTeacherRelationExample();
                TLessonTeacherRelationExample.Criteria c= e.createCriteria();
                c.andLessonIdEqualTo(lessonId).andTeacherIdEqualTo(teacherId);
                if(CollectionUtils.isNotEmpty(positionType)){
                    c.andPositionTypeNotIn(positionType);
                }
                TLessonTeacherRelation  r=new TLessonTeacherRelation();
                r.setStatus(0);
                tLessonTeacherRelationMapper.updateByExampleSelective(r,e);
            }
        }

   /* public void saveBatchStuSchedule(List<TLessonTeacherRelation> list) {
        List<TLessonTeacherRelation> signleList=new ArrayList<>();
        for (TLessonTeacherRelation tLessonTeacherRelation:list) {

            TLessonTeacherRelationExample example=new TLessonTeacherRelationExample();
            example.createCriteria().andLessonIdEqualTo(tLessonTeacherRelation.getLessonId()).andTeacherIdEqualTo(tLessonTeacherRelation.getTeacherId());
            //去掉重复的值防止重复插入
            if(tLessonTeacherRelationMapper.countByExample(example)>0){
                //使用continue;来直接结束单次循环
                continue;
            }
            signleList.add(tLessonTeacherRelation);

        }
        if(signleList.size()>0){
            tLessonTeacherRelationMapper.insertBatch(signleList);
        }
    }*/

    @Override
    public List<TLessonTeacherRelation> queryListByPositionType(Integer lessonId,Integer positionType) {
        List<TLessonTeacherRelation> list=   tLessonTeacherRelationMapper.queryList(lessonId,positionType);
        if(list!=null){
            return list;
        }
        return null;
    }

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
        			TLessonInfo l = lessonMap.get(e.getLessonId().longValue());
        			if(l == null){
        				continue;
        			}
        			e.setLessonName(l.getTitle());
        		}
	        }
		}
		return list;
	}


	@Override
	public void saveBatchLessonTeacher(List<TLessonTeacher> list) {
		for (TLessonTeacher e:list) {
			e.setId(null);
			e.setAddBaseInfo(ShiroUtils.getUserId());
			e.setStatus(1);
			tLessonTeacherMapper.insert(e);
		}
//		tLessonTeacherMapper.insertList(list);
	}


	@Override
	public void updateLessonTeacher(TLessonTeacher tLessonTeacher) {
		tLessonTeacherMapper.updateByPrimaryKey(tLessonTeacher);
	}


	@Override
	public void deleteLessonTeacher(Long lessonId) {
		Example example = new Example(TLessonTeacher.class);
		Criteria  criteria = example.createCriteria();
		criteria.andEqualTo("lessonId", lessonId);
		TLessonTeacher tLessonTeacher = new TLessonTeacher();
		tLessonTeacher.setStatus(0);
		tLessonTeacherMapper.updateByExampleSelective(tLessonTeacher, example);
	}


	@Override
	public void deleteTeacherLesson(Long teacherId) {
		Example example = new Example(TLessonTeacher.class);
		Criteria  criteria = example.createCriteria();
		criteria.andEqualTo("teacherId", teacherId);
		TLessonTeacher tLessonTeacher = new TLessonTeacher();
		tLessonTeacher.setStatus(0);
		tLessonTeacherMapper.updateByExampleSelective(tLessonTeacher, example);
	}
}

package com.yoohoo.en.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.mapper.LessonDao;
import com.yoohoo.en.dao.mapper.TCategoryInfoMapper;
import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.mapper.TLessonInfoMapper;
import com.yoohoo.en.dao.mapper.TStudentLessonScheduleMapper;
import com.yoohoo.en.dao.model.TCategoryInfo;
import com.yoohoo.en.dao.model.TCategoryInfoExample;
import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonChapterExample;
import com.yoohoo.en.dao.model.TLessonInfo;
import com.yoohoo.en.dao.model.TLessonInfoExample;
import com.yoohoo.en.dao.model.TLessonInfoExample.Criteria;
import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.TStudentLessonScheduleExample;
import com.yoohoo.en.dao.model.ext.LessonInfoExt;
import com.yoohoo.en.dao.model.ext.TLessonChapterExt;
import com.yoohoo.en.model.PageModel;
import com.yoohoo.en.service.ILessonInfoService;

@Service
public class LessonInfoServiceImpl implements ILessonInfoService {
    @Autowired
    private TLessonInfoMapper lessonInfoMapper;

    @Autowired
    private TLessonChapterMapper lessonChapterMapper;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private TStudentLessonScheduleMapper studentLessonScheduleMapper;

    @Autowired
    private TCategoryInfoMapper categoryInfoMapper;

    @Override
    public List<TLessonInfo> queryList(TLessonInfoExample example) {
    	List<TLessonInfo> list = lessonInfoMapper.selectByExample(example);
    	if(CollectionUtils.isNotEmpty(list)){
    		List<TLessonInfo> countList = studentLessonScheduleMapper.queryLessonSchedulCount(list.stream().map(TLessonInfo::getLessonId).collect(Collectors.toList()));
        	if(CollectionUtils.isNotEmpty(countList)){
        		Map<Long, TLessonInfo> countMap = countList.stream().collect(Collectors.toMap(TLessonInfo::getLessonId, Function.identity()));
            	for(TLessonInfo e:list){
            		if(countMap.containsKey(e.getLessonId())){
                		e.setClassBeginCount(countMap.get(e.getLessonId()).getClassBeginCount());
            		}
            	}
        	}
    	}
        return list;
    }

    @Override
    public int count(TLessonInfoExample example) {
        return lessonInfoMapper.countByExample(example);
    }

    @Override
    public TLessonInfo queryById(Long lessonId) {
        return lessonInfoMapper.selectByPrimaryKey(lessonId);
    }

    @Override
    public Long insert(TLessonInfo lessonInfo) {
        lessonInfoMapper.insert(lessonInfo);
        return lessonInfo.getLessonId();
    }

    @Override
    public int update(TLessonInfo lessonInfo) {
        return lessonInfoMapper.updateByPrimaryKeySelective(lessonInfo);
    }

    @Override
    public int delete(int lessonId) {
        return lessonInfoMapper.deleteByPrimaryKey(lessonId);
    }

    @Override
    public int deleteBatch(List<Integer> lessonIds) {
        TLessonInfoExample example = new TLessonInfoExample();
        example.createCriteria().andLessonIdIn(lessonIds);
        return lessonInfoMapper.deleteByExample(example);
    }

    /**
     * @param lessonName
     * @param lessonType 课程类型 1系统课程 2拓展课程
     * @param studentId
     * @return
     */
    @Override
    public BaseResp queryLessons2(String lessonName, String lessonType, Integer studentId) throws InvocationTargetException, IllegalAccessException {
        BaseResp resp = new BaseResp();
        TLessonInfoExample example = new TLessonInfoExample();
        example.setOrderByClause("addtime desc");
        Criteria cri = example.createCriteria();
        if (StringUtils.isNotEmpty(lessonName)) {
            cri.andTitleLike("%" + lessonName + "%");
        }
        if (StringUtils.isNotEmpty(lessonType)) {
            try {
                cri.andLessonTypeEqualTo(Integer.valueOf(lessonType));
            } catch (NumberFormatException e) {
            }
        }
        List<TLessonInfo> lessonList = lessonInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(lessonList)) {
            resp.getData().put("lessonList", LessonInfo2LessonInfoExt(lessonList));
        }
        return resp;
    }

    @Override
    public BaseResp queryLessons(String lessonName, int categoryId, int pageNo, int limit, Integer type) throws InvocationTargetException, IllegalAccessException {
        BaseResp resp = new BaseResp();
        TLessonInfoExample example = new TLessonInfoExample();
        example.setOrderByClause("addtime desc");                   //以此字段排序
        int start = (pageNo - 1) * limit;
        example.setPageHelper(new PageHelper(start, limit));
        Criteria cri = example.createCriteria();
        if (StringUtils.isNotEmpty(lessonName)) {
            cri.andTitleLike("%" + lessonName + "%");
        }
        if (categoryId > 0) {
            cri.andCategoryIdIn(categoryIds(categoryId));
        }

        cri.andStatusEqualTo(1);
        if(type==1){
            //身份为老师
            cri.andLessonTypeBetween(1,2);
        }else {
            cri.andLessonTypeEqualTo(1);
        }
        List<TLessonInfo> lessonList = lessonInfoMapper.selectByExample(example);

        int total = lessonInfoMapper.countByExample(example);

        if (CollectionUtils.isNotEmpty(lessonList)) {
            //resp.getData().put("lessonList",lessonList);
            resp.getData().put("lessonList", LessonInfo2LessonInfoExt(lessonList));
            resp.getData().put("page", new PageModel<>(pageNo, limit, LessonInfo2LessonInfoExt(lessonList), total));
        }
        return resp;
    }

    /**
     * 查询课程库章节路径
     *
     * @param lessonId
     * @param
     * @param
     * @return
     */
    @Override
    public BaseResp queryChapters(Long lessonId) {
        BaseResp resp = new BaseResp();
        TLessonChapterExample example = new TLessonChapterExample();
        example.setOrderByClause("order_num asc");
        //int start = (pageNo - 1) * limit;
        //example.setPageHelper(new PageHelper(start, limit));
        TLessonChapterExample.Criteria criteria = example.createCriteria();
        criteria.andLessonIdEqualTo(lessonId);
        List<TLessonChapter> chapterList = lessonChapterMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(chapterList)) {
            List<TLessonChapter> cxList = new ArrayList<>(chapterList.size());
            for (TLessonChapter c : chapterList) {
                cxList.add(new TLessonChapterExt().copyFrom(c));
            }
            //chapterList.addAll(cxList);
            resp.getData().put("chapterList", chapterList);
        }
        return resp;
    }

    @Override
    public void LessonUnder(Integer lessonId) {
        TLessonInfoExample example=new TLessonInfoExample();
        example.createCriteria().andLessonIdEqualTo(lessonId);
        TLessonInfo record=new TLessonInfo();
        record.setStatus(0);
        lessonInfoMapper.updateByExampleSelective(record,example);
    }

    @Override
    public void LessonUp(Integer lessonId) {

        TLessonInfoExample example=new TLessonInfoExample();
        example.createCriteria().andLessonIdEqualTo(lessonId);
        TLessonInfo record=new TLessonInfo();
        record.setStatus(1);
        lessonInfoMapper.updateByExampleSelective(record,example);
    }


    private List<Integer> categoryIds(int categoryId) {
        List<Integer> ids = new ArrayList<>();
        ids.add(categoryId);
        TCategoryInfoExample example = new TCategoryInfoExample();
        example.createCriteria().andParentIdEqualTo(categoryId);
        List<TCategoryInfo> tCategoryInfos = categoryInfoMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(tCategoryInfos)) {
            for (TCategoryInfo tCategoryInfo : tCategoryInfos) {
                ids.add(tCategoryInfo.getId());
            }
        }
        return ids;
    }

    private List<LessonInfoExt> LessonInfo2LessonInfoExt(List<TLessonInfo> lessonList) throws InvocationTargetException, IllegalAccessException {
        List<LessonInfoExt> ls = new ArrayList<LessonInfoExt>();
        for (TLessonInfo l : lessonList) {
            LessonInfoExt le = new LessonInfoExt();
          //  le.copyFrom(l);
            BeanUtils.copyProperties(le,l);
            TLessonChapterExample lcExample = new TLessonChapterExample();
            lcExample.setOrderByClause("order_num asc");
            lcExample.createCriteria().andLessonIdEqualTo(l.getLessonId());

            List<TLessonChapter> chapterList = lessonChapterMapper.selectByExample(lcExample);
            if (CollectionUtils.isNotEmpty(chapterList)) {
                List<TLessonChapterExt> cxList = new ArrayList<>(chapterList.size());
                for (TLessonChapter c : chapterList) {
                   // cxList.add(new TLessonChapterExt().copyFrom(c));
                TLessonChapterExt tcExt=    new TLessonChapterExt();
                    BeanUtils.copyProperties(tcExt,c);
                    cxList.add(tcExt);
                //  BeanUtils.copyProperties(cxList,c);
                }
                le.setChapterList(cxList);
            }
            ls.add(le);
        }
        return ls;
    }

    @Override
    public String checkLessonInfo(TLessonInfo l) {

        if (StringUtils.isEmpty(l.getTitle())) {
            return "课程名称不能为空";
        }

        TLessonInfoExample example = new TLessonInfoExample();
        TLessonInfoExample.Criteria cri = example.createCriteria().andTitleEqualTo(l.getTitle());
        if (null != l.getLessonId()) {
            cri.andLessonIdNotEqualTo(l.getLessonId());
        }
        if (lessonInfoMapper.countByExample(example) > 0) {
            return "课程名称不能重复";
        }
        return null;
    }

    /**
     * 申请测评试听
     * @param userId
     * @param lessonId
     * @param chapterId
     * @return
     */

    @Override
    public BaseResp applyStatus(Long userId, int lessonId, int chapterId) {
        BaseResp resp = new BaseResp();
        TStudentLessonScheduleExample example = new TStudentLessonScheduleExample();
        example.createCriteria().andStudentIdEqualTo(userId)
                .andLessonIdEqualTo(Long.valueOf(lessonId))
                .andChapterIdEqualTo(chapterId);
        List<TStudentLessonSchedule> tStudentLessonSchedules = studentLessonScheduleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(tStudentLessonSchedules)) {
            for (TStudentLessonSchedule tStudentLessonSchedule : tStudentLessonSchedules) {
                switch (tStudentLessonSchedule.getType()) {
                    case 1:
                        resp.put("normal", true);
                        break;
                    case 3:
                        resp.put("pangTing", true);
                        break;
                    case 4:
                        resp.put("shiTing", true);
                        break;
                    case 5:
                        resp.put("pingCe", true);
                        break;
                    default:
                        resp.put("normal", true);
                        break;
                }
            }
        }
        return resp;
    }

}

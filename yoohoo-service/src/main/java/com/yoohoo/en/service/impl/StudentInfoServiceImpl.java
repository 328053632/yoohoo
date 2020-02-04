package com.yoohoo.en.service.impl;

import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.*;
import com.yoohoo.en.dao.model.*;
import com.yoohoo.en.dao.model.ext.LessonInfoExt;
import com.yoohoo.en.dao.model.ext.TLessonChapterExt;
import com.yoohoo.en.service.IStudentInfoService;
import com.yoohoo.en.service.ISystemConfigService;
import com.yoohoo.en.utils.SendSmsUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService
{
    @Autowired
    private TStudentInfoMapper studentInfoMapper;

    @Autowired
    private ISystemConfigService configService;

    @Autowired
    private TVerifycodeMapper verifycodeMapper;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private TStudentLearnPathMapper tStudentLearnPathMapper;

    @Autowired
    private TLessonChapterMapper tLessonChapterMapper;

    @Autowired
    private TLessonInfoMapper tLessonInfoMapper;

    @Override
    public TStudentInfo query(String account, String password)
    {
        TStudentInfoExample example = new TStudentInfoExample();
        example.createCriteria().andMsisdnEqualTo(account).andPasswdEqualTo(password);
        List<TStudentInfo> tStudentInfos = studentInfoMapper.selectByExampleXml(example);
        if (CollectionUtils.isEmpty(tStudentInfos))
        {
            return null;
        }
        return tStudentInfos.get(0);
    }

    @Override
    public TStudentInfo query(String account)
    {
        return gettStudentInfo(account,null);
    }

    @Override
    public TStudentInfo queryRegistered(String account)
    {
        return gettStudentInfo(account,"1");
    }

    public TStudentInfo gettStudentInfo(String account,String regStatus) {
        TStudentInfoExample example = new TStudentInfoExample();
        TStudentInfoExample.Criteria cri = example.createCriteria().andMsisdnEqualTo(account).andStatusEqualTo(ApplicationConstant.USER_STATUS_NORMAL);
        if(StringUtils.isNotEmpty(regStatus))
        {
            cri.andRegStatusEqualTo(regStatus);
        }
        List<TStudentInfo> tStudentInfos = studentInfoMapper.selectByExampleXml(example);
        if (CollectionUtils.isEmpty(tStudentInfos))
        {
            return null;
        }
        return tStudentInfos.get(0);
    }

    @Override
    public TStudentInfo query(int id)
    {
        TStudentInfoExample example = new TStudentInfoExample();
        example.createCriteria().andUserIdEqualTo(id).andStatusEqualTo(ApplicationConstant.USER_STATUS_NORMAL);
        List<TStudentInfo> tStudentInfos = studentInfoMapper.selectByExampleXml(example);
        if (CollectionUtils.isEmpty(tStudentInfos))
        {
            return null;
        }
        return tStudentInfos.get(0);
    }

    @Override
    public Long insert(TStudentInfo studentInfo)
    {
        if (studentInfoMapper.insert(studentInfo) <= 0)
        {
            return 0l;
        }
        return studentInfo.getUserId();
    }

    @Override
    public boolean sendVerificationCode(String phone, int serviceType)
    {
        String verifyCodeStr = ((int)((Math.random() * 9 + 1) * 100000)) + "";

        if (SendSmsUtil.sendVerifyCodeSms(phone, verifyCodeStr, configService.getSmsConfig()))
        {
            Date time = new Date();
            TVerifycode verifycode = new TVerifycode();
            verifycode.setCreateTime(time);
            verifycode.setMsisdn(phone);
            verifycode.setVerifyCode(verifyCodeStr);
            verifycode.setServiceType(serviceType);
            verifycode.setOverTime(DateUtils.addMinutes(time, 10));
            verifycodeMapper.insert(verifycode);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(TStudentInfo studentInfo)
    {
        return studentInfoMapper.updateByPrimaryKeySelective(studentInfo) > 0;
    }

    @Override
    public TStudentInfo insertOrUpdate(TStudentInfo studentInfo)
    {
        TStudentInfo info = query(studentInfo.getMsisdn());
        if (info != null)
        {
            info.setPasswd(studentInfo.getPasswd());
            info.setMsisdn(studentInfo.getMsisdn());
            info.setRegStatus("1");
            info.setLastUpdateTime(new Date());
            update(info);
            return info;
        }
        studentInfo.setRegTime(new Date());
        insert(studentInfo);
        return studentInfo;
    }

	@Override
	public boolean checkUserIsExists(String msisdn) {
		TStudentInfoExample example=new TStudentInfoExample();
		example.createCriteria().andStatusEqualTo(ApplicationConstant.UserStatus.NORMAL.getValue())
                .andRegStatusEqualTo("1").andMsisdnEqualTo(msisdn);
		return studentInfoMapper.countByExampleXml(example) > 0;
	}

    @Override
    public List<LessonInfoExt> queryStudentLearnPath(Long studentId)
    {
        List<TLessonChapterExt> lessonEXList=lessonDao.queryLessonChapterLearnPath(studentId);
        if(CollectionUtils.isNotEmpty(lessonEXList))
        {
            List<Integer> chapterIds=new ArrayList<>();
            List<Integer> lessonIds=new ArrayList<>();
            List<Integer> pathsId=new ArrayList<>();

            for(TLessonChapterExt ce:lessonEXList)
            {
                if(!chapterIds.contains(ce.getChapterId()))
                {
                    chapterIds.add(ce.getChapterId());
                }
                if(!lessonIds.contains(ce.getLessonId()))
                {
                    lessonIds.add(ce.getLessonId());
                }
            }
            TStudentLearnPathExample slpExample=new TStudentLearnPathExample();
            slpExample.createCriteria().andChapterIdIn(chapterIds).andStudentIdEqualTo(studentId);
            List<TStudentLearnPath> learnPathList = tStudentLearnPathMapper.selectByExample(slpExample);
            if(CollectionUtils.isNotEmpty(learnPathList))
            {
                for(TStudentLearnPath p:learnPathList){
                    if(!pathsId.contains(p.getChapterId()))
                    {
                        pathsId.add(p.getChapterId());
                    }
                }
            }
            TLessonInfoExample lessonExample=new TLessonInfoExample();
            lessonExample.createCriteria().andLessonIdIn(lessonIds);
       //     lessonExample.setOrderByClause("title");
            List<TLessonInfo> lessonList = tLessonInfoMapper.selectByExample(lessonExample);
            if(CollectionUtils.isNotEmpty(lessonList))
            {
                List<LessonInfoExt> ls=new ArrayList<>(lessonList.size());
                Map<Long,LessonInfoExt> cache=new HashMap<>(lessonList.size());
                for(TLessonInfo l: lessonList)
                {
                    LessonInfoExt lessonExt=new LessonInfoExt();
                    lessonExt.copyFrom(l);
                    lessonExt.setChapterList(new ArrayList<TLessonChapterExt>());
                    ls.add(lessonExt);
                    cache.put(l.getLessonId(),lessonExt);
                }
                TLessonChapterExample chapterExaple=new TLessonChapterExample();
                chapterExaple.setOrderByClause("order_num");
                chapterExaple.createCriteria().andLessonIdIn(lessonIds).andChapterIdIn(chapterIds);
                List<TLessonChapter> chapterList = tLessonChapterMapper.selectByExample(chapterExaple);
                if(CollectionUtils.isNotEmpty(chapterList))
                {
                    for(TLessonChapter chapter:chapterList)
                    {
                        TLessonChapterExt cExt=new TLessonChapterExt();
                        cExt.copyFrom(chapter);
                        cExt.setLight(pathsId.contains(chapter.getChapterId()));
                        cache.get(chapter.getLessonId()).getChapterList().add(cExt);
                    }
                }
                cache.clear();
                chapterIds.clear();
                lessonIds.clear();
                pathsId.clear();
                return ls;
            }
        }
        return null;
    }
}

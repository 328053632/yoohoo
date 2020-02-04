package com.yoohoo.en.consume.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.bean.request.QueryConsumeRequest;
import com.yoohoo.en.bean.request.QueryStudentBalanceReq;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.constant.ResponseCode;
import com.yoohoo.en.consume.service.IConsumeService;
import com.yoohoo.en.dao.mapper.ConsumeDao;
import com.yoohoo.en.dao.mapper.TClassDefineMapper;
import com.yoohoo.en.dao.mapper.TClassMapper;
import com.yoohoo.en.dao.mapper.TClassScheduleMapper;
import com.yoohoo.en.dao.mapper.TStudentBalanceLogMapper;
import com.yoohoo.en.dao.mapper.TStudentInfoMapper;
import com.yoohoo.en.dao.mapper.TStudentLessonScheduleMapper;
import com.yoohoo.en.dao.model.TClass;
import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.TClassSchedule;
import com.yoohoo.en.dao.model.TStudentBalanceLog;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TStudentLessonSchedule;
import com.yoohoo.en.dao.model.TStudentLessonScheduleExample;
import com.yoohoo.en.dao.model.ext.ConsumePackInfo;
import com.yoohoo.en.dao.model.ext.StuTypeItem;
import com.yoohoo.en.dao.model.ext.StudentConsumeInfo;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.student.service.TStudentInfoService;
import com.yoohoo.en.utils.TypeItemUtils;

@Service
public class ConsumeServiceImpl implements IConsumeService {

	@Autowired
	private TStudentInfoMapper tStudentInfoMapper;

	@Autowired
	private TClassMapper tClassMapper;
	@Autowired
	private TClassDefineMapper tClassDefineMapper;

	@Autowired
	private ConsumeDao consumeDao;
	
	@Autowired
	private TClassScheduleMapper tClassScheduleMapper;
	
	@Autowired
	private TStudentLessonScheduleMapper tStudentLessonScheduleMapper;
	
	@Autowired
	private TStudentBalanceLogMapper tStudentBalanceLogMapper;


	@Autowired
	private TStudentInfoService tStudentInfoService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public List<ConsumePackInfo> queryCheckedConsumeList(QueryConsumeRequest req) {
		Long addUserId = null;
		if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId())){
			addUserId = ShiroUtils.getUserId();
		}
		List<ConsumePackInfo> datas = consumeDao.queryClassScheduleExtByCondition(req.getLessonId(), req.getClassId(), req.getClassItemId(), req.getDateStr(), addUserId);
		if (CollectionUtils.isNotEmpty(datas)) {
			for (ConsumePackInfo sci : datas) {
                String s = tClassMapper.selectTeacherName(sci.getClassId());
                sci.setAssistantName(s);
                TClass tClass = tClassMapper.selectByPrimaryKey(sci.getClassId());
                sci.setClassType(tClass.getClassType());
                //0未上课 1课中  2已上过课
				if(null != sci.getEndTime() && sci.getEndTime().before(new Date(System.currentTimeMillis()-ApplicationConstant.LESSON_CONFIRM_AFTER_CONSUME)))
				{
					sci.setTeachStatus(2);
				}else if(null != sci.getLessonTime() && sci.getLessonTime().before(new Date(System.currentTimeMillis()+ApplicationConstant.LESSON_CONFIRM_AFTER_CONSUME))){
					if(Integer.compare(sci.getStatus(), 2) ==0)
					{
						sci.setTeachStatus(2);
					}else{
						sci.setTeachStatus(1);
					}
				}else{
					sci.setTeachStatus(0);
				}

			List<StudentConsumeInfo> userList =new ArrayList<>();
					List<StudentConsumeInfo> studentScheduleList = consumeDao
							.queryStudentConsumeByScheduleId(sci.getScheduleId());
					if (CollectionUtils.isNotEmpty(studentScheduleList)) {

					}
					if (CollectionUtils.isNotEmpty(studentScheduleList)) {
						userList.addAll(studentScheduleList);
					}
					sci.setStudentConsumeList(userList);
				//}
				if (CollectionUtils.isNotEmpty(sci.getStudentConsumeList())) {
//					TClassDefine classItem = tClassDefineMapper.selectByPrimaryKey(sci.getClassItemId());
					for (StudentConsumeInfo consume : sci.getStudentConsumeList()) {
						
//						TypeItemUtils.buildStuTypeItemType( consume);
						if(ApplicationConstant.CheckStatus.CHECKED.eq(sci.getCheckStatus()))
						{
							consume.setPrice((null == consume.getAmount()?0:consume.getAmount()));
						}else{
//							TypeItemUtils.buildStuTypeItemDefaultPrice(classItem, consume);
							consume.setPrice(null);
						}
					}
				}
			}
		}
		return datas;
	}

	@Override
	public List<StuTypeItem> queryStuTypeItemList(Integer classItemId) {
		TClassDefine classItem = tClassDefineMapper.selectByPrimaryKey(classItemId);
		List<StuTypeItem> datas = TypeItemUtils.getStuTypeItems();
		for(StuTypeItem item: datas)
		{
			TypeItemUtils.buildStuTypeItemType(item);
			TypeItemUtils.buildStuTypeItemDefaultPrice(classItem, item);
		}
		return datas;
	}

	@Override
	public BaseResp sureClassLessonScheduleAndStudentConsume(ConsumePackInfo cpg) {
		BaseResp  resp=new BaseResp();
		TClassSchedule classSchedule = tClassScheduleMapper.selectByPrimaryKey(cpg.getScheduleId());
		if(null != classSchedule && !ApplicationConstant.CheckStatus.CHECKED.eq(classSchedule.getCheckStatus()))
		{
			TClassSchedule record=new TClassSchedule();
			record.setCheckStatus(ApplicationConstant.CheckStatus.CHECKED.getValue());
			record.setScheduleId(classSchedule.getScheduleId());
			tClassScheduleMapper.updateByPrimaryKeySelective(record);
			
			Date now=new Date();
			List<StudentConsumeInfo> consumeList = cpg.getStudentConsumeList();
			if(CollectionUtils.isNotEmpty(consumeList))
			{
				for(StudentConsumeInfo consume:consumeList)
				{
					TStudentLessonScheduleExample ssExample=new TStudentLessonScheduleExample();
					TStudentLessonScheduleExample.Criteria ssCri = ssExample.createCriteria();
					if(null != consume.getRecordId())
					{
						ssCri.andRecordIdEqualTo(consume.getRecordId());
					}else{
						ssCri.andScheduleIdEqualTo(consume.getScheduleId())
						.andLessonIdEqualTo(classSchedule.getLessonId())
						.andChapterIdEqualTo(classSchedule.getChapterId())
						.andStudentIdEqualTo(consume.getStudentId());
					}
					List<TStudentLessonSchedule> stuSchedules = tStudentLessonScheduleMapper.selectByExample(ssExample);
					TStudentLessonSchedule stuExamp=null;
					if(CollectionUtils.isNotEmpty(stuSchedules))
					{
						stuExamp=stuSchedules.get(0);
						stuExamp.setType(consume.getType());
						stuExamp.setLessonTime(classSchedule.getLessonTime());
						stuExamp.setStatus(ApplicationConstant.CheckStatus.CHECKED.getValue());
						tStudentLessonScheduleMapper.updateByExampleSelective(stuExamp, ssExample);
					}else{
						stuExamp=new TStudentLessonSchedule();
						stuExamp.setScheduleId(classSchedule.getScheduleId());
						stuExamp.setStudentId(consume.getScheduleId());
						stuExamp.setLessonId(classSchedule.getLessonId());
						stuExamp.setChapterId(classSchedule.getChapterId());
						stuExamp.setType(consume.getType());
						stuExamp.setStatus(ApplicationConstant.CheckStatus.CHECKED.getValue());
						stuExamp.setLessonTime(classSchedule.getLessonTime());
						stuExamp.setAddtime(now);
						stuExamp.setAddBaseInfo(ShiroUtils.getUserId());
						tStudentLessonScheduleMapper.insertSelective(stuExamp);
					}
					if(null != stuExamp && null != stuExamp.getRecordId())
					{
						TStudentBalanceLog blanceRecord=new TStudentBalanceLog();
						blanceRecord.setUserId(stuExamp.getStudentId());
						blanceRecord.setbType(stuExamp.getType());
						blanceRecord.setType(ApplicationConstant.BlanceType.CONSUME.getValue());
						blanceRecord.setScheduleId(stuExamp.getScheduleId());
						blanceRecord.setRecordId(stuExamp.getRecordId());
						blanceRecord.setAmount(consume.getPrice());
						blanceRecord.setStatus(ApplicationConstant.BlacneLogStatus.SURE.getValue());
						blanceRecord.setAddtime(now);
						blanceRecord.setMark(consume.getTypeName());
						blanceRecord.setMemo(consume.getMemo());
						blanceRecord.setAddBaseInfo(ShiroUtils.getUserId());
						if(tStudentBalanceLogMapper.insertSelective(blanceRecord) >0)
						{
							consumeDao.changeStudentBlance(stuExamp.getStudentId(),0-consume.getPrice(),null);
							TStudentInfo studentInfo = tStudentInfoMapper.selectByPrimaryKey(stuExamp.getStudentId());
							blanceRecord.setAfterBalance(studentInfo.getBalance());
							//更新余额记录
							tStudentBalanceLogMapper.updateByPrimaryKeySelective(blanceRecord);
							stuExamp.setLogId(blanceRecord.getLogId());
							tStudentLessonScheduleMapper.updateByPrimaryKey(stuExamp);
						}
					}
					/**
					 * 自动亮灯
					 */
					tStudentInfoService.addLearnPath(consume.getStudentId(),cpg.getLessonId().intValue(),cpg.getChapterId());
				}
			}
		}else{
			resp.setResultCodeInfo(ResponseCode.CONSUME_RECORD_HAS_SURED);
			return resp;
		}
		return resp;
	}

	@Override
	public List<TStudentBalanceLog> queryStudentBalanceLogList(QueryStudentBalanceReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countStudentBalanceLog(QueryStudentBalanceReq req) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String queryTeacher(Integer classId) {
        String s = tClassMapper.selectTeacherName(Long.valueOf(classId));
        return s;
	}

}

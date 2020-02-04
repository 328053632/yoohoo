package com.yoohoo.en.student.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.yoohoo.en.bean.request.RechargeRequest;
import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.ConsumeDao;
import com.yoohoo.en.dao.mapper.LessonDao;
import com.yoohoo.en.dao.mapper.TClassStudentRMapper;
import com.yoohoo.en.dao.mapper.TLessonChapterMapper;
import com.yoohoo.en.dao.mapper.TLessonInfoMapper;
import com.yoohoo.en.dao.mapper.TStudentBalanceLogMapper;
import com.yoohoo.en.dao.mapper.TStudentInfoMapper;
import com.yoohoo.en.dao.mapper.TStudentLearnPathMapper;
import com.yoohoo.en.dao.model.TClassStudentR;
import com.yoohoo.en.dao.model.TClassStudentRExample;
import com.yoohoo.en.dao.model.TStudentBalanceLog;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.dao.model.TStudentInfoExample;
import com.yoohoo.en.dao.model.TStudentLearnPath;
import com.yoohoo.en.dao.model.TStudentLearnPathExample;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.service.IClassService;
import com.yoohoo.en.student.dao.TStudentInfoDao;
import com.yoohoo.en.student.entity.TStudentInfoEntity;
import com.yoohoo.en.student.service.TStudentInfoService;

@Service("tStudentInfoService")
public class TStudentInfoServiceImpl implements TStudentInfoService {
	@Autowired
	private TStudentInfoDao tStudentInfoDao;
	@Autowired
	private TStudentInfoMapper tStudentInfoMapper;

	@Autowired
	private ConsumeDao consumeDao;

	@Autowired
	private TStudentBalanceLogMapper tStudentBalanceLogMapper;

	@Autowired
	private TClassStudentRMapper tClassStudentRMapper;

	@Autowired
	private IClassService classService;

	@Autowired
	private LessonDao lessonDao;

	@Autowired
	private TStudentLearnPathMapper tStudentLearnPathMapper;

	@Autowired
	private TLessonInfoMapper tLessonInfoMapper;

	@Autowired
	private TLessonChapterMapper tLessonChapterMapper;

	@Override
	public TStudentInfoEntity queryObject(Integer userId) {
		return tStudentInfoDao.queryObject(userId);
	}

	@Override
	public List<TStudentInfoEntity> queryList(Map<String, Object> map) {
		return tStudentInfoDao.queryList(map);
	}

	@Override
	public List<TStudentInfoEntity> queryListReg(Map<String, Object> map) {
		return  tStudentInfoDao.queryListforReg(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return tStudentInfoDao.queryTotal(map);
	}

	@Override
	public void save(TStudentInfoEntity tStudentInfo) {
		tStudentInfo.setBalance(0);
		tStudentInfo.setRegTime(new Date());
		tStudentInfo.setRegStatus("0");
		tStudentInfo.setStatus(ApplicationConstant.UserStatus.NORMAL.getValue());
		tStudentInfo.setAddBaseInfo(ShiroUtils.getUserId());
		TStudentInfo studentInfo = new TStudentInfo();
		BeanUtils.copyProperties(tStudentInfo, studentInfo);
		tStudentInfoMapper.insert(studentInfo);
	}

	@Override
	public void update(TStudentInfoEntity tStudentInfo) {
		tStudentInfo.setLastUpdateTime(new Date());
		tStudentInfo.setModifyBaseInfo(ShiroUtils.getUserId());
		TStudentInfo studentInfo = new TStudentInfo();
		BeanUtils.copyProperties(tStudentInfo, studentInfo);
		tStudentInfoMapper.updateByPrimaryKeySelective(studentInfo);
	}

	@Override
	public void delete(Long userId) {
		TStudentInfo record = new TStudentInfo();
		record.setUserId(userId);
		record.setStatus(ApplicationConstant.UserStatus.DELETED.getValue());
		tStudentInfoMapper.updateByPrimaryKeySelective(record);

		TClassStudentRExample csrExample=new TClassStudentRExample();
		csrExample.createCriteria().andStudentIdEqualTo(userId).andStatusNotEqualTo(0);
		List<TClassStudentR> csrList = tClassStudentRMapper.selectByExample(csrExample);
		List<Long> ids=new ArrayList<>();
		ids.add(userId);
		if(CollectionUtils.isNotEmpty(csrList))
		{
			for(TClassStudentR r:csrList)
			{
				classService.deleteBatchStudent(r.getClassId(),null,ids);
			}
		}
	}

	@Override
	public void deleteBatch(Long[] userIds) {
		if (null != userIds && userIds.length > 0) {
			for (int i = 0; i < userIds.length; i++) {
				delete(userIds[i]);
			}
		}
	}

	@Override
	public String checkStudentInfo(TStudentInfoEntity student) {

		if (StringUtils.isEmpty(student.getMsisdn()) || StringUtils.isEmpty(student.getName())) {
			return "手机号码、姓名都不能为空！";
		}
		TStudentInfoExample example = new TStudentInfoExample();
		TStudentInfoExample.Criteria cri = example.createCriteria()
				.andStatusEqualTo(ApplicationConstant.UserStatus.NORMAL.getValue()).andNameEqualTo(student.getName());
		if (null != student.getUserId()) {
			cri.andUserIdNotEqualTo(student.getUserId());
		}
		if (tStudentInfoMapper.countByExampleXml(example) > 0) {
			return "学生姓名不能重复";
		}
		example = new TStudentInfoExample();
		cri = example.createCriteria().andStatusEqualTo(ApplicationConstant.UserStatus.NORMAL.getValue())
				.andMsisdnEqualTo(student.getMsisdn());
		if (null != student.getUserId()) {
			cri.andUserIdNotEqualTo(student.getUserId());
		}
		if (tStudentInfoMapper.countByExampleXml(example) > 0) {
			return "此号码已使用，请更换为其它号码";
		}
		return null;
	}

	@Override
	public boolean userIsExistsAndNomal(Long userId) {
		return null != tStudentInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean doRecharge(RechargeRequest rechargeReq) {

		TStudentBalanceLog record = new TStudentBalanceLog();
		record.setAddtime(new Date());
		record.setAmount(rechargeReq.getAmount());
		record.setMark(rechargeReq.getRemark());
		record.setUserId(rechargeReq.getUserId());
		record.setType(ApplicationConstant.BlanceType.RECHARGE.getValue());
		record.setbType(rechargeReq.getReChargeType());
		record.setStatus(ApplicationConstant.BlacneLogStatus.SURE.getValue());
		if (tStudentBalanceLogMapper.insertSelective(record) > 0) {
			if (consumeDao.changeStudentBlance(rechargeReq.getUserId(), rechargeReq.getAmount(),
					rechargeReq.getReChargeType()) > 0) {
				TStudentInfo studentInfo = tStudentInfoMapper.selectByPrimaryKey(rechargeReq.getUserId());
				record.setAfterBalance(studentInfo.getBalance());
				// 更新余额记录
				tStudentBalanceLogMapper.updateByPrimaryKeySelective(record);
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean addLearnPath(Long studentId, Integer lessonId, Integer chapterId) {

		TStudentLearnPathExample example=new TStudentLearnPathExample();
		example.createCriteria().andStudentIdEqualTo(studentId).andLessonIdEqualTo(Long.valueOf(lessonId)).andChapterIdEqualTo(chapterId);

		if(tStudentLearnPathMapper.countByExample(example) > 0)
		{
			return true;
		}
		TStudentLearnPath r=new TStudentLearnPath();
		r.setStudentId(studentId);
		r.setLessonId(lessonId);
		r.setChapterId(chapterId);
		r.setAddTime(new Date());
		return tStudentLearnPathMapper.insert(r) >0;
	}

	@Override
	public boolean removeLearnPath(Long studentId, Integer lessonId, Integer chapterId) {
		TStudentLearnPathExample example=new TStudentLearnPathExample();
		example.createCriteria().andStudentIdEqualTo(studentId).andLessonIdEqualTo(Long.valueOf(lessonId)).andChapterIdEqualTo(chapterId);
		return tStudentLearnPathMapper.deleteByExample(example) > 0;
	}

	@Override
	public List<Long> queryIdList(Map<String, Object> map) {
		return tStudentInfoDao.queryIdList(map);
	}

	@Override
	@Transactional
	public void updateBind(TStudentInfoEntity tStudentInfo) {
		//修改所属合伙人
		update(tStudentInfo);
		//清空原合伙人班级下的所有未上课程
		if(tStudentInfo.getOldAddUserId() != null){
			tStudentInfoDao.clearUnfinishLesson(tStudentInfo);
			List<Long> idList = tStudentInfoDao.queryUnFinishScheduleIdList(tStudentInfo);
			if(idList != null && idList.size() > 0){
				tStudentInfoDao.clearClassSchedule(idList);
			}
		}
		
	}

	@Override
	public boolean checkStudentUnConfirm(Long studentId, Long subAdminUserId) {
		return tStudentInfoDao.queryUnConfirmLessons(studentId, subAdminUserId) == 0;
	}

}

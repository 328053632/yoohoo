package com.yoohoo.en.teacher.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoohoo.en.constant.ApplicationConstant;
import com.yoohoo.en.dao.mapper.TTeacherInfoMapper;
import com.yoohoo.en.dao.mapper.TTeacherPositionRelationMapper;
import com.yoohoo.en.dao.mapper.TTeacherRateMapper;
import com.yoohoo.en.dao.model.TTeacherInfo;
import com.yoohoo.en.dao.model.TTeacherInfoExample;
import com.yoohoo.en.dao.model.TTeacherPositionRelation;
import com.yoohoo.en.dao.model.TTeacherPositionRelationExample;
import com.yoohoo.en.dao.model.TTeacherRate;
import com.yoohoo.en.dao.model.TTeacherPositionRelationExample.Criteria;
import com.yoohoo.en.mcore.utils.ShiroUtils;
import com.yoohoo.en.msys.service.SysUserRoleService;
import com.yoohoo.en.teacher.dao.TTeacherInfoDao;
import com.yoohoo.en.teacher.entity.TTeacherInfoEntity;
import com.yoohoo.en.teacher.service.TTeacherInfoService;
import com.yoohoo.en.utils.MD5Util;



@Service("tTeacherInfoService")
public class TTeacherInfoServiceImpl implements TTeacherInfoService {
	@Autowired
	private TTeacherInfoDao tTeacherInfoDao;
	@Autowired
	private TTeacherInfoMapper tTeacherInfoMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private TTeacherPositionRelationMapper teacherPositionRelationMapper;
	@Autowired
	private TTeacherRateMapper tTeacherRateMapper;
	
	@Override
	public TTeacherInfoEntity queryObject(Integer teacherId){
		TTeacherInfoEntity info = tTeacherInfoDao.queryObject(teacherId);
		TTeacherRate record = new TTeacherRate();
		record.setTeacherId(teacherId.longValue());
		List<TTeacherRate> rateList = tTeacherRateMapper.select(record);
		info.setRateList(rateList);
		return info;
	}
	
	@Override
	public List<TTeacherInfoEntity> queryList(Map<String, Object> map){
		return tTeacherInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tTeacherInfoDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(TTeacherInfoEntity tTeacherInfo){
		tTeacherInfo.setStatus(ApplicationConstant.UserStatus.NORMAL.getValue());
		tTeacherInfo.setCreateTime(new Date());
		tTeacherInfo.setUpdateTime(new Date());
		tTeacherInfo.setPassword(MD5Util.MD5Encode(tTeacherInfo.getPassword()));
		tTeacherInfoDao.save(tTeacherInfo);
		List<TTeacherRate> rateList = tTeacherInfo.getRateList();
		for(TTeacherRate e:rateList){
			e.setAddBaseInfo(tTeacherInfo.getAddUserId());
			e.setTeacherId(tTeacherInfo.getTeacherId().longValue());
		}
		tTeacherRateMapper.insertList(rateList);
	}
	
	@Override
	@Transactional
	public void update(TTeacherInfoEntity tTeacherInfo){
		if(tTeacherInfo.getTeacherId() == null){
			return;
		}
		tTeacherInfo.setUpdateTime(new Date());
		if(StringUtils.isNotEmpty(tTeacherInfo.getPassword()) && StringUtils.isNotEmpty(tTeacherInfo.getPassword().trim()))
		{
			tTeacherInfo.setPassword(tTeacherInfo.getPassword());
		}else{
			tTeacherInfo.setPassword(null);
		}
		tTeacherInfoDao.update(tTeacherInfo);
		List<TTeacherRate> rateList = tTeacherInfo.getRateList();
		TTeacherRate model = new TTeacherRate();
		model.setTeacherId(tTeacherInfo.getTeacherId().longValue());
		tTeacherRateMapper.delete(model);
		for(TTeacherRate e:rateList){
			e.setId(null);
			e.setAddBaseInfo(tTeacherInfo.getAddUserId());
			e.setTeacherId(tTeacherInfo.getTeacherId().longValue());
			tTeacherRateMapper.insert(e);
		}
	}
	
	@Override
	public void delete(Integer teacherId){
		TTeacherInfo record=new TTeacherInfo();
		record.setTeacherId(teacherId);
		record.setStatus(ApplicationConstant.UserStatus.DELETED.getValue());
		tTeacherInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void deleteBatch(Integer[] teacherIds){
		if(null != teacherIds && teacherIds.length >0)
		{
			for(int i=0;i<teacherIds.length;i++)
			{
				delete(teacherIds[i]);
			}
		}
	}

	@Override
	public boolean accountIsExists(String account) {
		
		TTeacherInfoExample example=new TTeacherInfoExample();
		example.createCriteria().andAccountEqualTo(account).andStatusEqualTo(ApplicationConstant.UserStatus.NORMAL.getValue());
		return tTeacherInfoMapper.countByExample(example) > 0;
	}

	/**
	 * //1.防止重复插入
	 * //2.实现删除员工类型
	 *
	 * @param list
	 */
	@Override
	public void insertBatchPositonType(List<TTeacherPositionRelation> list) {
		//获取teacherId
		Integer teacherId = list.get(0).getTeacherId();
		TTeacherInfoEntity teacherInfo = tTeacherInfoDao.queryObject(teacherId);
		//首先完成不重复插入操作
		//创建一个用于老师id的数组(用于使用not in 来删除不在这个范围的老师类型)
		List<Integer> PositionIds=new ArrayList<>();
		//创建一个addList（用于存储不在数据库中的新增老师类型）
		List<TTeacherPositionRelation> addList=new ArrayList<>();
		for (TTeacherPositionRelation teacherPositionRelation:list) {
			teacherPositionRelation.setAddBaseInfo(teacherInfo.getAddUserId());
			teacherPositionRelation.setModifyBaseInfo(ShiroUtils.getUserId());
			//首先将用户保存的老师id存储到用于以后进行删除的id中
			PositionIds.add(teacherPositionRelation.getPositionType());
			//进行条件查询，查询当前遍历到的一组信息中数据库中是否已经存在
			TTeacherPositionRelationExample example=new TTeacherPositionRelationExample();
			example.createCriteria().andTeacherIdEqualTo(teacherPositionRelation.getTeacherId()).andPositionTypeEqualTo(teacherPositionRelation.getPositionType())
			.andUserIdEquals(teacherInfo.getAddUserId()).andStatusEqualTo(1);
			List<TTeacherPositionRelation> tTeacherPositionRelations = teacherPositionRelationMapper.selectByExample(example);
			if(null!=tTeacherPositionRelations &&tTeacherPositionRelations.size()>0){
				//如果存在 修改status
				TTeacherPositionRelation bean=tTeacherPositionRelations.get(0);
				bean.setStatus(teacherPositionRelation.getStatus());
				teacherPositionRelationMapper.updateByExample(bean,example);
			}else{
				//如果不存在 添加到addList中
				addList.add(teacherPositionRelation);
			}
		}

		if(addList.size()>0){
			teacherPositionRelationMapper.insertBatchList(addList);
		}

		//当信息全部添加完成后进行删除操作
		deleteTeacherPositionType(teacherId,PositionIds);

	}

	private void deleteTeacherPositionType(Integer teacherId, List<Integer> positionIds) {
		//使用not in 语句直接进行查询如果查到那么就修改状态

		TTeacherPositionRelationExample example=new TTeacherPositionRelationExample();
		TTeacherPositionRelationExample.Criteria criteria = example.createCriteria();
		criteria.andTeacherIdEqualTo(teacherId);
		//如果不为空
		if(CollectionUtils.isNotEmpty(positionIds)){
			//增加not in 条件
			criteria.andPositionTypeNotIn(positionIds);
		}
		//直接根据条件进行更新即可
		TTeacherPositionRelation bean=new TTeacherPositionRelation();
		bean.setStatus(0);
		teacherPositionRelationMapper.updateByExampleSelective(bean,example);
	}

	@Override
	public List<TTeacherPositionRelation> selectByTeacherId(Integer teacherId, Long addUserId) {
		return teacherPositionRelationMapper.queryList(teacherId, addUserId);
	}

	@Override
	public List<TTeacherInfo> queryByPositionType(Integer positionType) {

		//用于存储老师id
		List<Long> teacherIdList=new ArrayList<>();

		//获取所有类型
		TTeacherPositionRelationExample example=new TTeacherPositionRelationExample();
		Criteria criteria = example.createCriteria();
		criteria.andPositionTypeEqualTo(positionType).andStatusEqualTo(1);
		 if(!sysUserRoleService.isAdmin(ShiroUtils.getUserId())){
	        criteria.andUserIdEquals(ShiroUtils.getUserId()); 
	        Map<String, Object> map = new HashMap<>();
	        map.put("addUserId", ShiroUtils.getUserId());
	        List<Long> teacherIdListTemp = tTeacherInfoDao.queryIdList(map);
	        if(CollectionUtils.isNotEmpty(teacherIdListTemp)){
		        criteria.andTeacherIdIn(tTeacherInfoDao.queryIdList(map));
	        }
	       
		 }
		List<TTeacherPositionRelation> list = teacherPositionRelationMapper.selectByExample(example);
		for (TTeacherPositionRelation tTeacherPositionRelation:list){
			teacherIdList.add(Long.parseLong(tTeacherPositionRelation.getTeacherId().toString()));
		}

		//根据获得到的老师id的数组进行查询老师的相关信息
		List<TTeacherInfo> teacherInfos = new ArrayList<TTeacherInfo>();
		TTeacherInfoExample example1=new TTeacherInfoExample();
		if(teacherIdList.size() > 0){
			example1.createCriteria().andTeacherIdIn(teacherIdList);
			teacherInfos = tTeacherInfoMapper.selectByExample(example1);
		}
		return teacherInfos;
	}

	@Override
	public TTeacherInfo queryById(Integer teacherId) {
		return tTeacherInfoMapper.selectByPrimaryKey(teacherId);
	}

	@Override
	public List<TTeacherInfo> getTeacherByName(String likeName) {

		TTeacherInfoExample example=new TTeacherInfoExample();

		example.createCriteria().andEnNameLike(likeName);
        List<TTeacherInfo> tTeacherInfoList = tTeacherInfoMapper.selectByExample(example);
        if (null != tTeacherInfoList && tTeacherInfoList.size() > 0) {
            return tTeacherInfoList;
        }
        return null;
	}

	@Override
	public List<Long> queryIdList(Map<String, Object> map) {
		return tTeacherInfoDao.queryIdList(map);
	}
}

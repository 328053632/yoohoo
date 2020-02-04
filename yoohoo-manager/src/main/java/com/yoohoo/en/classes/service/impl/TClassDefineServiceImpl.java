package com.yoohoo.en.classes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.classes.service.TClassDefineService;
import com.yoohoo.en.dao.PageHelper;
import com.yoohoo.en.dao.mapper.TClassDefineMapper;
import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.TClassDefineExample;



@Service("tClassDefineService")
public class TClassDefineServiceImpl implements TClassDefineService {
	@Autowired
	private TClassDefineMapper tClassDefineDao;

	
	@Override
	public TClassDefine queryObject(Integer classItemId){
		return tClassDefineDao.selectByPrimaryKey(classItemId);
	}
	
	@Override
	public List<TClassDefine> queryList(Map<String, Object> map){
		
		TClassDefineExample example=new TClassDefineExample();
		example.setPageHelper(new PageHelper<>((Integer)map.get("offset"), (Integer)map.get("limit")));
		return tClassDefineDao.selectByExample(example);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		TClassDefineExample example=new TClassDefineExample();
		example.setPageHelper(new PageHelper<>((Integer)map.get("offset"), (Integer)map.get("limit")));
		return tClassDefineDao.countByExample(example);
	}
	
	@Override
	public void save(TClassDefine tClassDefine){
		tClassDefine.setAddTime(new Date());
		tClassDefineDao.insertSelective(tClassDefine);
	}
	
	@Override
	public void update(TClassDefine tClassDefine){
		tClassDefine.setAddTime(new Date());
		tClassDefineDao.updateByPrimaryKeySelective(tClassDefine);
	}
	
	@Override
	public void delete(Integer classItemId){
		tClassDefineDao.deleteByPrimaryKey(classItemId);
	}
	
	@Override
	public void deleteBatch(Integer[] classItemIds){
		for(int i=0;i<classItemIds.length;i++)
		{
			this.delete(classItemIds[i]);
		}
	}

	@Override
	public String checkClassDefine(TClassDefine c) {
		if(StringUtils.isEmpty(c.getClassItemName()))
		{
			return "班级配置名称不能为空";
		}
		
		TClassDefineExample example=new TClassDefineExample();
		TClassDefineExample.Criteria cri=example.createCriteria().andClassItemNameEqualTo(c.getClassItemName());
		if(null != c.getClassItemId())
		{
			cri.andClassItemIdNotEqualTo(c.getClassItemId());
		}
		if(tClassDefineDao.countByExample(example) > 0)
		{
			return "班级配置名称不能重复";
		}
		
		return null;
	}

	@Override
	public List<TClassDefine> queryList() {
		TClassDefineExample example=new TClassDefineExample();
		example.createCriteria();

		return tClassDefineDao.selectByExample(example);
	}

}

package com.yoohoo.en.feetemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoohoo.en.dao.mapper.TFeetemplateMapper;
import com.yoohoo.en.dao.model.TFeetemplate;
import com.yoohoo.en.feetemplate.service.FeetemplateService;
import java.util.List;

/**
 * Created By LiWenLong On 2018/8/10 14:06
 * E-Mail:it_lwl@163.com
 */
@Service
public class FeetemplateServiceImpl implements FeetemplateService {
	@Autowired
	private TFeetemplateMapper feetemplateMapper;

	@Override
	public List<TFeetemplate> getList(
			TFeetemplate model) {
		if(model == null){
			model = new TFeetemplate();
			model.setStatus(1);
		}
		return feetemplateMapper.select(model);
	}

	@Override
	public void save(TFeetemplate model) {
		feetemplateMapper.insert(model);
	}

	@Override
	public void update(TFeetemplate model) {
		feetemplateMapper.updateByPrimaryKey(model);
	}

	@Override
	public int count(TFeetemplate model) {
		return feetemplateMapper.selectCount(model);
	}

	@Override
	public TFeetemplate getOne(TFeetemplate model) {
		return feetemplateMapper.selectByPrimaryKey(model.getId());
	}
}

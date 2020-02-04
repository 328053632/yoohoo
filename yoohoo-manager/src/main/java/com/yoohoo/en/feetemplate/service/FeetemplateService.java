package com.yoohoo.en.feetemplate.service;

import com.yoohoo.en.dao.model.TFeetemplate;
import java.util.List;


/**
 * Created By LiWenLong On 2018/8/10 14:00
 * E-Mail:it_lwl@163.com
 */
public interface FeetemplateService {
	
	TFeetemplate getOne(TFeetemplate model);

	List<TFeetemplate> getList(TFeetemplate model);

	void save(TFeetemplate model);

	void update(TFeetemplate model);

	int count(TFeetemplate model);
}

package com.yoohoo.en.consume.service;

import java.util.List;

import com.yoohoo.en.bean.request.QueryConsumeRequest;
import com.yoohoo.en.bean.request.QueryStudentBalanceReq;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TClass;
import com.yoohoo.en.dao.model.TStudentBalanceLog;
import com.yoohoo.en.dao.model.ext.ConsumePackInfo;
import com.yoohoo.en.dao.model.ext.StuTypeItem;

public interface IConsumeService {

	List<ConsumePackInfo> queryCheckedConsumeList(QueryConsumeRequest req);
	
	List<StuTypeItem> queryStuTypeItemList(Integer classItemId);

	BaseResp sureClassLessonScheduleAndStudentConsume(ConsumePackInfo cpg);
	
	List<TStudentBalanceLog> queryStudentBalanceLogList(QueryStudentBalanceReq req);

	int countStudentBalanceLog(QueryStudentBalanceReq req);

	String queryTeacher(Integer classId);

}

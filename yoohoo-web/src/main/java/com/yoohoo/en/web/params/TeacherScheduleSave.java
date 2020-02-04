package com.yoohoo.en.web.params;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.yoohoo.en.dao.model.TTeacherScheduleNv;
import com.yoohoo.en.dao.model.common.BaseInfoModel;
import com.yoohoo.en.utils.DateUtil;

public class TeacherScheduleSave extends BaseInfoModel {
	private static final long serialVersionUID = -4823790190870260748L;

    private Integer ifWeekBatch;
    private Long teacherId;
    private List<TTeacherScheduleNv> scheduleList;
	public Integer getIfWeekBatch() {
		return ifWeekBatch;
	}
	public void setIfWeekBatch(Integer ifWeekBatch) {
		this.ifWeekBatch = ifWeekBatch;
	}
	public List<TTeacherScheduleNv> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(List<TTeacherScheduleNv> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
    
	public List<TTeacherScheduleNv> buildScheduleList(){
		if(CollectionUtils.isEmpty(scheduleList)){
			return null;
		}
		if(ifWeekBatch == 1){
			scheduleList.addAll(makeWeekScheduleList(scheduleList));
		}
        for(TTeacherScheduleNv e:scheduleList){
        	e.setAddBaseInfo(teacherId);
        }
		return scheduleList;
	}
	
	private List<TTeacherScheduleNv> makeWeekScheduleList(List<TTeacherScheduleNv> scheduleList){
		List<TTeacherScheduleNv> list = new ArrayList<TTeacherScheduleNv>();
		LocalDate now = LocalDate.now();
		int dayStart = now.getDayOfWeek().getValue()+1;
		TTeacherScheduleNv e = null;
		for(int i=dayStart;i<7;i++){
			now = now.plusDays(1);
			for(TTeacherScheduleNv schedule:scheduleList){
				e = new TTeacherScheduleNv();
				BeanUtils.copyProperties(schedule, e);
				e.setDate(DateUtil.getIntValueFromLocalDate(now));
				list.add(e);
			}
		}
		return list;
	}

}
package com.yoohoo.en.dao.model.ext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

public class TeacherScheduleTimesOfDay implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String month;
    
    private String day;

    private Integer all;

    private Integer used;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getAll() {
		return all;
	}

	public void setAll(Integer all) {
		this.all = all;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}
	
	public static List<TeacherScheduleTimesOfDay> buildList(List<TeacherScheduleInfo> scheduleList){
		Map<String, List<TeacherScheduleInfo>> allDataMap = scheduleList.stream().collect(Collectors.groupingBy(TeacherScheduleInfo::getDateLabel));
		if(MapUtils.isEmpty(allDataMap)){
			return null;
		}
		Map<String, List<TeacherScheduleInfo>> finishDataMap = scheduleList.stream().filter(e -> e.getStatus() == 2).collect(Collectors.groupingBy(TeacherScheduleInfo::getDateLabel));
		if(MapUtils.isEmpty(finishDataMap)){
			finishDataMap = new HashMap<>();
		}
		List<TeacherScheduleTimesOfDay> list = new ArrayList<TeacherScheduleTimesOfDay>();
		
		return list;
	}
}

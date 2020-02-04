package com.yoohoo.en.dao.model.ext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import com.yoohoo.en.dao.model.TLessonChapter;
import com.yoohoo.en.dao.model.TLessonInfo;
import org.apache.commons.beanutils.BeanUtils;


public class LessonInfoExt extends TLessonInfo {

	private static final long serialVersionUID = -5034628236360662508L;
	
	private Integer classId;
	
	private List<TLessonChapterExt> chapterList;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public List<TLessonChapterExt> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<TLessonChapterExt> chapterList) {
		this.chapterList = chapterList;
	}

	public boolean copyFrom(TLessonInfo lesson)
	{
		try {
			BeanUtils.copyProperties(this, lesson);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

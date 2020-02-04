package com.yoohoo.en.dao.model;

import java.util.List;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TLessonInfo extends BaseInfoModel {
    private Long lessonId;

    private String title;

    private String introduce;

    private String coverUrl;

    private String ageSection;

    private Integer lessonType;

    private String times;

    private Integer categoryId;

    private String categoryName;

    private Integer status;

    private Integer classBeginCount;

    private List<TLessonTeacher> teachers;


    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public String getAgeSection() {
        return ageSection;
    }

    public void setAgeSection(String ageSection) {
        this.ageSection = ageSection == null ? null : ageSection.trim();
    }

    public Integer getLessonType() {
        return lessonType;
    }

    public void setLessonType(Integer lessonType) {
        this.lessonType = lessonType;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getClassBeginCount() {
		return classBeginCount;
	}

	public void setClassBeginCount(Integer classBeginCount) {
		this.classBeginCount = classBeginCount;
	}

	public List<TLessonTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TLessonTeacher> teachers) {
		this.teachers = teachers;
	}
}
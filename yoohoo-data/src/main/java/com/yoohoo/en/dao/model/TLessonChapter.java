package com.yoohoo.en.dao.model;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TLessonChapter extends BaseInfoModel {
	
    private Integer chapterId;

    private Integer lessonId;

    private String title;

    private String trait;
    
    private String introduce;

    private Integer orderNum;

    private static final long serialVersionUID = 1L;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTrait() {
		return trait;
	}

	public void setTrait(String trait) {
		this.trait = trait;
	}

	public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    private TLessonChapter(Builder b) {
        chapterId = b.chapterId;
        lessonId = b.lessonId;
        title = b.title;
        introduce = b.introduce;
        orderNum = b.orderNum;
    }

    public TLessonChapter() {
        super();
    }

    public static class Builder {
        private Integer chapterId;

        private Integer lessonId;

        private String title;

        private String introduce;

        private Integer orderNum;

        public Builder chapterId(Integer chapterId) {
            this.chapterId = chapterId;
            return this;
        }

        public Builder lessonId(Integer lessonId) {
            this.lessonId = lessonId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder introduce(String introduce) {
            this.introduce = introduce;
            return this;
        }

        public Builder orderNum(Integer orderNum) {
            this.orderNum = orderNum;
            return this;
        }

        public TLessonChapter build() {
            return new TLessonChapter(this);
        }
    }
}
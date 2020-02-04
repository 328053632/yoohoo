package com.yoohoo.en.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TClassScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TClassScheduleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andScheduleIdIsNull() {
            addCriterion("schedule_id is null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIsNotNull() {
            addCriterion("schedule_id is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleIdEqualTo(Long value) {
            addCriterion("schedule_id =", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotEqualTo(Integer value) {
            addCriterion("schedule_id <>", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThan(Integer value) {
            addCriterion("schedule_id >", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_id >=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThan(Integer value) {
            addCriterion("schedule_id <", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_id <=", value, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdIn(List<Integer> values) {
            addCriterion("schedule_id in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotIn(List<Integer> values) {
            addCriterion("schedule_id not in", values, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andScheduleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_id not between", value1, value2, "scheduleId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Long value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Integer value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Integer value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Integer value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Integer> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Integer> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Integer value1, Integer value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andLessonIdIsNull() {
            addCriterion("lesson_id is null");
            return (Criteria) this;
        }

        public Criteria andLessonIdIsNotNull() {
            addCriterion("lesson_id is not null");
            return (Criteria) this;
        }

        public Criteria andLessonIdEqualTo(Integer value) {
            addCriterion("lesson_id =", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdNotEqualTo(Integer value) {
            addCriterion("lesson_id <>", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdGreaterThan(Integer value) {
            addCriterion("lesson_id >", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lesson_id >=", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdLessThan(Integer value) {
            addCriterion("lesson_id <", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdLessThanOrEqualTo(Integer value) {
            addCriterion("lesson_id <=", value, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdIn(List<Integer> values) {
            addCriterion("lesson_id in", values, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdNotIn(List<Integer> values) {
            addCriterion("lesson_id not in", values, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdBetween(Integer value1, Integer value2) {
            addCriterion("lesson_id between", value1, value2, "lessonId");
            return (Criteria) this;
        }

        public Criteria andLessonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lesson_id not between", value1, value2, "lessonId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNull() {
            addCriterion("chapter_id is null");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNotNull() {
            addCriterion("chapter_id is not null");
            return (Criteria) this;
        }

        public Criteria andChapterIdEqualTo(Integer value) {
            addCriterion("chapter_id =", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotEqualTo(Integer value) {
            addCriterion("chapter_id <>", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThan(Integer value) {
            addCriterion("chapter_id >", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chapter_id >=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThan(Integer value) {
            addCriterion("chapter_id <", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThanOrEqualTo(Integer value) {
            addCriterion("chapter_id <=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIn(List<Integer> values) {
            addCriterion("chapter_id in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotIn(List<Integer> values) {
            addCriterion("chapter_id not in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdBetween(Integer value1, Integer value2) {
            addCriterion("chapter_id between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chapter_id not between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andLessonTimeIsNull() {
            addCriterion("lesson_time is null");
            return (Criteria) this;
        }

        public Criteria andLessonTimeIsNotNull() {
            addCriterion("lesson_time is not null");
            return (Criteria) this;
        }

        public Criteria andLessonTimeEqualTo(Date value) {
            addCriterion("lesson_time =", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeNotEqualTo(Date value) {
            addCriterion("lesson_time <>", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeGreaterThan(Date value) {
            addCriterion("lesson_time >", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lesson_time >=", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeLessThan(Date value) {
            addCriterion("lesson_time <", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeLessThanOrEqualTo(Date value) {
            addCriterion("lesson_time <=", value, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeIn(List<Date> values) {
            addCriterion("lesson_time in", values, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeNotIn(List<Date> values) {
            addCriterion("lesson_time not in", values, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeBetween(Date value1, Date value2) {
            addCriterion("lesson_time between", value1, value2, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andLessonTimeNotBetween(Date value1, Date value2) {
            addCriterion("lesson_time not between", value1, value2, "lessonTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(Integer value) {
            addCriterion("teacher_id =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(Integer value) {
            addCriterion("teacher_id <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(Integer value) {
            addCriterion("teacher_id >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("teacher_id >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(Integer value) {
            addCriterion("teacher_id <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(Integer value) {
            addCriterion("teacher_id <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<Integer> values) {
            addCriterion("teacher_id in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<Integer> values) {
            addCriterion("teacher_id not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(Integer value1, Integer value2) {
            addCriterion("teacher_id between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("teacher_id not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdIsNull() {
            addCriterion("eteacher_id is null");
            return (Criteria) this;
        }

        public Criteria andEteacherIdIsNotNull() {
            addCriterion("eteacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andEteacherIdEqualTo(Integer value) {
            addCriterion("eteacher_id =", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdNotEqualTo(Integer value) {
            addCriterion("eteacher_id <>", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdGreaterThan(Integer value) {
            addCriterion("eteacher_id >", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eteacher_id >=", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdLessThan(Integer value) {
            addCriterion("eteacher_id <", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdLessThanOrEqualTo(Integer value) {
            addCriterion("eteacher_id <=", value, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdIn(List<Integer> values) {
            addCriterion("eteacher_id in", values, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdNotIn(List<Integer> values) {
            addCriterion("eteacher_id not in", values, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdBetween(Integer value1, Integer value2) {
            addCriterion("eteacher_id between", value1, value2, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andEteacherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eteacher_id not between", value1, value2, "eteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdIsNull() {
            addCriterion("masterteacher_id is null");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdIsNotNull() {
            addCriterion("masterteacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdEqualTo(Integer value) {
            addCriterion("masterteacher_id =", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdNotEqualTo(Integer value) {
            addCriterion("masterteacher_id <>", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdGreaterThan(Integer value) {
            addCriterion("masterteacher_id >", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("masterteacher_id >=", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdLessThan(Integer value) {
            addCriterion("masterteacher_id <", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdLessThanOrEqualTo(Integer value) {
            addCriterion("masterteacher_id <=", value, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdIn(List<Integer> values) {
            addCriterion("masterteacher_id in", values, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdNotIn(List<Integer> values) {
            addCriterion("masterteacher_id not in", values, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdBetween(Integer value1, Integer value2) {
            addCriterion("masterteacher_id between", value1, value2, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andMasterteacherIdNotBetween(Integer value1, Integer value2) {
            addCriterion("masterteacher_id not between", value1, value2, "masterteacherId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(String value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(String value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(String value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(String value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(String value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLike(String value) {
            addCriterion("room_id like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotLike(String value) {
            addCriterion("room_id not like", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<String> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<String> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(String value1, String value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(String value1, String value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdIsNull() {
            addCriterion("room_teacher_passwd is null");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdIsNotNull() {
            addCriterion("room_teacher_passwd is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdEqualTo(String value) {
            addCriterion("room_teacher_passwd =", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdNotEqualTo(String value) {
            addCriterion("room_teacher_passwd <>", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdGreaterThan(String value) {
            addCriterion("room_teacher_passwd >", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("room_teacher_passwd >=", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdLessThan(String value) {
            addCriterion("room_teacher_passwd <", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdLessThanOrEqualTo(String value) {
            addCriterion("room_teacher_passwd <=", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdLike(String value) {
            addCriterion("room_teacher_passwd like", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdNotLike(String value) {
            addCriterion("room_teacher_passwd not like", value, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdIn(List<String> values) {
            addCriterion("room_teacher_passwd in", values, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdNotIn(List<String> values) {
            addCriterion("room_teacher_passwd not in", values, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdBetween(String value1, String value2) {
            addCriterion("room_teacher_passwd between", value1, value2, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherPasswdNotBetween(String value1, String value2) {
            addCriterion("room_teacher_passwd not between", value1, value2, "roomTeacherPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlIsNull() {
            addCriterion("room_teacher_url is null");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlIsNotNull() {
            addCriterion("room_teacher_url is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlEqualTo(String value) {
            addCriterion("room_teacher_url =", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlNotEqualTo(String value) {
            addCriterion("room_teacher_url <>", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlGreaterThan(String value) {
            addCriterion("room_teacher_url >", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlGreaterThanOrEqualTo(String value) {
            addCriterion("room_teacher_url >=", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlLessThan(String value) {
            addCriterion("room_teacher_url <", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlLessThanOrEqualTo(String value) {
            addCriterion("room_teacher_url <=", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlLike(String value) {
            addCriterion("room_teacher_url like", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlNotLike(String value) {
            addCriterion("room_teacher_url not like", value, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlIn(List<String> values) {
            addCriterion("room_teacher_url in", values, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlNotIn(List<String> values) {
            addCriterion("room_teacher_url not in", values, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlBetween(String value1, String value2) {
            addCriterion("room_teacher_url between", value1, value2, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomTeacherUrlNotBetween(String value1, String value2) {
            addCriterion("room_teacher_url not between", value1, value2, "roomTeacherUrl");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdIsNull() {
            addCriterion("room_stu_passwd is null");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdIsNotNull() {
            addCriterion("room_stu_passwd is not null");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdEqualTo(String value) {
            addCriterion("room_stu_passwd =", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdNotEqualTo(String value) {
            addCriterion("room_stu_passwd <>", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdGreaterThan(String value) {
            addCriterion("room_stu_passwd >", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("room_stu_passwd >=", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdLessThan(String value) {
            addCriterion("room_stu_passwd <", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdLessThanOrEqualTo(String value) {
            addCriterion("room_stu_passwd <=", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdLike(String value) {
            addCriterion("room_stu_passwd like", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdNotLike(String value) {
            addCriterion("room_stu_passwd not like", value, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdIn(List<String> values) {
            addCriterion("room_stu_passwd in", values, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdNotIn(List<String> values) {
            addCriterion("room_stu_passwd not in", values, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdBetween(String value1, String value2) {
            addCriterion("room_stu_passwd between", value1, value2, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andRoomStuPasswdNotBetween(String value1, String value2) {
            addCriterion("room_stu_passwd not between", value1, value2, "roomStuPasswd");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDateLabelIsNull() {
            addCriterion("date_label is null");
            return (Criteria) this;
        }

        public Criteria andDateLabelIsNotNull() {
            addCriterion("date_label is not null");
            return (Criteria) this;
        }

        public Criteria andDateLabelEqualTo(String value) {
            addCriterion("date_label =", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotEqualTo(String value) {
            addCriterion("date_label <>", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelGreaterThan(String value) {
            addCriterion("date_label >", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelGreaterThanOrEqualTo(String value) {
            addCriterion("date_label >=", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLessThan(String value) {
            addCriterion("date_label <", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLessThanOrEqualTo(String value) {
            addCriterion("date_label <=", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelLike(String value) {
            addCriterion("date_label like", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotLike(String value) {
            addCriterion("date_label not like", value, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelIn(List<String> values) {
            addCriterion("date_label in", values, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotIn(List<String> values) {
            addCriterion("date_label not in", values, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelBetween(String value1, String value2) {
            addCriterion("date_label between", value1, value2, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andDateLabelNotBetween(String value1, String value2) {
            addCriterion("date_label not between", value1, value2, "dateLabel");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andIsUploadIsNull() {
            addCriterion("is_upload is null");
            return (Criteria) this;
        }

        public Criteria andIsUploadIsNotNull() {
            addCriterion("is_upload is not null");
            return (Criteria) this;
        }

        public Criteria andIsUploadEqualTo(Integer value) {
            addCriterion("is_upload =", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotEqualTo(Integer value) {
            addCriterion("is_upload <>", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadGreaterThan(Integer value) {
            addCriterion("is_upload >", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_upload >=", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadLessThan(Integer value) {
            addCriterion("is_upload <", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadLessThanOrEqualTo(Integer value) {
            addCriterion("is_upload <=", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadIn(List<Integer> values) {
            addCriterion("is_upload in", values, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotIn(List<Integer> values) {
            addCriterion("is_upload not in", values, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadBetween(Integer value1, Integer value2) {
            addCriterion("is_upload between", value1, value2, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotBetween(Integer value1, Integer value2) {
            addCriterion("is_upload not between", value1, value2, "isUpload");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
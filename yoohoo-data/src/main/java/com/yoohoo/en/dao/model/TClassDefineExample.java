package com.yoohoo.en.dao.model;

import com.yoohoo.en.dao.PageHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TClassDefineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    protected PageHelper pageHelper;


    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }

    public TClassDefineExample() {
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

        public Criteria andClassItemIdIsNull() {
            addCriterion("class_item_id is null");
            return (Criteria) this;
        }

        public Criteria andClassItemIdIsNotNull() {
            addCriterion("class_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassItemIdEqualTo(Integer value) {
            addCriterion("class_item_id =", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdNotEqualTo(Integer value) {
            addCriterion("class_item_id <>", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdGreaterThan(Integer value) {
            addCriterion("class_item_id >", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_item_id >=", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdLessThan(Integer value) {
            addCriterion("class_item_id <", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_item_id <=", value, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdIn(List<Integer> values) {
            addCriterion("class_item_id in", values, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdNotIn(List<Integer> values) {
            addCriterion("class_item_id not in", values, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdBetween(Integer value1, Integer value2) {
            addCriterion("class_item_id between", value1, value2, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_item_id not between", value1, value2, "classItemId");
            return (Criteria) this;
        }

        public Criteria andClassItemNameIsNull() {
            addCriterion("class_item_name is null");
            return (Criteria) this;
        }

        public Criteria andClassItemNameIsNotNull() {
            addCriterion("class_item_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassItemNameEqualTo(String value) {
            addCriterion("class_item_name =", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameNotEqualTo(String value) {
            addCriterion("class_item_name <>", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameGreaterThan(String value) {
            addCriterion("class_item_name >", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_item_name >=", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameLessThan(String value) {
            addCriterion("class_item_name <", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameLessThanOrEqualTo(String value) {
            addCriterion("class_item_name <=", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameLike(String value) {
            addCriterion("class_item_name like", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameNotLike(String value) {
            addCriterion("class_item_name not like", value, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameIn(List<String> values) {
            addCriterion("class_item_name in", values, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameNotIn(List<String> values) {
            addCriterion("class_item_name not in", values, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameBetween(String value1, String value2) {
            addCriterion("class_item_name between", value1, value2, "classItemName");
            return (Criteria) this;
        }

        public Criteria andClassItemNameNotBetween(String value1, String value2) {
            addCriterion("class_item_name not between", value1, value2, "classItemName");
            return (Criteria) this;
        }

        public Criteria andStudentNumIsNull() {
            addCriterion("student_num is null");
            return (Criteria) this;
        }

        public Criteria andStudentNumIsNotNull() {
            addCriterion("student_num is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNumEqualTo(Integer value) {
            addCriterion("student_num =", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumNotEqualTo(Integer value) {
            addCriterion("student_num <>", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumGreaterThan(Integer value) {
            addCriterion("student_num >", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_num >=", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumLessThan(Integer value) {
            addCriterion("student_num <", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumLessThanOrEqualTo(Integer value) {
            addCriterion("student_num <=", value, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumIn(List<Integer> values) {
            addCriterion("student_num in", values, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumNotIn(List<Integer> values) {
            addCriterion("student_num not in", values, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumBetween(Integer value1, Integer value2) {
            addCriterion("student_num between", value1, value2, "studentNum");
            return (Criteria) this;
        }

        public Criteria andStudentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("student_num not between", value1, value2, "studentNum");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andLessonPriceIsNull() {
            addCriterion("lesson_price is null");
            return (Criteria) this;
        }

        public Criteria andLessonPriceIsNotNull() {
            addCriterion("lesson_price is not null");
            return (Criteria) this;
        }

        public Criteria andLessonPriceEqualTo(Integer value) {
            addCriterion("lesson_price =", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceNotEqualTo(Integer value) {
            addCriterion("lesson_price <>", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceGreaterThan(Integer value) {
            addCriterion("lesson_price >", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("lesson_price >=", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceLessThan(Integer value) {
            addCriterion("lesson_price <", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceLessThanOrEqualTo(Integer value) {
            addCriterion("lesson_price <=", value, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceIn(List<Integer> values) {
            addCriterion("lesson_price in", values, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceNotIn(List<Integer> values) {
            addCriterion("lesson_price not in", values, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceBetween(Integer value1, Integer value2) {
            addCriterion("lesson_price between", value1, value2, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andLessonPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("lesson_price not between", value1, value2, "lessonPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceIsNull() {
            addCriterion("absenteeism_price is null");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceIsNotNull() {
            addCriterion("absenteeism_price is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceEqualTo(Integer value) {
            addCriterion("absenteeism_price =", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceNotEqualTo(Integer value) {
            addCriterion("absenteeism_price <>", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceGreaterThan(Integer value) {
            addCriterion("absenteeism_price >", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("absenteeism_price >=", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceLessThan(Integer value) {
            addCriterion("absenteeism_price <", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceLessThanOrEqualTo(Integer value) {
            addCriterion("absenteeism_price <=", value, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceIn(List<Integer> values) {
            addCriterion("absenteeism_price in", values, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceNotIn(List<Integer> values) {
            addCriterion("absenteeism_price not in", values, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceBetween(Integer value1, Integer value2) {
            addCriterion("absenteeism_price between", value1, value2, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("absenteeism_price not between", value1, value2, "absenteeismPrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceIsNull() {
            addCriterion("leave_price is null");
            return (Criteria) this;
        }

        public Criteria andLeavePriceIsNotNull() {
            addCriterion("leave_price is not null");
            return (Criteria) this;
        }

        public Criteria andLeavePriceEqualTo(Integer value) {
            addCriterion("leave_price =", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceNotEqualTo(Integer value) {
            addCriterion("leave_price <>", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceGreaterThan(Integer value) {
            addCriterion("leave_price >", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_price >=", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceLessThan(Integer value) {
            addCriterion("leave_price <", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceLessThanOrEqualTo(Integer value) {
            addCriterion("leave_price <=", value, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceIn(List<Integer> values) {
            addCriterion("leave_price in", values, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceNotIn(List<Integer> values) {
            addCriterion("leave_price not in", values, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceBetween(Integer value1, Integer value2) {
            addCriterion("leave_price between", value1, value2, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andLeavePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_price not between", value1, value2, "leavePrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceIsNull() {
            addCriterion("try_price is null");
            return (Criteria) this;
        }

        public Criteria andTryPriceIsNotNull() {
            addCriterion("try_price is not null");
            return (Criteria) this;
        }

        public Criteria andTryPriceEqualTo(Integer value) {
            addCriterion("try_price =", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceNotEqualTo(Integer value) {
            addCriterion("try_price <>", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceGreaterThan(Integer value) {
            addCriterion("try_price >", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("try_price >=", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceLessThan(Integer value) {
            addCriterion("try_price <", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceLessThanOrEqualTo(Integer value) {
            addCriterion("try_price <=", value, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceIn(List<Integer> values) {
            addCriterion("try_price in", values, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceNotIn(List<Integer> values) {
            addCriterion("try_price not in", values, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceBetween(Integer value1, Integer value2) {
            addCriterion("try_price between", value1, value2, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andTryPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("try_price not between", value1, value2, "tryPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceIsNull() {
            addCriterion("attend_price is null");
            return (Criteria) this;
        }

        public Criteria andAttendPriceIsNotNull() {
            addCriterion("attend_price is not null");
            return (Criteria) this;
        }

        public Criteria andAttendPriceEqualTo(Integer value) {
            addCriterion("attend_price =", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceNotEqualTo(Integer value) {
            addCriterion("attend_price <>", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceGreaterThan(Integer value) {
            addCriterion("attend_price >", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("attend_price >=", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceLessThan(Integer value) {
            addCriterion("attend_price <", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceLessThanOrEqualTo(Integer value) {
            addCriterion("attend_price <=", value, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceIn(List<Integer> values) {
            addCriterion("attend_price in", values, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceNotIn(List<Integer> values) {
            addCriterion("attend_price not in", values, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceBetween(Integer value1, Integer value2) {
            addCriterion("attend_price between", value1, value2, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andAttendPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("attend_price not between", value1, value2, "attendPrice");
            return (Criteria) this;
        }

        public Criteria andPrice01IsNull() {
            addCriterion("price_01 is null");
            return (Criteria) this;
        }

        public Criteria andPrice01IsNotNull() {
            addCriterion("price_01 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice01EqualTo(Integer value) {
            addCriterion("price_01 =", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01NotEqualTo(Integer value) {
            addCriterion("price_01 <>", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01GreaterThan(Integer value) {
            addCriterion("price_01 >", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01GreaterThanOrEqualTo(Integer value) {
            addCriterion("price_01 >=", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01LessThan(Integer value) {
            addCriterion("price_01 <", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01LessThanOrEqualTo(Integer value) {
            addCriterion("price_01 <=", value, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01In(List<Integer> values) {
            addCriterion("price_01 in", values, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01NotIn(List<Integer> values) {
            addCriterion("price_01 not in", values, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01Between(Integer value1, Integer value2) {
            addCriterion("price_01 between", value1, value2, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice01NotBetween(Integer value1, Integer value2) {
            addCriterion("price_01 not between", value1, value2, "price01");
            return (Criteria) this;
        }

        public Criteria andPrice5IsNull() {
            addCriterion("price5 is null");
            return (Criteria) this;
        }

        public Criteria andPrice5IsNotNull() {
            addCriterion("price5 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice5EqualTo(Integer value) {
            addCriterion("price5 =", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5NotEqualTo(Integer value) {
            addCriterion("price5 <>", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5GreaterThan(Integer value) {
            addCriterion("price5 >", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5GreaterThanOrEqualTo(Integer value) {
            addCriterion("price5 >=", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5LessThan(Integer value) {
            addCriterion("price5 <", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5LessThanOrEqualTo(Integer value) {
            addCriterion("price5 <=", value, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5In(List<Integer> values) {
            addCriterion("price5 in", values, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5NotIn(List<Integer> values) {
            addCriterion("price5 not in", values, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5Between(Integer value1, Integer value2) {
            addCriterion("price5 between", value1, value2, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice5NotBetween(Integer value1, Integer value2) {
            addCriterion("price5 not between", value1, value2, "price5");
            return (Criteria) this;
        }

        public Criteria andPrice6IsNull() {
            addCriterion("price6 is null");
            return (Criteria) this;
        }

        public Criteria andPrice6IsNotNull() {
            addCriterion("price6 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice6EqualTo(Integer value) {
            addCriterion("price6 =", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6NotEqualTo(Integer value) {
            addCriterion("price6 <>", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6GreaterThan(Integer value) {
            addCriterion("price6 >", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6GreaterThanOrEqualTo(Integer value) {
            addCriterion("price6 >=", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6LessThan(Integer value) {
            addCriterion("price6 <", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6LessThanOrEqualTo(Integer value) {
            addCriterion("price6 <=", value, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6In(List<Integer> values) {
            addCriterion("price6 in", values, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6NotIn(List<Integer> values) {
            addCriterion("price6 not in", values, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6Between(Integer value1, Integer value2) {
            addCriterion("price6 between", value1, value2, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice6NotBetween(Integer value1, Integer value2) {
            addCriterion("price6 not between", value1, value2, "price6");
            return (Criteria) this;
        }

        public Criteria andPrice7IsNull() {
            addCriterion("price7 is null");
            return (Criteria) this;
        }

        public Criteria andPrice7IsNotNull() {
            addCriterion("price7 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice7EqualTo(Integer value) {
            addCriterion("price7 =", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7NotEqualTo(Integer value) {
            addCriterion("price7 <>", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7GreaterThan(Integer value) {
            addCriterion("price7 >", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7GreaterThanOrEqualTo(Integer value) {
            addCriterion("price7 >=", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7LessThan(Integer value) {
            addCriterion("price7 <", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7LessThanOrEqualTo(Integer value) {
            addCriterion("price7 <=", value, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7In(List<Integer> values) {
            addCriterion("price7 in", values, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7NotIn(List<Integer> values) {
            addCriterion("price7 not in", values, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7Between(Integer value1, Integer value2) {
            addCriterion("price7 between", value1, value2, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice7NotBetween(Integer value1, Integer value2) {
            addCriterion("price7 not between", value1, value2, "price7");
            return (Criteria) this;
        }

        public Criteria andPrice8IsNull() {
            addCriterion("price8 is null");
            return (Criteria) this;
        }

        public Criteria andPrice8IsNotNull() {
            addCriterion("price8 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice8EqualTo(Integer value) {
            addCriterion("price8 =", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8NotEqualTo(Integer value) {
            addCriterion("price8 <>", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8GreaterThan(Integer value) {
            addCriterion("price8 >", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8GreaterThanOrEqualTo(Integer value) {
            addCriterion("price8 >=", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8LessThan(Integer value) {
            addCriterion("price8 <", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8LessThanOrEqualTo(Integer value) {
            addCriterion("price8 <=", value, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8In(List<Integer> values) {
            addCriterion("price8 in", values, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8NotIn(List<Integer> values) {
            addCriterion("price8 not in", values, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8Between(Integer value1, Integer value2) {
            addCriterion("price8 between", value1, value2, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice8NotBetween(Integer value1, Integer value2) {
            addCriterion("price8 not between", value1, value2, "price8");
            return (Criteria) this;
        }

        public Criteria andPrice9IsNull() {
            addCriterion("price9 is null");
            return (Criteria) this;
        }

        public Criteria andPrice9IsNotNull() {
            addCriterion("price9 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice9EqualTo(Integer value) {
            addCriterion("price9 =", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9NotEqualTo(Integer value) {
            addCriterion("price9 <>", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9GreaterThan(Integer value) {
            addCriterion("price9 >", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9GreaterThanOrEqualTo(Integer value) {
            addCriterion("price9 >=", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9LessThan(Integer value) {
            addCriterion("price9 <", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9LessThanOrEqualTo(Integer value) {
            addCriterion("price9 <=", value, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9In(List<Integer> values) {
            addCriterion("price9 in", values, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9NotIn(List<Integer> values) {
            addCriterion("price9 not in", values, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9Between(Integer value1, Integer value2) {
            addCriterion("price9 between", value1, value2, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice9NotBetween(Integer value1, Integer value2) {
            addCriterion("price9 not between", value1, value2, "price9");
            return (Criteria) this;
        }

        public Criteria andPrice02IsNull() {
            addCriterion("price02 is null");
            return (Criteria) this;
        }

        public Criteria andPrice02IsNotNull() {
            addCriterion("price02 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice02EqualTo(Integer value) {
            addCriterion("price02 =", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02NotEqualTo(Integer value) {
            addCriterion("price02 <>", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02GreaterThan(Integer value) {
            addCriterion("price02 >", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02GreaterThanOrEqualTo(Integer value) {
            addCriterion("price02 >=", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02LessThan(Integer value) {
            addCriterion("price02 <", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02LessThanOrEqualTo(Integer value) {
            addCriterion("price02 <=", value, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02In(List<Integer> values) {
            addCriterion("price02 in", values, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02NotIn(List<Integer> values) {
            addCriterion("price02 not in", values, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02Between(Integer value1, Integer value2) {
            addCriterion("price02 between", value1, value2, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice02NotBetween(Integer value1, Integer value2) {
            addCriterion("price02 not between", value1, value2, "price02");
            return (Criteria) this;
        }

        public Criteria andPrice03IsNull() {
            addCriterion("price03 is null");
            return (Criteria) this;
        }

        public Criteria andPrice03IsNotNull() {
            addCriterion("price03 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice03EqualTo(Integer value) {
            addCriterion("price03 =", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03NotEqualTo(Integer value) {
            addCriterion("price03 <>", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03GreaterThan(Integer value) {
            addCriterion("price03 >", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03GreaterThanOrEqualTo(Integer value) {
            addCriterion("price03 >=", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03LessThan(Integer value) {
            addCriterion("price03 <", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03LessThanOrEqualTo(Integer value) {
            addCriterion("price03 <=", value, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03In(List<Integer> values) {
            addCriterion("price03 in", values, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03NotIn(List<Integer> values) {
            addCriterion("price03 not in", values, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03Between(Integer value1, Integer value2) {
            addCriterion("price03 between", value1, value2, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice03NotBetween(Integer value1, Integer value2) {
            addCriterion("price03 not between", value1, value2, "price03");
            return (Criteria) this;
        }

        public Criteria andPrice10IsNull() {
            addCriterion("price10 is null");
            return (Criteria) this;
        }

        public Criteria andPrice10IsNotNull() {
            addCriterion("price10 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice10EqualTo(Integer value) {
            addCriterion("price10 =", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10NotEqualTo(Integer value) {
            addCriterion("price10 <>", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10GreaterThan(Integer value) {
            addCriterion("price10 >", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10GreaterThanOrEqualTo(Integer value) {
            addCriterion("price10 >=", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10LessThan(Integer value) {
            addCriterion("price10 <", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10LessThanOrEqualTo(Integer value) {
            addCriterion("price10 <=", value, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10In(List<Integer> values) {
            addCriterion("price10 in", values, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10NotIn(List<Integer> values) {
            addCriterion("price10 not in", values, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10Between(Integer value1, Integer value2) {
            addCriterion("price10 between", value1, value2, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice10NotBetween(Integer value1, Integer value2) {
            addCriterion("price10 not between", value1, value2, "price10");
            return (Criteria) this;
        }

        public Criteria andPrice11IsNull() {
            addCriterion("price11 is null");
            return (Criteria) this;
        }

        public Criteria andPrice11IsNotNull() {
            addCriterion("price11 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice11EqualTo(Integer value) {
            addCriterion("price11 =", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11NotEqualTo(Integer value) {
            addCriterion("price11 <>", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11GreaterThan(Integer value) {
            addCriterion("price11 >", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11GreaterThanOrEqualTo(Integer value) {
            addCriterion("price11 >=", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11LessThan(Integer value) {
            addCriterion("price11 <", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11LessThanOrEqualTo(Integer value) {
            addCriterion("price11 <=", value, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11In(List<Integer> values) {
            addCriterion("price11 in", values, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11NotIn(List<Integer> values) {
            addCriterion("price11 not in", values, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11Between(Integer value1, Integer value2) {
            addCriterion("price11 between", value1, value2, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice11NotBetween(Integer value1, Integer value2) {
            addCriterion("price11 not between", value1, value2, "price11");
            return (Criteria) this;
        }

        public Criteria andPrice12IsNull() {
            addCriterion("price12 is null");
            return (Criteria) this;
        }

        public Criteria andPrice12IsNotNull() {
            addCriterion("price12 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice12EqualTo(Integer value) {
            addCriterion("price12 =", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12NotEqualTo(Integer value) {
            addCriterion("price12 <>", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12GreaterThan(Integer value) {
            addCriterion("price12 >", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12GreaterThanOrEqualTo(Integer value) {
            addCriterion("price12 >=", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12LessThan(Integer value) {
            addCriterion("price12 <", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12LessThanOrEqualTo(Integer value) {
            addCriterion("price12 <=", value, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12In(List<Integer> values) {
            addCriterion("price12 in", values, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12NotIn(List<Integer> values) {
            addCriterion("price12 not in", values, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12Between(Integer value1, Integer value2) {
            addCriterion("price12 between", value1, value2, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice12NotBetween(Integer value1, Integer value2) {
            addCriterion("price12 not between", value1, value2, "price12");
            return (Criteria) this;
        }

        public Criteria andPrice13IsNull() {
            addCriterion("price13 is null");
            return (Criteria) this;
        }

        public Criteria andPrice13IsNotNull() {
            addCriterion("price13 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice13EqualTo(Integer value) {
            addCriterion("price13 =", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13NotEqualTo(Integer value) {
            addCriterion("price13 <>", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13GreaterThan(Integer value) {
            addCriterion("price13 >", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13GreaterThanOrEqualTo(Integer value) {
            addCriterion("price13 >=", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13LessThan(Integer value) {
            addCriterion("price13 <", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13LessThanOrEqualTo(Integer value) {
            addCriterion("price13 <=", value, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13In(List<Integer> values) {
            addCriterion("price13 in", values, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13NotIn(List<Integer> values) {
            addCriterion("price13 not in", values, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13Between(Integer value1, Integer value2) {
            addCriterion("price13 between", value1, value2, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice13NotBetween(Integer value1, Integer value2) {
            addCriterion("price13 not between", value1, value2, "price13");
            return (Criteria) this;
        }

        public Criteria andPrice14IsNull() {
            addCriterion("price14 is null");
            return (Criteria) this;
        }

        public Criteria andPrice14IsNotNull() {
            addCriterion("price14 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice14EqualTo(Integer value) {
            addCriterion("price14 =", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14NotEqualTo(Integer value) {
            addCriterion("price14 <>", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14GreaterThan(Integer value) {
            addCriterion("price14 >", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14GreaterThanOrEqualTo(Integer value) {
            addCriterion("price14 >=", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14LessThan(Integer value) {
            addCriterion("price14 <", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14LessThanOrEqualTo(Integer value) {
            addCriterion("price14 <=", value, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14In(List<Integer> values) {
            addCriterion("price14 in", values, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14NotIn(List<Integer> values) {
            addCriterion("price14 not in", values, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14Between(Integer value1, Integer value2) {
            addCriterion("price14 between", value1, value2, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice14NotBetween(Integer value1, Integer value2) {
            addCriterion("price14 not between", value1, value2, "price14");
            return (Criteria) this;
        }

        public Criteria andPrice15IsNull() {
            addCriterion("price15 is null");
            return (Criteria) this;
        }

        public Criteria andPrice15IsNotNull() {
            addCriterion("price15 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice15EqualTo(Integer value) {
            addCriterion("price15 =", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15NotEqualTo(Integer value) {
            addCriterion("price15 <>", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15GreaterThan(Integer value) {
            addCriterion("price15 >", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15GreaterThanOrEqualTo(Integer value) {
            addCriterion("price15 >=", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15LessThan(Integer value) {
            addCriterion("price15 <", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15LessThanOrEqualTo(Integer value) {
            addCriterion("price15 <=", value, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15In(List<Integer> values) {
            addCriterion("price15 in", values, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15NotIn(List<Integer> values) {
            addCriterion("price15 not in", values, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15Between(Integer value1, Integer value2) {
            addCriterion("price15 between", value1, value2, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice15NotBetween(Integer value1, Integer value2) {
            addCriterion("price15 not between", value1, value2, "price15");
            return (Criteria) this;
        }

        public Criteria andPrice16IsNull() {
            addCriterion("price16 is null");
            return (Criteria) this;
        }

        public Criteria andPrice16IsNotNull() {
            addCriterion("price16 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice16EqualTo(Integer value) {
            addCriterion("price16 =", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16NotEqualTo(Integer value) {
            addCriterion("price16 <>", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16GreaterThan(Integer value) {
            addCriterion("price16 >", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16GreaterThanOrEqualTo(Integer value) {
            addCriterion("price16 >=", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16LessThan(Integer value) {
            addCriterion("price16 <", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16LessThanOrEqualTo(Integer value) {
            addCriterion("price16 <=", value, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16In(List<Integer> values) {
            addCriterion("price16 in", values, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16NotIn(List<Integer> values) {
            addCriterion("price16 not in", values, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16Between(Integer value1, Integer value2) {
            addCriterion("price16 between", value1, value2, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice16NotBetween(Integer value1, Integer value2) {
            addCriterion("price16 not between", value1, value2, "price16");
            return (Criteria) this;
        }

        public Criteria andPrice17IsNull() {
            addCriterion("price17 is null");
            return (Criteria) this;
        }

        public Criteria andPrice17IsNotNull() {
            addCriterion("price17 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice17EqualTo(Integer value) {
            addCriterion("price17 =", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17NotEqualTo(Integer value) {
            addCriterion("price17 <>", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17GreaterThan(Integer value) {
            addCriterion("price17 >", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17GreaterThanOrEqualTo(Integer value) {
            addCriterion("price17 >=", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17LessThan(Integer value) {
            addCriterion("price17 <", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17LessThanOrEqualTo(Integer value) {
            addCriterion("price17 <=", value, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17In(List<Integer> values) {
            addCriterion("price17 in", values, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17NotIn(List<Integer> values) {
            addCriterion("price17 not in", values, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17Between(Integer value1, Integer value2) {
            addCriterion("price17 between", value1, value2, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice17NotBetween(Integer value1, Integer value2) {
            addCriterion("price17 not between", value1, value2, "price17");
            return (Criteria) this;
        }

        public Criteria andPrice18IsNull() {
            addCriterion("price18 is null");
            return (Criteria) this;
        }

        public Criteria andPrice18IsNotNull() {
            addCriterion("price18 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice18EqualTo(Integer value) {
            addCriterion("price18 =", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18NotEqualTo(Integer value) {
            addCriterion("price18 <>", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18GreaterThan(Integer value) {
            addCriterion("price18 >", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18GreaterThanOrEqualTo(Integer value) {
            addCriterion("price18 >=", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18LessThan(Integer value) {
            addCriterion("price18 <", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18LessThanOrEqualTo(Integer value) {
            addCriterion("price18 <=", value, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18In(List<Integer> values) {
            addCriterion("price18 in", values, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18NotIn(List<Integer> values) {
            addCriterion("price18 not in", values, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18Between(Integer value1, Integer value2) {
            addCriterion("price18 between", value1, value2, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice18NotBetween(Integer value1, Integer value2) {
            addCriterion("price18 not between", value1, value2, "price18");
            return (Criteria) this;
        }

        public Criteria andPrice19IsNull() {
            addCriterion("price19 is null");
            return (Criteria) this;
        }

        public Criteria andPrice19IsNotNull() {
            addCriterion("price19 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice19EqualTo(Integer value) {
            addCriterion("price19 =", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19NotEqualTo(Integer value) {
            addCriterion("price19 <>", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19GreaterThan(Integer value) {
            addCriterion("price19 >", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19GreaterThanOrEqualTo(Integer value) {
            addCriterion("price19 >=", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19LessThan(Integer value) {
            addCriterion("price19 <", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19LessThanOrEqualTo(Integer value) {
            addCriterion("price19 <=", value, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19In(List<Integer> values) {
            addCriterion("price19 in", values, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19NotIn(List<Integer> values) {
            addCriterion("price19 not in", values, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19Between(Integer value1, Integer value2) {
            addCriterion("price19 between", value1, value2, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice19NotBetween(Integer value1, Integer value2) {
            addCriterion("price19 not between", value1, value2, "price19");
            return (Criteria) this;
        }

        public Criteria andPrice20IsNull() {
            addCriterion("price20 is null");
            return (Criteria) this;
        }

        public Criteria andPrice20IsNotNull() {
            addCriterion("price20 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice20EqualTo(Integer value) {
            addCriterion("price20 =", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20NotEqualTo(Integer value) {
            addCriterion("price20 <>", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20GreaterThan(Integer value) {
            addCriterion("price20 >", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20GreaterThanOrEqualTo(Integer value) {
            addCriterion("price20 >=", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20LessThan(Integer value) {
            addCriterion("price20 <", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20LessThanOrEqualTo(Integer value) {
            addCriterion("price20 <=", value, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20In(List<Integer> values) {
            addCriterion("price20 in", values, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20NotIn(List<Integer> values) {
            addCriterion("price20 not in", values, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20Between(Integer value1, Integer value2) {
            addCriterion("price20 between", value1, value2, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice20NotBetween(Integer value1, Integer value2) {
            addCriterion("price20 not between", value1, value2, "price20");
            return (Criteria) this;
        }

        public Criteria andPrice21IsNull() {
            addCriterion("price21 is null");
            return (Criteria) this;
        }

        public Criteria andPrice21IsNotNull() {
            addCriterion("price21 is not null");
            return (Criteria) this;
        }

        public Criteria andPrice21EqualTo(Integer value) {
            addCriterion("price21 =", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21NotEqualTo(Integer value) {
            addCriterion("price21 <>", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21GreaterThan(Integer value) {
            addCriterion("price21 >", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21GreaterThanOrEqualTo(Integer value) {
            addCriterion("price21 >=", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21LessThan(Integer value) {
            addCriterion("price21 <", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21LessThanOrEqualTo(Integer value) {
            addCriterion("price21 <=", value, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21In(List<Integer> values) {
            addCriterion("price21 in", values, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21NotIn(List<Integer> values) {
            addCriterion("price21 not in", values, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21Between(Integer value1, Integer value2) {
            addCriterion("price21 between", value1, value2, "price21");
            return (Criteria) this;
        }

        public Criteria andPrice21NotBetween(Integer value1, Integer value2) {
            addCriterion("price21 not between", value1, value2, "price21");
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
package com.yoohoo.en.dao.model;

import com.yoohoo.en.dao.PageHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TStudentInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    protected PageHelper pageHelper;

    public TStudentInfoExample() {
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

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper=pageHelper;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNull() {
            addCriterion("en_name is null");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNotNull() {
            addCriterion("en_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnNameEqualTo(String value) {
            addCriterion("en_name =", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotEqualTo(String value) {
            addCriterion("en_name <>", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThan(String value) {
            addCriterion("en_name >", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("en_name >=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThan(String value) {
            addCriterion("en_name <", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThanOrEqualTo(String value) {
            addCriterion("en_name <=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLike(String value) {
            addCriterion("en_name like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotLike(String value) {
            addCriterion("en_name not like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameIn(List<String> values) {
            addCriterion("en_name in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotIn(List<String> values) {
            addCriterion("en_name not in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameBetween(String value1, String value2) {
            addCriterion("en_name between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotBetween(String value1, String value2) {
            addCriterion("en_name not between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Integer value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Integer value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Integer value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Integer value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Integer> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Integer> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Integer value1, Integer value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceIsNull() {
            addCriterion("earb_balance is null");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceIsNotNull() {
            addCriterion("earb_balance is not null");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceEqualTo(Integer value) {
            addCriterion("earb_balance =", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceNotEqualTo(Integer value) {
            addCriterion("earb_balance <>", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceGreaterThan(Integer value) {
            addCriterion("earb_balance >", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("earb_balance >=", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceLessThan(Integer value) {
            addCriterion("earb_balance <", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("earb_balance <=", value, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceIn(List<Integer> values) {
            addCriterion("earb_balance in", values, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceNotIn(List<Integer> values) {
            addCriterion("earb_balance not in", values, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceBetween(Integer value1, Integer value2) {
            addCriterion("earb_balance between", value1, value2, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andEarbBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("earb_balance not between", value1, value2, "earbBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceIsNull() {
            addCriterion("present_balance is null");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceIsNotNull() {
            addCriterion("present_balance is not null");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceEqualTo(Integer value) {
            addCriterion("present_balance =", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceNotEqualTo(Integer value) {
            addCriterion("present_balance <>", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceGreaterThan(Integer value) {
            addCriterion("present_balance >", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("present_balance >=", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceLessThan(Integer value) {
            addCriterion("present_balance <", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("present_balance <=", value, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceIn(List<Integer> values) {
            addCriterion("present_balance in", values, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceNotIn(List<Integer> values) {
            addCriterion("present_balance not in", values, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceBetween(Integer value1, Integer value2) {
            addCriterion("present_balance between", value1, value2, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andPresentBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("present_balance not between", value1, value2, "presentBalance");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNull() {
            addCriterion("msisdn is null");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNotNull() {
            addCriterion("msisdn is not null");
            return (Criteria) this;
        }

        public Criteria andMsisdnEqualTo(String value) {
            addCriterion("msisdn =", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotEqualTo(String value) {
            addCriterion("msisdn <>", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThan(String value) {
            addCriterion("msisdn >", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThanOrEqualTo(String value) {
            addCriterion("msisdn >=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThan(String value) {
            addCriterion("msisdn <", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThanOrEqualTo(String value) {
            addCriterion("msisdn <=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLike(String value) {
            addCriterion("msisdn like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotLike(String value) {
            addCriterion("msisdn not like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnIn(List<String> values) {
            addCriterion("msisdn in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotIn(List<String> values) {
            addCriterion("msisdn not in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnBetween(String value1, String value2) {
            addCriterion("msisdn between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotBetween(String value1, String value2) {
            addCriterion("msisdn not between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRegStatusIsNull() {
            addCriterion("reg_status is null");
            return (Criteria) this;
        }

        public Criteria andRegStatusIsNotNull() {
            addCriterion("reg_status is not null");
            return (Criteria) this;
        }

        public Criteria andRegStatusEqualTo(String value) {
            addCriterion("reg_status =", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusNotEqualTo(String value) {
            addCriterion("reg_status <>", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusGreaterThan(String value) {
            addCriterion("reg_status >", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusGreaterThanOrEqualTo(String value) {
            addCriterion("reg_status >=", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusLessThan(String value) {
            addCriterion("reg_status <", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusLessThanOrEqualTo(String value) {
            addCriterion("reg_status <=", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusLike(String value) {
            addCriterion("reg_status like", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusNotLike(String value) {
            addCriterion("reg_status not like", value, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusIn(List<String> values) {
            addCriterion("reg_status in", values, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusNotIn(List<String> values) {
            addCriterion("reg_status not in", values, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusBetween(String value1, String value2) {
            addCriterion("reg_status between", value1, value2, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegStatusNotBetween(String value1, String value2) {
            addCriterion("reg_status not between", value1, value2, "regStatus");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNull() {
            addCriterion("reg_time is null");
            return (Criteria) this;
        }

        public Criteria andRegTimeIsNotNull() {
            addCriterion("reg_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegTimeEqualTo(Date value) {
            addCriterion("reg_time =", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotEqualTo(Date value) {
            addCriterion("reg_time <>", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThan(Date value) {
            addCriterion("reg_time >", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_time >=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThan(Date value) {
            addCriterion("reg_time <", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeLessThanOrEqualTo(Date value) {
            addCriterion("reg_time <=", value, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeIn(List<Date> values) {
            addCriterion("reg_time in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotIn(List<Date> values) {
            addCriterion("reg_time not in", values, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeBetween(Date value1, Date value2) {
            addCriterion("reg_time between", value1, value2, "regTime");
            return (Criteria) this;
        }

        public Criteria andRegTimeNotBetween(Date value1, Date value2) {
            addCriterion("reg_time not between", value1, value2, "regTime");
            return (Criteria) this;
        }

        public Criteria andFromAdminIsNull() {
            addCriterion("from_admin is null");
            return (Criteria) this;
        }

        public Criteria andFromAdminIsNotNull() {
            addCriterion("from_admin is not null");
            return (Criteria) this;
        }

        public Criteria andFromAdminEqualTo(Integer value) {
            addCriterion("from_admin =", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminNotEqualTo(Integer value) {
            addCriterion("from_admin <>", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminGreaterThan(Integer value) {
            addCriterion("from_admin >", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_admin >=", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminLessThan(Integer value) {
            addCriterion("from_admin <", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminLessThanOrEqualTo(Integer value) {
            addCriterion("from_admin <=", value, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminIn(List<Integer> values) {
            addCriterion("from_admin in", values, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminNotIn(List<Integer> values) {
            addCriterion("from_admin not in", values, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminBetween(Integer value1, Integer value2) {
            addCriterion("from_admin between", value1, value2, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andFromAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("from_admin not between", value1, value2, "fromAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminIsNull() {
            addCriterion("last_update_admin is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminIsNotNull() {
            addCriterion("last_update_admin is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminEqualTo(Integer value) {
            addCriterion("last_update_admin =", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminNotEqualTo(Integer value) {
            addCriterion("last_update_admin <>", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminGreaterThan(Integer value) {
            addCriterion("last_update_admin >", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_update_admin >=", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminLessThan(Integer value) {
            addCriterion("last_update_admin <", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminLessThanOrEqualTo(Integer value) {
            addCriterion("last_update_admin <=", value, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminIn(List<Integer> values) {
            addCriterion("last_update_admin in", values, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminNotIn(List<Integer> values) {
            addCriterion("last_update_admin not in", values, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminBetween(Integer value1, Integer value2) {
            addCriterion("last_update_admin between", value1, value2, "lastUpdateAdmin");
            return (Criteria) this;
        }

        public Criteria andLastUpdateAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("last_update_admin not between", value1, value2, "lastUpdateAdmin");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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
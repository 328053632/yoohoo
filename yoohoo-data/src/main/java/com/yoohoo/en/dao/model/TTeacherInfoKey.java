package com.yoohoo.en.dao.model;

import java.io.Serializable;

public class TTeacherInfoKey implements Serializable {
    private Integer teacherId;

    private String country;

    private static final long serialVersionUID = 1L;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }
}
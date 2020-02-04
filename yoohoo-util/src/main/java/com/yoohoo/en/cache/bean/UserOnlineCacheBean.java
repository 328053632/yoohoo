package com.yoohoo.en.cache.bean;

import java.io.Serializable;

public class UserOnlineCacheBean implements Serializable {

	/**
     * 注释内容
     */
    private static final long serialVersionUID = 7812328854352321288L;
    private String userID;
	private String schoolID;
	private String sessonID;
	private int userType = 0;

	
	/**
     * @return 返回 sessonID
     */
    public String getSessonID()
    {
        return sessonID;
    }

    /**
     * @param 对sessonID进行赋值
     */
    public void setSessonID(String sessonID)
    {
        this.sessonID = sessonID;
    }

    public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}

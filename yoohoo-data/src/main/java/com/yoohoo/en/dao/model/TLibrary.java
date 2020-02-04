package com.yoohoo.en.dao.model;

import java.util.Date;

import javax.persistence.Column;

import com.yoohoo.en.dao.model.common.BaseInfoModel;

public class TLibrary extends BaseInfoModel {
	@Column
    private Integer fileId;

	@Column
    private String fileName;

	@Column
    private String fileUrl;

    private Integer fileType;

	@Column
    private String fileSize;

	@Column
    private Date createTime;

	@Column
    private String uplaodUser;

	@Column
    private Integer talkFileId;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUplaodUser() {
        return uplaodUser;
    }

    public void setUplaodUser(String uplaodUser) {
        this.uplaodUser = uplaodUser == null ? null : uplaodUser.trim();
    }

    public Integer getTalkFileId() {
        return talkFileId;
    }

    public void setTalkFileId(Integer talkFileId) {
        this.talkFileId = talkFileId;
    }
}
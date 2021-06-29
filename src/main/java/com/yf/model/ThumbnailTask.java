package com.yf.model;

import java.util.Date;

public class ThumbnailTask {
    private Long id;

    private Long coursewareId;

    private Byte coursewareType;

    private Byte encodeStatus;

    private Integer errorCode;

    private String errorMsg;

    private Date startTime;

    private Date lastUpdateTime;

    private Date submitTime;

    public ThumbnailTask(Long id, Long coursewareId, Byte coursewareType, Byte encodeStatus, Integer errorCode, String errorMsg, Date startTime, Date lastUpdateTime, Date submitTime) {
        this.id = id;
        this.coursewareId = coursewareId;
        this.coursewareType = coursewareType;
        this.encodeStatus = encodeStatus;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.startTime = startTime;
        this.lastUpdateTime = lastUpdateTime;
        this.submitTime = submitTime;
    }

    public ThumbnailTask() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Long coursewareId) {
        this.coursewareId = coursewareId;
    }

    public Byte getCoursewareType() {
        return coursewareType;
    }

    public void setCoursewareType(Byte coursewareType) {
        this.coursewareType = coursewareType;
    }

    public Byte getEncodeStatus() {
        return encodeStatus;
    }

    public void setEncodeStatus(Byte encodeStatus) {
        this.encodeStatus = encodeStatus;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
package com.yf.model;

import java.util.Date;

public class AibeikeOperateLog {
    private Long id;

    private String aibeikeMediaId;

    private Byte aibeikeMediaType;

    private Long coursewareOnshelfId;

    private Long coursewareReferenceId;

    private Long userId;

    private Byte userType;

    private Date createTime;

    private Byte source;

    private Byte version;

    public AibeikeOperateLog(Long id, String aibeikeMediaId, Byte aibeikeMediaType, Long coursewareOnshelfId, Long coursewareReferenceId, Long userId, Byte userType, Date createTime, Byte source, Byte version) {
        this.id = id;
        this.aibeikeMediaId = aibeikeMediaId;
        this.aibeikeMediaType = aibeikeMediaType;
        this.coursewareOnshelfId = coursewareOnshelfId;
        this.coursewareReferenceId = coursewareReferenceId;
        this.userId = userId;
        this.userType = userType;
        this.createTime = createTime;
        this.source = source;
        this.version = version;
    }

    public AibeikeOperateLog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAibeikeMediaId() {
        return aibeikeMediaId;
    }

    public void setAibeikeMediaId(String aibeikeMediaId) {
        this.aibeikeMediaId = aibeikeMediaId == null ? null : aibeikeMediaId.trim();
    }

    public Byte getAibeikeMediaType() {
        return aibeikeMediaType;
    }

    public void setAibeikeMediaType(Byte aibeikeMediaType) {
        this.aibeikeMediaType = aibeikeMediaType;
    }

    public Long getCoursewareOnshelfId() {
        return coursewareOnshelfId;
    }

    public void setCoursewareOnshelfId(Long coursewareOnshelfId) {
        this.coursewareOnshelfId = coursewareOnshelfId;
    }

    public Long getCoursewareReferenceId() {
        return coursewareReferenceId;
    }

    public void setCoursewareReferenceId(Long coursewareReferenceId) {
        this.coursewareReferenceId = coursewareReferenceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
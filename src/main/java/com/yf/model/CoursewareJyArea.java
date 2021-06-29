package com.yf.model;

import java.util.Date;

public class CoursewareJyArea {
    private Long id;

    private Long coursewareOnshelfId;

    private Integer jyAreaId;

    private Date createTime;

    private Date lastUpdateTime;

    private Byte isDeleted;

    public CoursewareJyArea(Long id, Long coursewareOnshelfId, Integer jyAreaId, Date createTime, Date lastUpdateTime, Byte isDeleted) {
        this.id = id;
        this.coursewareOnshelfId = coursewareOnshelfId;
        this.jyAreaId = jyAreaId;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.isDeleted = isDeleted;
    }

    public CoursewareJyArea() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoursewareOnshelfId() {
        return coursewareOnshelfId;
    }

    public void setCoursewareOnshelfId(Long coursewareOnshelfId) {
        this.coursewareOnshelfId = coursewareOnshelfId;
    }

    public Integer getJyAreaId() {
        return jyAreaId;
    }

    public void setJyAreaId(Integer jyAreaId) {
        this.jyAreaId = jyAreaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}
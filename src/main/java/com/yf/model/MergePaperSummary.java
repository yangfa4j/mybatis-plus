package com.yf.model;

import java.util.Date;

public class MergePaperSummary {
    private Long id;

    private String businessId;

    private String originalPaperIds;

    private Long userId;

    private Byte userType;

    private Byte status;

    private String destPaperId;

    private Byte isDeleted;

    private Date createTime;

    private Date finishTime;

    private Date lastUpdateTime;

    public MergePaperSummary(Long id, String businessId, String originalPaperIds, Long userId, Byte userType, Byte status, String destPaperId, Byte isDeleted, Date createTime, Date finishTime, Date lastUpdateTime) {
        this.id = id;
        this.businessId = businessId;
        this.originalPaperIds = originalPaperIds;
        this.userId = userId;
        this.userType = userType;
        this.status = status;
        this.destPaperId = destPaperId;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public MergePaperSummary() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getOriginalPaperIds() {
        return originalPaperIds;
    }

    public void setOriginalPaperIds(String originalPaperIds) {
        this.originalPaperIds = originalPaperIds == null ? null : originalPaperIds.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDestPaperId() {
        return destPaperId;
    }

    public void setDestPaperId(String destPaperId) {
        this.destPaperId = destPaperId == null ? null : destPaperId.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
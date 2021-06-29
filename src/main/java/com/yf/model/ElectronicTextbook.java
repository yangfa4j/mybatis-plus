package com.yf.model;

import java.util.Date;

public class ElectronicTextbook {
    private Integer id;

    private String name;

    private Integer gradeId;

    private Integer studySectionId;

    private Integer subjectId;

    private Integer textbookVersionId;

    private Byte status;

    private Byte deleteStatus;

    private Integer pictureImgId;

    private String pdfMediaId;

    private String externalUrl;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    public ElectronicTextbook(Integer id, String name, Integer gradeId, Integer studySectionId, Integer subjectId, Integer textbookVersionId, Byte status, Byte deleteStatus, Integer pictureImgId, String pdfMediaId, String externalUrl, Date createTime, Integer createUserId, String createUserName, Date updateTime, Integer updateUserId, String updateUserName) {
        this.id = id;
        this.name = name;
        this.gradeId = gradeId;
        this.studySectionId = studySectionId;
        this.subjectId = subjectId;
        this.textbookVersionId = textbookVersionId;
        this.status = status;
        this.deleteStatus = deleteStatus;
        this.pictureImgId = pictureImgId;
        this.pdfMediaId = pdfMediaId;
        this.externalUrl = externalUrl;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.updateTime = updateTime;
        this.updateUserId = updateUserId;
        this.updateUserName = updateUserName;
    }

    public ElectronicTextbook() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getStudySectionId() {
        return studySectionId;
    }

    public void setStudySectionId(Integer studySectionId) {
        this.studySectionId = studySectionId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTextbookVersionId() {
        return textbookVersionId;
    }

    public void setTextbookVersionId(Integer textbookVersionId) {
        this.textbookVersionId = textbookVersionId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Byte deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPictureImgId() {
        return pictureImgId;
    }

    public void setPictureImgId(Integer pictureImgId) {
        this.pictureImgId = pictureImgId;
    }

    public String getPdfMediaId() {
        return pdfMediaId;
    }

    public void setPdfMediaId(String pdfMediaId) {
        this.pdfMediaId = pdfMediaId == null ? null : pdfMediaId.trim();
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl == null ? null : externalUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }
}
package com.yf.dao;

import com.yf.model.MergePaperSummary;

public interface MergePaperSummaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MergePaperSummary record);

    int insertSelective(MergePaperSummary record);

    MergePaperSummary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MergePaperSummary record);

    int updateByPrimaryKey(MergePaperSummary record);
}
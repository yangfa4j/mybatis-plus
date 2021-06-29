package com.yf.dao;

import com.yf.model.AibeikeOperateLog;

public interface AibeikeOperateLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AibeikeOperateLog record);

    int insertSelective(AibeikeOperateLog record);

    AibeikeOperateLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AibeikeOperateLog record);

    int updateByPrimaryKey(AibeikeOperateLog record);
}
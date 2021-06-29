package com.yf.dao;

import com.yf.model.ThumbnailTask;

public interface ThumbnailTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThumbnailTask record);

    int insertSelective(ThumbnailTask record);

    ThumbnailTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThumbnailTask record);

    int updateByPrimaryKey(ThumbnailTask record);
}
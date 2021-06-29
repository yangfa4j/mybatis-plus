package com.yf.dao;

import com.yf.model.CoursewareJyArea;

public interface CoursewareJyAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoursewareJyArea record);

    int insertSelective(CoursewareJyArea record);

    CoursewareJyArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoursewareJyArea record);

    int updateByPrimaryKey(CoursewareJyArea record);
}
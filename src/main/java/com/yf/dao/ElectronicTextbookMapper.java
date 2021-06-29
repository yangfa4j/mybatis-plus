package com.yf.dao;

import com.yf.model.ElectronicTextbook;

public interface ElectronicTextbookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElectronicTextbook record);

    int insertSelective(ElectronicTextbook record);

    ElectronicTextbook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElectronicTextbook record);

    int updateByPrimaryKey(ElectronicTextbook record);
}
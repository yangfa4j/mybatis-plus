package com.test.mapstruct.order;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // 只需定义顶层映射，MapStruct 会自动递归处理所有嵌套字段
    OrderDto orderToOrderDto(Order order);
}
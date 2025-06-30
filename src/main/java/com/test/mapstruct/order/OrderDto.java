package com.test.mapstruct.order;

import lombok.Data;

@Data
// DTO 类（结构与实体类完全一致）
public class OrderDto {
    private Long id;
    private String orderNo;
    private CustomerDto customer; // 嵌套的客户 DTO
    // Getter/Setter
}
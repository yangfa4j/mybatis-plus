package com.test.mapstruct.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private Customer customer; // 嵌套的客户对象
    // Getter/Setter
}
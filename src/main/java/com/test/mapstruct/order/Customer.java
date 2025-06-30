package com.test.mapstruct.order;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private Address address; // 嵌套的地址对象
    // Getter/Setter
}
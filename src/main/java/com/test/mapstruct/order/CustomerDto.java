package com.test.mapstruct.order;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private AddressDto address; // 嵌套的地址 DTO
    // Getter/Setter
}
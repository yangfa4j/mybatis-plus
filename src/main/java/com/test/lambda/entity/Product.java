package com.test.lambda.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date 2020/12/14
 * @Author yangfa
 * @Description
 */
@Data
public class Product {

    private Long id;
    private Integer num;
    private BigDecimal price;
    private String name;
    private String category;

    public Product(Long id, Integer num, BigDecimal price, String name, String category) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.name = name;
        this.category = category;
    }
}

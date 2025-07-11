package com.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yangfa
 * @date 2025/7/11 14:51
 * IntelliJ IDEA
 * @description
 */
public class CamelToSnakeCase {

    public static void main(String[] args) {
        System.out.println(camelToSnakeCase("focusDistributionGoods"));
        System.out.println(camelToSnakeCase("AlibabaGateway.focusDistributionGoods"));
    }


    /**
     * 将驼峰命名转换为下划线分隔的小写格式
     * 例如：focusDistributionGoods -> focus_distribution_goods
     * 例如：AlibabaGateway.focusDistributionGoods -> alibaba_gateway.focus_distribution_goods
     */
    private static String camelToSnakeCase(String camelCase) {
        if (StringUtils.isEmpty(camelCase)) {
            return camelCase;
        }

        return camelCase
                .replaceAll("([a-z])([A-Z])", "$1_$2")  // 在小写字母和大写字母之间插入下划线
                .toLowerCase();  // 转换为小写
    }
}

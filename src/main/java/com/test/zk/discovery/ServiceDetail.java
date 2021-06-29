package com.test.zk.discovery;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务的附加信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("details")
public class ServiceDetail {
    //服务注册的根路径
    public static final String REGISTER_ROOT_PATH = "/service";
    // 服务的描述
    private String desc;
    // 服务的权重
    private int weight;
}
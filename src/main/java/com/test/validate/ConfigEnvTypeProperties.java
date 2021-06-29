package com.test.validate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Validated
@Data
@ConfigurationProperties(prefix = "env")
public class ConfigEnvTypeProperties {

    @NotEmpty(message = "环境变量不能为空")
    private String type;

}

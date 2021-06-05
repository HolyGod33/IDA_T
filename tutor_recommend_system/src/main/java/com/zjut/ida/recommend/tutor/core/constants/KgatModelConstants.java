package com.zjut.ida.recommend.tutor.core.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wly
 * @date 2021/4/22 19:30
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "model")
public class KgatModelConstants {
    private String host;
    private String port;
    private String version;
    private String modelName;
    private String signatureName;

    public String getRequestUrl() {
        return String.format("http://%s:%s/%s/models/%s:predict", host, port, version, modelName);
    }
}

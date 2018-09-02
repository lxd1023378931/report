package com.uzak.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
@ConfigurationProperties(prefix = "zipkin")
@Data
public class ZipKinConfigure {
    private String host;
    private String serviceName;
    private float traceSampler;
    private boolean enable;
}

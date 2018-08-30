package com.uzak.data.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/8/30.
 */
@Configuration
@ConfigurationProperties(prefix = "grpc")
@Data
public class GrpcConfigure {
    private String defaultHost;
}

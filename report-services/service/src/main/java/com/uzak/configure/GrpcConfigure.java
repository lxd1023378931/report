package com.uzak.configure;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29.
 */
@Component
@ConfigurationProperties(prefix = "grpc")
@Data
public class GrpcConfigure {
    private int port;
    private List<String> service;
}

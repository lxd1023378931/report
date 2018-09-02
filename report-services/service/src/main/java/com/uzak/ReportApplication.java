package com.uzak;

import com.uzak.config.GrpcClient;
import com.uzak.config.ZipKinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2018/8/26.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties
@ComponentScan
public class ReportApplication {

    @Autowired
    private GrpcClient grpcClient;

    @Autowired
    private ZipKinClient zipKinClient;
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Bean
    public ReportApplication init() {
        grpcClient.start();
        zipKinClient.init();
        return this;
    }
}

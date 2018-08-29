package com.uzak;

import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2018/8/26.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Bean
    public ManagedChannel startGrpc(){
        ManagedChannel channel = NettyChannelBuilder.forAddress("127.0.0.1",8081).negotiationType(NegotiationType.PLAINTEXT).build();
        return channel;
    }
}

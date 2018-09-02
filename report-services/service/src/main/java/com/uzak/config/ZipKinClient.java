package com.uzak.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.grpc.BraveGrpcServerInterceptor;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.mysql.MySQLStatementInterceptorManagementBean;
import com.uzak.configure.ZipKinConfigure;
import com.uzak.util.StringUtil;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
public class ZipKinClient {
    private BraveGrpcServerInterceptor braveGrpcServerInterceptor = null;
    private Brave brave;
    @Autowired
    private ZipKinConfigure conf;

    public void init() {
        if (conf.isEnable()) {
            try {
                if (StringUtil.isBlank(conf.getServiceName())) {
                    throw new Exception("ServiceName not allowed to be empty");
                }
                if (StringUtil.isBlank(conf.getHost())) {
                    throw new Exception("Zipkin's url not allowed to be empty");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            braveGrpcServerInterceptor = new BraveGrpcServerInterceptor(brave());
        }
    }

    public ServerServiceDefinition getServerServiceDefinition(ServerServiceDefinition serviceDef) {
        if (braveGrpcServerInterceptor != null) {
            return ServerInterceptors.intercept(serviceDef, braveGrpcServerInterceptor);
        } else {
            return serviceDef;
        }
    }
    private Brave brave() {
        return new Brave.Builder(conf.getServiceName())
                .traceSampler(conf.getTraceSampler() == 0 ? Sampler.ALWAYS_SAMPLE : Sampler.create(conf.getTraceSampler()))
                .spanCollector(HttpSpanCollector.create(String.format("http://%s", conf.getHost()),
                        new EmptySpanCollectorMetricsHandler()))
                .build();
    }

    /**
     * 监听数据库连接
     * @return
     */
    @Bean
    public MySQLStatementInterceptorManagementBean mySQLStatementInterceptorManagementBean() {
        return new MySQLStatementInterceptorManagementBean(brave().clientTracer());
    }
}

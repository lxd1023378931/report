package com.uzak.service.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.grpc.BraveGrpcServerInterceptor;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.uzak.service.data.configure.ZipKinConfigure;
import com.uzak.service.util.StringUtil;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
public class ZipKinClient {
    private BraveGrpcServerInterceptor braveGrpcServerInterceptor = null;
    private Brave brave = null;
    private static ZipKinClient client = null;

    public void init(ZipKinConfigure conf) {
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
            brave = brave(conf);
            braveGrpcServerInterceptor = new BraveGrpcServerInterceptor(brave);
        }
    }

    public ServerServiceDefinition getServerServiceDefinition(ServerServiceDefinition serviceDef) {
        if (braveGrpcServerInterceptor != null) {
            return ServerInterceptors.intercept(serviceDef, braveGrpcServerInterceptor);
        } else {
            return serviceDef;
        }
    }

    public BraveGrpcServerInterceptor getBraveGrpcServerInterceptor() {
        return braveGrpcServerInterceptor;
    }

    private Brave brave(ZipKinConfigure conf) {
        return new Brave.Builder(conf.getServiceName())
                .traceSampler(conf.getTraceSampler() == 0 ? Sampler.ALWAYS_SAMPLE : Sampler.create(conf.getTraceSampler()))
                .spanCollector(HttpSpanCollector.create(String.format("http://%s", conf.getHost()),
                        new EmptySpanCollectorMetricsHandler()))
                .build();
    }
}

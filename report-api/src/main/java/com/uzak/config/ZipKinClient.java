package com.uzak.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.grpc.BraveGrpcClientInterceptor;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.uzak.data.configure.ZipKinConfigure;
import com.uzak.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/30.
 */
@Component
public class ZipKinClient {
    private static BraveGrpcClientInterceptor braveGrpcClientInterceptor = null;

    @Autowired
    ZipKinConfigure zipKinConfigure;

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
            braveGrpcClientInterceptor = new BraveGrpcClientInterceptor(brave(conf));
        }
    }

    public BraveGrpcClientInterceptor getBraveGrpcClientInterceptor() {
        if (braveGrpcClientInterceptor == null) {
            synchronized (ZipKinClient.class) {
                if (braveGrpcClientInterceptor == null) {
                    init(zipKinConfigure);
                }
            }
        }
        return braveGrpcClientInterceptor;
    }

    private Brave brave(ZipKinConfigure conf) {
        return new Brave.Builder(conf.getServiceName())
                .traceSampler(conf.getTraceSampler() == 0 ? Sampler.ALWAYS_SAMPLE : Sampler.create(conf.getTraceSampler()))
                .spanCollector(HttpSpanCollector.create(String.format("http://%s", conf.getHost()),
                        new EmptySpanCollectorMetricsHandler()))
                .build();
    }
}

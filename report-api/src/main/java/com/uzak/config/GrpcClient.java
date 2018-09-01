package com.uzak.config;

import com.uzak.data.configure.GrpcConfigure;
import com.uzak.data.configure.ZipKinConfigure;
import com.uzak.util.StringUtil;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.AbstractStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/8/30.
 */
@Service
public class GrpcClient {
    private ConcurrentHashMap<String, ManagedChannel> channels;
    @Autowired
    private GrpcConfigure grpcConfigure;

    @Autowired
    private ZipKinClient client;

    public GrpcClient() {
        if (channels == null) {
            channels = new ConcurrentHashMap<>();
        }
    }

    public AbstractStub getFutureStub(Class clazz) {
        return getFutureStub(clazz, grpcConfigure.getDefaultHost());
    }

    public AbstractStub getFutureStub(Class clazz, String grpcHost) {
        AbstractStub futureStub = null;
        try {
            futureStub = (AbstractStub) clazz.getDeclaredMethod("newFutureStub", Channel.class).invoke(null, getGrpcChannel(grpcHost));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return futureStub;
    }

    private ManagedChannel getGrpcChannel(String host) {
        if (StringUtil.isBlank(host)) {
            try {
                throw new Exception("[grpc: host] is not allowed to be empty !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (channels.contains(host)) {
            return channels.get(host);
        } else {
            synchronized (GrpcClient.class) {
                if (!channels.contains(host)) {
                    ManagedChannel channel = NettyChannelBuilder.forTarget(host).intercept(client.getBraveGrpcClientInterceptor()).negotiationType(NegotiationType.PLAINTEXT).build();
                    channels.put(host, channel);
                }
            }
        }
        return channels.get(host);
    }

}

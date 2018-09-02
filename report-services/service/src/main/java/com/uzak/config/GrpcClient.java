package com.uzak.config;

import com.uzak.configure.GrpcConfigure;
import com.uzak.impl.TestServiceImpl;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/8/27.
 */
@Component
public class GrpcClient {
    private Server server;

    @Autowired
    private GrpcConfigure grpcConfigure;
    @Autowired
    private ZipKinClient zipKinClient;

    @Autowired
    TestServiceImpl testService;

    public void start() {
        try {
            synchronized (GrpcClient.class) {
                NettyServerBuilder serverBuilder = NettyServerBuilder.forPort(grpcConfigure.getPort());
                addService(serverBuilder);
                server = serverBuilder.build().start();
                System.out.println("Server started, listening on " + grpcConfigure.getPort());
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        GrpcClient.this.stop();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册GRPC服务
     *
     * @param serverBuilder
     */
    private void addService(NettyServerBuilder serverBuilder) {
        synchronized (GrpcClient.class) {
            try {
                serverBuilder.addService(zipKinClient.getServerServiceDefinition(testService.bindService()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
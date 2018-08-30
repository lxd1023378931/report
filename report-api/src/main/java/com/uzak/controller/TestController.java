package com.uzak.controller;

import com.uzak.config.GrpcClient;
import com.uzak.inter.bean.test.TestRequest;
import com.uzak.inter.service.test.TestBeanServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/26.
 */
@RequestMapping("/test")
@RestController
public class TestController {

    ManagedChannel channel;
    @Autowired
    private GrpcClient client;

    private TestBeanServiceGrpc.TestBeanServiceFutureStub testBeanServiceFutureStub;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public Object test1() {
        TestRequest.TestBeanRequest.Builder builder = TestRequest.TestBeanRequest.newBuilder();
        builder.setUrl("/test/test1");
        builder.setTime("12324");
        TestRequest.TestBeanResponse response = null;
        testBeanServiceFutureStub = (TestBeanServiceGrpc.TestBeanServiceFutureStub) client.getFutureStub(TestBeanServiceGrpc.class);
        try {
            response = testBeanServiceFutureStub.getTest(builder.build()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null) {
            return "test1";
        } else {
            return response.getRequest().getUrl() + "-" + response.getRequest().getTime();
        }
    }
}

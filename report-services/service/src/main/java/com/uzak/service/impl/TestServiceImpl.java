package com.uzak.service.impl;

import com.uzak.inter.bean.test.TestRequest;
import com.uzak.inter.service.test.TestBeanServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/26.
 */
public class TestServiceImpl extends TestBeanServiceGrpc.TestBeanServiceImplBase {

    @Override
    public void getTest(TestRequest.TestBeanRequest request, StreamObserver<TestRequest.TestBeanResponse> responseObserver) {
        System.out.println(request.getUrl() + ":" + request.getTime());
        TestRequest.TestBeanResponse.Builder response = TestRequest.TestBeanResponse.newBuilder();
        response.setRequest(request);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        return;
    }
}

package com.uzak.inter.service.test;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: TestService.proto")
public class TestBeanServiceGrpc {

  private TestBeanServiceGrpc() {}

  public static final String SERVICE_NAME = "TestBeanService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.uzak.inter.bean.test.TestRequest.TestBeanRequest,
      com.uzak.inter.bean.test.TestRequest.TestBeanResponse> METHOD_GET_TEST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "TestBeanService", "getTest"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.uzak.inter.bean.test.TestRequest.TestBeanRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.uzak.inter.bean.test.TestRequest.TestBeanResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TestBeanServiceStub newStub(io.grpc.Channel channel) {
    return new TestBeanServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TestBeanServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TestBeanServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static TestBeanServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TestBeanServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TestBeanServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getTest(com.uzak.inter.bean.test.TestRequest.TestBeanRequest request,
        io.grpc.stub.StreamObserver<com.uzak.inter.bean.test.TestRequest.TestBeanResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TEST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TEST,
            asyncUnaryCall(
              new MethodHandlers<
                com.uzak.inter.bean.test.TestRequest.TestBeanRequest,
                com.uzak.inter.bean.test.TestRequest.TestBeanResponse>(
                  this, METHODID_GET_TEST)))
          .build();
    }
  }

  /**
   */
  public static final class TestBeanServiceStub extends io.grpc.stub.AbstractStub<TestBeanServiceStub> {
    private TestBeanServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestBeanServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestBeanServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestBeanServiceStub(channel, callOptions);
    }

    /**
     */
    public void getTest(com.uzak.inter.bean.test.TestRequest.TestBeanRequest request,
        io.grpc.stub.StreamObserver<com.uzak.inter.bean.test.TestRequest.TestBeanResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TEST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TestBeanServiceBlockingStub extends io.grpc.stub.AbstractStub<TestBeanServiceBlockingStub> {
    private TestBeanServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestBeanServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestBeanServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestBeanServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.uzak.inter.bean.test.TestRequest.TestBeanResponse getTest(com.uzak.inter.bean.test.TestRequest.TestBeanRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TEST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TestBeanServiceFutureStub extends io.grpc.stub.AbstractStub<TestBeanServiceFutureStub> {
    private TestBeanServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestBeanServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestBeanServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestBeanServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.uzak.inter.bean.test.TestRequest.TestBeanResponse> getTest(
        com.uzak.inter.bean.test.TestRequest.TestBeanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TEST, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TEST = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TestBeanServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(TestBeanServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TEST:
          serviceImpl.getTest((com.uzak.inter.bean.test.TestRequest.TestBeanRequest) request,
              (io.grpc.stub.StreamObserver<com.uzak.inter.bean.test.TestRequest.TestBeanResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_GET_TEST);
  }

}

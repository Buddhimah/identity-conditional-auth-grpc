package org.wso2.carbon.identity.conditional.auth.functions.grpc.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Service.proto")
public final class GrpcServiceGrpc {

  private GrpcServiceGrpc() {}

  public static final String SERVICE_NAME = "GrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest,
      org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> getGrpcInvokeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grpcInvoke",
      requestType = org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest.class,
      responseType = org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest,
      org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> getGrpcInvokeMethod() {
    io.grpc.MethodDescriptor<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest, org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> getGrpcInvokeMethod;
    if ((getGrpcInvokeMethod = GrpcServiceGrpc.getGrpcInvokeMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getGrpcInvokeMethod = GrpcServiceGrpc.getGrpcInvokeMethod) == null) {
          GrpcServiceGrpc.getGrpcInvokeMethod = getGrpcInvokeMethod = 
              io.grpc.MethodDescriptor.<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest, org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrpcService", "grpcInvoke"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("grpcInvoke"))
                  .build();
          }
        }
     }
     return getGrpcInvokeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcServiceStub newStub(io.grpc.Channel channel) {
    return new GrpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GrpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void grpcInvoke(org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest request,
        io.grpc.stub.StreamObserver<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGrpcInvokeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGrpcInvokeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest,
                org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse>(
                  this, METHODID_GRPC_INVOKE)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcServiceStub extends io.grpc.stub.AbstractStub<GrpcServiceStub> {
    private GrpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void grpcInvoke(org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest request,
        io.grpc.stub.StreamObserver<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrpcInvokeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcServiceBlockingStub extends io.grpc.stub.AbstractStub<GrpcServiceBlockingStub> {
    private GrpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse grpcInvoke(org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest request) {
      return blockingUnaryCall(
          getChannel(), getGrpcInvokeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcServiceFutureStub extends io.grpc.stub.AbstractStub<GrpcServiceFutureStub> {
    private GrpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse> grpcInvoke(
        org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGrpcInvokeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GRPC_INVOKE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GRPC_INVOKE:
          serviceImpl.grpcInvoke((org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonRequest) request,
              (io.grpc.stub.StreamObserver<org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.JsonResponse>) responseObserver);
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

  private static abstract class GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.wso2.carbon.identity.conditional.auth.functions.grpc.service.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcService");
    }
  }

  private static final class GrpcServiceFileDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier {
    GrpcServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcServiceMethodDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcServiceFileDescriptorSupplier())
              .addMethod(getGrpcInvokeMethod())
              .build();
        }
      }
    }
    return result;
  }
}

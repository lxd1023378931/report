// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TestService.proto

package com.uzak.inter.service.test;

public final class TestService {
  private TestService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021TestService.proto\032\021TestRequest.proto2A" +
      "\n\017TestBeanService\022.\n\007getTest\022\020.TestBeanR" +
      "equest\032\021.TestBeanResponseB\035\n\033com.uzak.in" +
      "ter.service.testb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.uzak.inter.bean.test.TestRequest.getDescriptor(),
        }, assigner);
    com.uzak.inter.bean.test.TestRequest.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}

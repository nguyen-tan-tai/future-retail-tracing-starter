package com.futureretail.trace.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@AutoConfiguration
@ConditionalOnClass(name = "io.grpc.Server")
public class FutureRetailGrpcTracingAutoConfiguration {
}

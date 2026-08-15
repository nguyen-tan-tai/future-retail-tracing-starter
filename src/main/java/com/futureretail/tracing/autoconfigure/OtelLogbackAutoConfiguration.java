package com.futureretail.tracing.autoconfigure;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class OtelLogbackAutoConfiguration {

    @Bean
    ApplicationRunner otelLogbackInitializer(OpenTelemetry openTelemetry) {
        return args -> OpenTelemetryAppender.install(openTelemetry);
    }
}

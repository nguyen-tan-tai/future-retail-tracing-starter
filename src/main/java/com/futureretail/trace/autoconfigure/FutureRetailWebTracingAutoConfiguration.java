package com.futureretail.trace.autoconfigure;

import com.futureretail.trace.config.TraceProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(TraceProperties.class)
public class FutureRetailWebTracingAutoConfiguration {
}

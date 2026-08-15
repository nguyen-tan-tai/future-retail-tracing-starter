package com.futureretail.trace.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class FutureRetailTracingAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(FutureRetailTracingAutoConfigurationTest.class))
            .withPropertyValues("spring.application.name=test-app");

    @Test
    void autoConfigurationLoads() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(FutureRetailTracingAutoConfigurationTest.class);
        });
    }
}

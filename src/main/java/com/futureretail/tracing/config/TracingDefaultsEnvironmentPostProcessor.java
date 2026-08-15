package com.futureretail.tracing.config;

import java.util.Properties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

public class TracingDefaultsEnvironmentPostProcessor implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String PROPERTY_SOURCE_NAME = "tracingStarterYamlProperties";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        if (!StringUtils.hasText(appName) || "bootstrap".equals(appName)) {
            throw new IllegalArgumentException("""
                    ====================================================================================
                     APPLICATION LAUNCH FAILED: Missing Required Configuration
                    ====================================================================================
                     Downstream applications utilizing this starter MUST explicitly provide a name.
                     Please set 'spring.application.name' in your application.yml or application.properties.
                    ====================================================================================
                    """);
        }
        MutablePropertySources propertySources = environment.getPropertySources();
        Resource resource = new ClassPathResource("application-tracing.yaml");
        if (!resource.exists()) {
            return; // Soft-fail or log warning so downstream app can still boot without it
        }
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            Properties properties = yamlFactory.getObject();
            if (properties != null) {
                PropertiesPropertySource propertySource = new PropertiesPropertySource(PROPERTY_SOURCE_NAME, properties);
                propertySources.addLast(propertySource);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse starter fallback configurations", e);
        }
    }
}

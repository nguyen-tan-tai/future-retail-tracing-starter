package com.futureretail.trace.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "futureretail.tracing")
public class TraceProperties {

    private boolean enabled = true;
    private double samplingProbability = 1.0;
    private boolean baggageEnabled = true;
    @Value("${spring.application.name:unknown}")
    private String serviceName;

    @PostConstruct
    public void validateServiceName() {
        if ("unknown".equals(serviceName) || serviceName == null || serviceName.trim().isEmpty()) {
            throw new IllegalStateException("spring.application.name must be configured. " + "Please add 'spring.application.name: <your-service-name>' to application.yml");
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getSamplingProbability() {
        return samplingProbability;
    }

    public void setSamplingProbability(double samplingProbability) {
        this.samplingProbability = samplingProbability;
    }

    public boolean isBaggageEnabled() {
        return baggageEnabled;
    }

    public void setBaggageEnabled(boolean baggageEnabled) {
        this.baggageEnabled = baggageEnabled;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

package com.futureretail.trace.context;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.MDC;

public class TraceContextManager {

    private static final String TRACE_ID_KEY = "traceId";
    private static final String SPAN_ID_KEY = "spanId";
    private static final String TRACER_NAME = "futureretail-tracer";

    private TraceContextManager() {
    }

    public static Tracer getTracer() {
        return GlobalOpenTelemetry.getTracer(TRACER_NAME);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    public static String getSpanId() {
        return MDC.get(SPAN_ID_KEY);
    }

    public static boolean hasTraceId() {
        return MDC.get(TRACE_ID_KEY) != null;
    }

    public static void setTraceId(String traceId) {
        if (traceId != null) {
            MDC.put(TRACE_ID_KEY, traceId);
        }
    }

    public static void setSpanId(String spanId) {
        if (spanId != null) {
            MDC.put(SPAN_ID_KEY, spanId);
        }
    }

    public static void addAttribute(String key, String value) {
        Span span = Span.current();
        if (span != null && span.isRecording()) {
            span.setAttribute(key, value);
        }
    }

    public static void addAttribute(String key, long value) {
        Span span = Span.current();
        if (span != null && span.isRecording()) {
            span.setAttribute(key, value);
        }
    }

    public static void addAttribute(String key, boolean value) {
        Span span = Span.current();
        if (span != null && span.isRecording()) {
            span.setAttribute(key, value);
        }
    }

    public static Span createSpan(String spanName) {
        return getTracer().spanBuilder(spanName).startSpan();
    }

    public static void recordEvent(String eventName, String... attributes) {
        Span span = Span.current();
        if (span != null && span.isRecording()) {
            span.addEvent(eventName);
        }
    }

    public static void clearContext() {
        MDC.remove(TRACE_ID_KEY);
        MDC.remove(SPAN_ID_KEY);
    }
}

package com.msa.global.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {

    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(4)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(2)
            .build();

    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(4))
            .build();

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> customConfiguration() {
        return resilience4JCircuitBreakerFactory ->
                resilience4JCircuitBreakerFactory.configureDefault(s ->
                        new Resilience4JConfigBuilder(s)
                        .timeLimiterConfig(timeLimiterConfig)
                        .circuitBreakerConfig(circuitBreakerConfig)
                        .build()
                );
    }
}

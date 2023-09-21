package com.msa.test.service;

import com.msa.global.fegin.LmsFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ConfigBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LmsFeignClient lmsFeignClient;
    private final CircuitBreakerFactory<Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuitBreakerFactory;

    @Override
    public void test() {
        log.info("Start call lmsapi health check");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuit breaker");
        String message = circuitBreaker.run(lmsFeignClient::healthCheck, throwable -> "lmsapi health Check Fail");
        log.info(message);
        log.info("End call lmsapi health check");
    }

    @Override
    public void test404() {
        log.info(lmsFeignClient.healthCheck404());
    }
}

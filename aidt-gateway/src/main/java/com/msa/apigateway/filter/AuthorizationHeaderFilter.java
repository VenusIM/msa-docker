package com.msa.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    // https://github.com/artemMartynenko/spring-cloud-gateway-oauth2-sso-sample-application/commits?author=artemMartynenko
    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {
        // application.yml 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 인증이 되어있지 않다면
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }

            List<String> authorizationHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
            String authorizationHeader = "";
            if(authorizationHeaders != null) {
                authorizationHeader = authorizationHeaders.get(0);
            }
            String bearer = authorizationHeader.replace("Bearer", "");

            if(!isValid(bearer)) {
                return onError(exchange, "bearer is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isValid(String bearer) {
        return StringUtils.isNotEmpty(bearer);
    }

    // Spring Webflux -> Mono, Flux
    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(error);
        return response.setComplete();
    }
}
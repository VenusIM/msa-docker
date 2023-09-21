package com.msa.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AiDtEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiDtEurekaApplication.class, args);
    }

}

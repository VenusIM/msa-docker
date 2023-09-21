package com.msa.test.controller;

import com.msa.test.service.TestService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vcloudapi")
@RequiredArgsConstructor
public class TestController {

    private final Environment environment;
    private final TestService testService;

    @GetMapping("/health_check")
    public String status() {
        return "It's working in aidt-vcloudapi test controller " +
                "\n LOCAL SEVER PORT >> " + environment.getProperty("local.server.port") +
                "\n SEVER PORT >> " + environment.getProperty("server.port");
    }

    @GetMapping("/test")
    public void test() {
        testService.test();
    }

    @GetMapping("/errorTest")
    public void test404() {
        testService.test404();
    }
}

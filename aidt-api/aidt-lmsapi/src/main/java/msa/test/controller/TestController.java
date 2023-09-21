package msa.test.controller;

import lombok.RequiredArgsConstructor;
import msa.test.service.TestService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lmsapi")
@RequiredArgsConstructor
public class TestController {

    private final Environment environment;
    private final TestService testService;

    @GetMapping("/health_check")
    public String status() {
        return "It's working in aidt-lmsapi test controller " +
                "\n LOCAL SEVER PORT >> " + environment.getProperty("local.server.port") +
                "\n SEVER PORT >> " + environment.getProperty("server.port");
    }

    @GetMapping("/test")
    public void test() {
        testService.test();
    }
}

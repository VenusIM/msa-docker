package com.msa.global.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aidt-lmsapi")
public interface LmsFeignClient {

    @GetMapping("/lmsapi/health_check")
    String healthCheck();

    @GetMapping("/lmsapi/health_check404")
    String healthCheck404();
}

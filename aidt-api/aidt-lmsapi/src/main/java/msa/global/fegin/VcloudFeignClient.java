package msa.global.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "aidt-vcloudapi")
public interface VcloudFeignClient {

    @GetMapping("/vcloudapi/health_check")
    String healthCheck();
}

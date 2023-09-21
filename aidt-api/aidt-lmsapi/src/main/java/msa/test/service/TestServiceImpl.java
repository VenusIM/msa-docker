package msa.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.global.fegin.VcloudFeignClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final VcloudFeignClient vcloudFeignClient;

    @Override
    public void test() {
        log.info(vcloudFeignClient.healthCheck());
    }
}

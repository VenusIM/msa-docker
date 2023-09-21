package com.msa.global.fegin;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("{} 요청이 성공하지 못했습니다. status : {}, body : {}", methodKey, response.status(), response.body());

        //Exception Handling 추가
        return new Exception();
    }
}
package com.swiggy.FeignClientMicroService.config;

import com.swiggy.FeignClientMicroService.errorDecoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }


}

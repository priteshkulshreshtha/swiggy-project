package com.swiggy.FeignClientMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientMicroServiceApplication.class, args);
	}

}

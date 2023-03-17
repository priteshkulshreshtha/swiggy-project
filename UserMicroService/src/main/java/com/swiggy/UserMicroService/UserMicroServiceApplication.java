package com.swiggy.UserMicroService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.UserMicroService.entities.UserCart;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.repository.UserCartRepository;
import com.swiggy.UserMicroService.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.PublicKey;

@SpringBootApplication
public class UserMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroServiceApplication.class, args);
	}

	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public ObjectMapper getObjectMapper() { return new ObjectMapper();}
}

package com.swiggy.UserMicroService.util;

import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.exception.BadResponseDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtils {

    @Autowired
    WebClient.Builder webClient;

    public <T> T postRequest(String uri, Class<T> t, Object body) {
        return webClient
                .filter(WebClientUtils.errorHandlingFilter())
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(t)
                .block();
    };

    public <T> T getRequest(String uri, Class<T> t) {
        return webClient
                .filter(WebClientUtils.errorHandlingFilter())
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(t)
                .block();
    };

    public static ExchangeFilterFunction errorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if(clientResponse.statusCode()!=null && (clientResponse.statusCode().is5xxServerError() || clientResponse.statusCode().is4xxClientError()) ) {
                return clientResponse.bodyToMono(ResponseWrapper.class)
                        .flatMap(errorBody -> {
                            return Mono.error(new BadResponseDataException(errorBody.getMessage()));
                        });
            }else {
                return Mono.just(clientResponse);
            }
        });
    }
}

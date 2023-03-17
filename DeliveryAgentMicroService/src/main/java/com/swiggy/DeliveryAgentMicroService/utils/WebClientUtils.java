package com.swiggy.DeliveryAgentMicroService.utils;

import com.swiggy.DeliveryAgentMicroService.beans.response.ResponseWrapper;
import com.swiggy.DeliveryAgentMicroService.exception.BadResponseDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUtils {

    @Autowired
    WebClient.Builder webClient;

    public <T> T postRequest(String uri, Class<T> t) {
        return webClient
                .filter(WebClientUtils.errorHandlingFilter())
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(t)
                .block();
    }

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

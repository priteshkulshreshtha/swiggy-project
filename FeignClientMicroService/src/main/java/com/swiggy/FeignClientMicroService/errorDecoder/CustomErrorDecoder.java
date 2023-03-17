package com.swiggy.FeignClientMicroService.errorDecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.FeignClientMicroService.beans.response.ResponseWrapper;
import com.swiggy.FeignClientMicroService.exceptions.BadResponseDataException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ResponseWrapper responseWrapper;
        try {
            byte[] stream = response.body().asInputStream().readAllBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            responseWrapper = objectMapper.readValue(stream, ResponseWrapper.class);
        } catch (Exception e) {
            return new BadResponseDataException("Could not read the response body");
        }
        return new BadResponseDataException(responseWrapper.getMessage());

    }
}

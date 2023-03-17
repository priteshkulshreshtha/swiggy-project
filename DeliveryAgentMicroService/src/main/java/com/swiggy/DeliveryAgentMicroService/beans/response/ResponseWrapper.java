package com.swiggy.DeliveryAgentMicroService.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper {
    private boolean status;
    private Object data;
    private String message;
    private Object error;

    public ResponseEntity<ResponseWrapper> getResponse(Object data, String message, HttpStatus status) {
        this.status = true;
        this.data = data;
        this.message = message;
        return new ResponseEntity<>(this, status);
    }

    public ResponseEntity<ResponseWrapper> getErrorResponse(Object errorBody, String message, HttpStatus status) {
        this.status = false;
        this.error = errorBody;
        this.message = message;
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON_UTF8).body(this);
    };
}

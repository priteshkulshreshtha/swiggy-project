package com.swiggy.FeignClientMicroService.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper implements Serializable {
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

    public ResponseEntity<ResponseWrapper> getErrorResponse(ExceptionResponseBody errorBody, String message, HttpStatus status) {
        this.status = false;
        this.error = errorBody;
        this.message = message;
        return new ResponseEntity<>(this, status);
    };


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}

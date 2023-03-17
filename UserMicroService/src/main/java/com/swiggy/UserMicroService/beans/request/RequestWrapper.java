package com.swiggy.UserMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestWrapper {
    private boolean status;
    private Object data;
    private String message;
    private Object error;

}

package com.swiggy.DeliveryAgentMicroService.beans.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestBody {
    private long id;
    private String  location;
}

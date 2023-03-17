package com.swiggy.OrderMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgentRequest {
    private long id;
    private String location;
}

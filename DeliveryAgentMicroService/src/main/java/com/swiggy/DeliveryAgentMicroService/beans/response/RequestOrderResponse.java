package com.swiggy.DeliveryAgentMicroService.beans.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderResponse {
    private long id;
    private long restaurantId;
    private String timeOfOrder;
    private long customerId;
    private String customerLocation;

}

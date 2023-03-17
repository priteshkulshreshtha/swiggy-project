package com.swiggy.OrderMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private long foodId;
    private long foodPrice;
    private long quantity;
}

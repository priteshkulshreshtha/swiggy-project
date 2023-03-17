package com.swiggy.FeignClientMicroService.beans.request.userClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private long userId;
    private long foodId;
}

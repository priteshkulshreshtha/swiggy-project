package com.swiggy.UserMicroService.beans.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private long userId;
    private long foodId;
    private List<Long> customizationFieldIds;
}

package com.swiggy.UserMicroService.controller;

import com.swiggy.UserMicroService.beans.request.CartItemRequest;
import com.swiggy.UserMicroService.beans.response.CartItemResponse;
import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.services.UserCartService;
import com.swiggy.UserMicroService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserCartController {

    @Autowired
    UserCartService userCartService;
    @Autowired
    UserService userService;

    @PostMapping("/user/{userId}/clear_cart")
    public ResponseEntity<ResponseWrapper> clearCartController(@PathVariable long userId) {
        userCartService.clearCartService(userId);
        return new ResponseWrapper().getResponse(null, "Cart is cleared", HttpStatus.OK);
    }

    ;

    @PostMapping("/cart/add_item")
    public ResponseEntity<ResponseWrapper> addFoodItemController(@RequestBody CartItemRequest request) {
        UserProfile userProfile = userService.getUserById(request.getUserId());


        userCartService.addFoodItemService(
                userProfile,
                request.getFoodId(),
                request.getCustomizationFieldIds()
        );

        return new ResponseWrapper().getResponse(null, "Item successfully added to cart", HttpStatus.OK);
    }

    @GetMapping("/cart_items/{userId}")
    public ResponseEntity<ResponseWrapper> getCartOrderItemsController(@PathVariable long userId) {
        List<CartItemResponse> cartItems = new ArrayList<>();
        userCartService.getCartOrderItems(userId).stream().forEach(cartItem -> {
            cartItems.add(new CartItemResponse(cartItem));
        });


        return new ResponseWrapper().getResponse(cartItems,
                "Successfully fetched cart items for " + userId,
                HttpStatus.OK
        );
    }

    @PostMapping("/cart/remove_item")
    public ResponseEntity<ResponseWrapper> removeFoodItemController(@RequestBody CartItemRequest request) {
        UserProfile userProfile = userService.getUserById(request.getUserId());
        userCartService.removeFoodItemService(
                userProfile,
                request.getFoodId()
        );
        return new ResponseWrapper().getResponse(null, "Food Item Deleted", HttpStatus.OK);
    }

    @PostMapping("/place_order/{userId}")
    public ResponseEntity<ResponseWrapper> placeOrder(@PathVariable long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        Map<String, Long> response = userCartService.placeOrder(userProfile);
        return new ResponseWrapper().getResponse(response, "Order sent.", HttpStatus.OK);

    }

}

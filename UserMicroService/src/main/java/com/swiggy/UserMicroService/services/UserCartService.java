package com.swiggy.UserMicroService.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.UserMicroService.beans.request.FoodItemRequest;
import com.swiggy.UserMicroService.beans.response.FoodItemResponse;
import com.swiggy.UserMicroService.beans.response.PlaceOrderBody;
import com.swiggy.UserMicroService.beans.response.ResponseWrapper;
import com.swiggy.UserMicroService.entities.CartItem;
import com.swiggy.UserMicroService.entities.UserCart;
import com.swiggy.UserMicroService.entities.UserProfile;
import com.swiggy.UserMicroService.exception.ResourceNotFoundException;
import com.swiggy.UserMicroService.exception.RestaurantMismatchException;
import com.swiggy.UserMicroService.repository.CartItemRepository;
import com.swiggy.UserMicroService.repository.CustomizationFieldRepository;
import com.swiggy.UserMicroService.repository.UserCartRepository;
import com.swiggy.UserMicroService.util.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserCartService {

    @Autowired
    private UserCartRepository userCartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    WebClientUtils webClientUtils;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    CustomizationFieldRepository fieldRepository;


    public void clearCartService(long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        UserCart userCart = userProfile.getUserCart();
        cartItemRepository.deleteAllByUserCart(userCart);
    }

    public void addFoodItemService(UserProfile userProfile, long foodId, List<Long> fieldIds) {


        long userCartRestaurantId = userProfile.getUserCart().getRestaurantId();

        UserCart userCart = userProfile.getUserCart();

        FoodItemResponse foodItem = this.getFoodItemById(foodId);

        if (userCartRestaurantId != 0 && userCartRestaurantId != foodItem.getRestaurantId())
            throw new RestaurantMismatchException("Food items from multiple restaurants cannot be selected");

        if (userCartRestaurantId == 0) {
            userCart.setRestaurantId(foodItem.getRestaurantId());
        }

        this.addToOrderItems(foodItem, userCart, fieldIds);
        userCartRepository.save(userCart);

    }

    public List<CartItem> getCartOrderItems(long userId) {
        UserProfile userProfile = userService.getUserById(userId);
        return userProfile.getUserCart().getOrderItemList();
    }

    public void removeFoodItemService(UserProfile userProfile, long foodId) {
        UserCart userCart = userProfile.getUserCart();

        // TODO: change this

//        this.removeFoodItem(this.getFoodItemById(foodId), userCart);
        userCartRepository.save(userCart);
    }

    public FoodItemResponse getFoodItemById(long foodId) {

        ResponseWrapper response = webClientUtils.getRequest(
                "http://localhost:8200/food_item/" + foodId,
                ResponseWrapper.class
        );

        return objectMapper.convertValue(response.getData(), FoodItemResponse.class);

    }


    public void addToOrderItems(FoodItemRequest foodItem, UserCart userCart, List<Long> fieldIds) {

        Optional<CartItem> cartItem = fieldRepository.findFirstByFieldIdAndUserCart()

        CartItem newCartItem;

        if (cartItem.isEmpty()) {
            newCartItem = new CartItem(foodItem);
            newCartItem.setUserCart(userCart);
        }
        else {
            newCartItem = cartItem.get();
            newCartItem.setQuantity(cartItem.get().getQuantity() + 1);
            newCartItem.setBasePrice(userCart.getBillAmount()+ foodItem.getPrice());
            userCartRepository.save(userCart);
            cartItemRepository.save(cartItem.get());
        }

        userCart.setBillAmount(userCart.getBillAmount()+ foodItem.getPrice());
        userCartRepository.save(userCart);
        cartItemRepository.save(newCartItem);
    }

    public void removeFoodItem(FoodItemRequest foodItem, UserCart userCart) {
        Optional<CartItem> cartItem = cartItemRepository.findFirstByFoodIdAndUserCart(foodItem.getId(), userCart);

        if (cartItem.isEmpty())
            throw new ResourceNotFoundException("Food item is not present in cart");

        if (cartItem.get().getQuantity() > 1) {
            cartItem.get().setQuantity(cartItem.get().getQuantity() - 1);
            cartItemRepository.save(cartItem.get());
            return;
        }
        else {
            cartItemRepository.deleteById(cartItem.get().getCartItemId());
        }
    }

    public Map<String, Long> placeOrder(UserProfile userProfile) {
        PlaceOrderBody order = new PlaceOrderBody();
        order.setCartItems(getCartOrderItems(userProfile.getId()));
        order.setCustomerId(userProfile.getId());
        order.setRestaurantId(userProfile.getUserCart().getRestaurantId());
        order.setTimeOfOrder(LocalDateTime.now());
        order.setCustomerLocation(userProfile.getAddress());
        order.setBillAmount(userProfile.getUserCart().getBillAmount());

        ResponseWrapper response = webClientUtils.postRequest(
                "http://localhost:8300/create_order",
                ResponseWrapper.class,
                order
        );

        return objectMapper.convertValue(response.getData(),  new TypeReference<Map<String, Long>>() {});

    }
}



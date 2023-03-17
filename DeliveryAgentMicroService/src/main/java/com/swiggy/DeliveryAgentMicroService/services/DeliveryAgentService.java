package com.swiggy.DeliveryAgentMicroService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.DeliveryAgentMicroService.beans.request.DeliveryAgentRequest;
import com.swiggy.DeliveryAgentMicroService.beans.request.NewDeliveryAgentRequest;
import com.swiggy.DeliveryAgentMicroService.beans.request.OrderRequestBody;
import com.swiggy.DeliveryAgentMicroService.beans.response.RequestOrderResponse;
import com.swiggy.DeliveryAgentMicroService.beans.response.ResponseWrapper;
import com.swiggy.DeliveryAgentMicroService.entity.DeliveryAgent;
import com.swiggy.DeliveryAgentMicroService.exception.DeliveryAlreadyInProgress;
import com.swiggy.DeliveryAgentMicroService.exception.NoOrdersAvailableException;
import com.swiggy.DeliveryAgentMicroService.exception.ResourceAlreadyPresentException;
import com.swiggy.DeliveryAgentMicroService.exception.ResourceNotFoundException;
import com.swiggy.DeliveryAgentMicroService.respository.DeliveryAgentRepository;
import com.swiggy.DeliveryAgentMicroService.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.Optional;

@Service
public class DeliveryAgentService {

    @Autowired
    DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    WebClient.Builder webClient;

    @Autowired
    WebClientUtils webClientUtils;

    @Autowired
    ObjectMapper objectMapper;

    public void createNewAgent(NewDeliveryAgentRequest newAgentDetails) {
        if (deliveryAgentRepository.findFirstByContactNumber(newAgentDetails.getContactNumber()).isPresent())
            throw new ResourceAlreadyPresentException("This contact number is already registered.");
        if (deliveryAgentRepository.findFirstByBikeNumber(newAgentDetails.getBikeNumber()).isPresent())
            throw new ResourceAlreadyPresentException("This bike number is already registered.");
        deliveryAgentRepository.save(new DeliveryAgent(newAgentDetails));
    }

    public DeliveryAgent getAgentById(long agentId) {
        Optional<DeliveryAgent> agent = deliveryAgentRepository.findById(agentId);
        if (agent.isEmpty()) throw new ResourceNotFoundException("Agent does not exist");
        return agent.get();
    }

    public void updateAgentDetails(DeliveryAgentRequest newAgentDetails) {
        DeliveryAgent agent = getAgentById(newAgentDetails.getId());
        agent.setName(newAgentDetails.getName());
        agent.setAddress(newAgentDetails.getAddress());
        agent.setBikeNumber(newAgentDetails.getBikeNumber());
        deliveryAgentRepository.save(agent);
    }

    public RequestOrderResponse requestDelivery(DeliveryAgent agent) {

        if (!Objects.isNull(agent.getCurrentOrderId())) {
            throw new DeliveryAlreadyInProgress("Complete the previous delivery first.");
        }

        OrderRequestBody request = new OrderRequestBody(agent.getId(), agent.getAddress());

        ResponseWrapper response = webClientUtils.postRequest(
                "http://localhost:8300/assign_delivery",
                ResponseWrapper.class
        );

        if (Objects.isNull(response)) {
            throw new NoOrdersAvailableException("No orders available");
        }

        RequestOrderResponse deliveryDetails = objectMapper.convertValue(response.getData(), RequestOrderResponse.class);
        agent.setCurrentOrderId(deliveryDetails.getId());
        deliveryAgentRepository.save(agent);

        return deliveryDetails;
    }

    public void orderDelivered(DeliveryAgent agent) {
        if (Objects.isNull(agent.getCurrentOrderId())) {
            throw new ResourceNotFoundException("No orders are assigned");
        }

        ResponseWrapper response = webClientUtils.postRequest(
                "http://localhost:8300/order_delivered/" + agent.getCurrentOrderId(),
                ResponseWrapper.class
        );

        agent.setCurrentOrderId(null);
        deliveryAgentRepository.save(agent);

    }
}

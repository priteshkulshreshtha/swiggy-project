package com.swiggy.DeliveryAgentMicroService.controller;

import com.swiggy.DeliveryAgentMicroService.beans.request.DeliveryAgentRequest;
import com.swiggy.DeliveryAgentMicroService.beans.request.NewDeliveryAgentRequest;
import com.swiggy.DeliveryAgentMicroService.beans.response.DeliveryAgentResponse;
import com.swiggy.DeliveryAgentMicroService.beans.response.ExceptionResponseBody;
import com.swiggy.DeliveryAgentMicroService.beans.response.RequestOrderResponse;
import com.swiggy.DeliveryAgentMicroService.beans.response.ResponseWrapper;
import com.swiggy.DeliveryAgentMicroService.entity.DeliveryAgent;
import com.swiggy.DeliveryAgentMicroService.services.DeliveryAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DeliveryAgentController {


    @Autowired
    DeliveryAgentService deliveryAgentService;

    @PostMapping("/delivery_agent")
    public ResponseEntity<ResponseWrapper> createDeliveryAgent(@RequestBody NewDeliveryAgentRequest newAgentDetails) {
        deliveryAgentService.createNewAgent(newAgentDetails);
        return new ResponseWrapper().getResponse(null, "New delivery agent registered", HttpStatus.CREATED);
    }

    @GetMapping("/delivery_agent/{agentId}")
    public ResponseEntity<ResponseWrapper> getAgentById(@PathVariable long agentId) {
        DeliveryAgent agent =  deliveryAgentService.getAgentById(agentId);
        return new ResponseWrapper().getResponse(
                new DeliveryAgentResponse(agent),
                "Fetched delivery agent with id: " + agentId,
                HttpStatus.OK
        );
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseWrapper> updateAgentDetails(@RequestBody DeliveryAgentRequest newAgentDetails) {
        DeliveryAgent agent = deliveryAgentService.getAgentById(newAgentDetails.getId());
        deliveryAgentService.updateAgentDetails(newAgentDetails);
        return new ResponseWrapper().getResponse(null,"Updated agent details.", HttpStatus.OK);
    }

    @GetMapping("/request_delivery/{agentId}")
    public ResponseEntity<ResponseWrapper> requestDelivery(@PathVariable long agentId) {
        DeliveryAgent agent = deliveryAgentService.getAgentById(agentId);
        RequestOrderResponse deliveryDetails = deliveryAgentService.requestDelivery(agent);
        return new ResponseWrapper().getResponse(deliveryDetails, "Delivery Assigned", HttpStatus.OK);
    }

    @PostMapping("/order_delivered/{agentId}")
    public ResponseEntity<ResponseWrapper> orderDelivered(@PathVariable long agentId) {
        DeliveryAgent agent = deliveryAgentService.getAgentById(agentId);
        Map<String, Long> response = new HashMap<>();
        response.put("oderId", agent.getCurrentOrderId());
        deliveryAgentService.orderDelivered(agent);
        return new ResponseWrapper().getResponse(response, "order is marked delivered", HttpStatus.OK);
    }

}

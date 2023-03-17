package com.swiggy.FeignClientMicroService.client;

import com.swiggy.FeignClientMicroService.beans.request.deliveryAgentClient.DeliveryAgentRequest;
import com.swiggy.FeignClientMicroService.beans.request.deliveryAgentClient.NewDeliveryAgentRequest;
import com.swiggy.FeignClientMicroService.beans.response.ResponseWrapper;
import com.swiggy.FeignClientMicroService.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="deliver-agent", url="http:/localhost:8400",configuration = FeignClientConfiguration.class)
public interface DeliveryAgentClient {
    @PostMapping("/delivery_agent")
    public ResponseEntity<ResponseWrapper> createDeliveryAgent(@RequestBody NewDeliveryAgentRequest newAgentDetails);

    @GetMapping("/delivery_agent/{agentId}")
    public ResponseEntity<ResponseWrapper> getAgentById(@PathVariable long agentId);

    @PostMapping("/update")
    public ResponseEntity<ResponseWrapper> updateAgentDetails(@RequestBody DeliveryAgentRequest newAgentDetails);

    @GetMapping("/request_delivery/{agentId}")
    public ResponseEntity<ResponseWrapper> requestDelivery(@PathVariable long agentId);

    @PostMapping("/order_delivered/{agentId}")
    public ResponseEntity<ResponseWrapper> orderDelivered(@PathVariable long agentId);
}

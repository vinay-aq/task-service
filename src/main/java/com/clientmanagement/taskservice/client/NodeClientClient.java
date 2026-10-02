package com.clientmanagement.taskservice.client;

import com.clientmanagement.taskservice.client.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name="node-client-service",
        url="${node-service.url}"
)
public interface NodeClientClient {
    @GetMapping("/api/clients/{clientId}")
    ClientResponse getClientById(
            @PathVariable Long clientId
    );
}

package com.clientmanagement.taskservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="node-user-service", url="node-service.url")
public interface NodeUserClient {
    @GetMapping("/api/users/{userId}")
    Object getUserById(
            @PathVariable Long userId
    );
}

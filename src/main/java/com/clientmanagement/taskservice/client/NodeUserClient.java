package com.clientmanagement.taskservice.client;

import com.clientmanagement.taskservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "node-user-service", url = "${node-service.url}")
public interface NodeUserClient {
    @GetMapping("/api/users/{userId}")
    UserResponse getUserById(
            @PathVariable Long userId
    );
}

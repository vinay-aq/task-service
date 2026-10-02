package com.clientmanagement.taskservice.task.controller;

import com.clientmanagement.taskservice.client.NodeClientClient;
import com.clientmanagement.taskservice.client.NodeUserClient;
import com.clientmanagement.taskservice.client.dto.ClientResponse;
import com.clientmanagement.taskservice.task.dto.CreateTaskRequest;
import com.clientmanagement.taskservice.task.dto.UpdateTaskRequest;
import com.clientmanagement.taskservice.task.dto.UpdateTaskStatus;
import com.clientmanagement.taskservice.task.entity.Task;
import com.clientmanagement.taskservice.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NodeClientClient nodeClientClient;
    private final NodeUserClient nodeUserClient;

    public TaskController(TaskService taskService, NodeClientClient nodeClientClient, NodeUserClient nodeUserClient) {
        this.taskService = taskService;
        this.nodeClientClient = nodeClientClient;
        this.nodeUserClient = nodeUserClient;
    }

    @PostMapping()
    public Task createTask(@Valid @RequestBody CreateTaskRequest request) {
         return taskService.createTask(request);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/client/{clientId}")
    public List<Task> getTaskByClientId(@PathVariable Long clientId) {
        return taskService.getTasksByClientId(clientId);
    }

    @PatchMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskStatus status) {
        return taskService.updateTaskStatus(taskId, status.getStatus());

    }

    @PatchMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskRequest taskBody) {
        return taskService.updateTask(taskId, taskBody);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/my")
    public List<Task> findTaskByUser(@AuthenticationPrincipal Jwt jwtData) {
        Long userId = jwtData.getClaim("id");
        return taskService.getTaskByAssignedUser(userId);
    }

    @GetMapping("/testNode/{clientId}")
    public ClientResponse testNode(@PathVariable Long clientId) {
        return nodeClientClient.getClientById(clientId);
    }



}

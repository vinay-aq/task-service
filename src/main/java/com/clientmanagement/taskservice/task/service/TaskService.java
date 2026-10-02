package com.clientmanagement.taskservice.task.service;

import com.clientmanagement.taskservice.client.NodeClientClient;
import com.clientmanagement.taskservice.client.NodeUserClient;
import com.clientmanagement.taskservice.client.dto.ClientResponse;
import com.clientmanagement.taskservice.client.dto.UserResponse;
import com.clientmanagement.taskservice.common.exception.ResourceNotFoundException;
import com.clientmanagement.taskservice.task.dto.CreateTaskRequest;
import com.clientmanagement.taskservice.task.dto.UpdateTaskRequest;
import com.clientmanagement.taskservice.task.entity.Task;
import com.clientmanagement.taskservice.task.entity.TaskStatus;
import com.clientmanagement.taskservice.task.respository.TaskRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final NodeClientClient nodeClientClient;
    private final NodeUserClient nodeUserClient;

    public TaskService(TaskRepository taskRepository, NodeClientClient nodeClientClient, NodeUserClient nodeUserClient) {
        this.taskRepository = taskRepository;
        this.nodeClientClient = nodeClientClient;
        this.nodeUserClient = nodeUserClient;
    }

    public Task createTask(CreateTaskRequest request) {
        try {
            ClientResponse client = nodeClientClient.getClientById(request.getClientId());
        } catch (FeignException.NotFound exception) {
            throw new ResourceNotFoundException("Client not found");
        }
        try {
            UserResponse user = nodeUserClient.getUserById(request.getAssignedTo());
        } catch (FeignException.NotFound exception) {
            throw new ResourceNotFoundException("Assigned user not found");
        }


        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setClientId(request.getClientId());
        task.setAssignedTo(request.getAssignedTo());
        task.setStatus(TaskStatus.TODO);
        task.setPriority(request.getPriority());
        task.setDueDate(LocalDate.now().plusDays(3));
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());

        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public List<Task> getTasksByClientId(Long clientId) {
        return taskRepository.findByClientId(clientId);
    }

    public Task updateTaskStatus(Long taskId, TaskStatus statusMsg) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setStatus(statusMsg);
        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, UpdateTaskRequest taskBody) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setTitle(taskBody.getTitle());
        task.setDescription(taskBody.getDescription());
        task.setAssignedTo(taskBody.getAssignedTo());
        task.setPriority(taskBody.getPriority());
        task.setDueDate(taskBody.getDueDate());
        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete((task));
    }

    public List<Task> getTaskByAssignedUser(Long userId) {
        return taskRepository.findByAssignedTo(userId);
    }

}

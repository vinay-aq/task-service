package com.clientmanagement.taskservice.task.dto;

import com.clientmanagement.taskservice.task.entity.TaskPriority;
import com.clientmanagement.taskservice.task.entity.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;

public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotNull
    private Long clientId;
    @NotNull
    private Long assignedTo;
    @NotNull
    private TaskPriority priority;
    private LocalDate dueDate;

    public CreateTaskRequest() {

    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


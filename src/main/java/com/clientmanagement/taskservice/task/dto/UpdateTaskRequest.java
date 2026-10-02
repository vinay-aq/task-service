package com.clientmanagement.taskservice.task.dto;

import com.clientmanagement.taskservice.task.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message="Assignee is required")
    private Long assignedTo;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotNull(message = "due date is required")
    private LocalDate dueDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
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
}

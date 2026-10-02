package com.clientmanagement.taskservice.task.dto;

import com.clientmanagement.taskservice.task.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskStatus {

    @NotNull(message = "status is required")
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

package com.clientmanagement.taskservice.common.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

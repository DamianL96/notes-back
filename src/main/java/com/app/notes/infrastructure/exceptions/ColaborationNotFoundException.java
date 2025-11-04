package com.app.notes.infrastructure.exceptions;

public class ColaborationNotFoundException extends RuntimeException {
    public ColaborationNotFoundException(String message) {
        super(message);
    }
}

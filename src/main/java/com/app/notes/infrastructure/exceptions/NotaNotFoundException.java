package com.app.notes.infrastructure.exceptions;

public class NotaNotFoundException extends RuntimeException {
    public NotaNotFoundException(String message) {
        super(message);
    }
}

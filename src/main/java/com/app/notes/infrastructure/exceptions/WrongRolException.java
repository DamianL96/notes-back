package com.app.notes.infrastructure.exceptions;

public class WrongRolException extends RuntimeException {
    public WrongRolException(String message) {
        super(message);
    }
}

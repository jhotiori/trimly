package org.trimly.backend.exception;

public class EntityNotFoundException extends TrimlyException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}

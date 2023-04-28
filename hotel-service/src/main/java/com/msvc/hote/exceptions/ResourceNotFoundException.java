package com.msvc.hote.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Recurso NO encontrado en el servidor");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

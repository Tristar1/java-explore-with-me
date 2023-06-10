package ru.practicum.error.Exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message + " was not found");
    }

}

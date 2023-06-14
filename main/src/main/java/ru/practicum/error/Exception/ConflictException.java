package ru.practicum.error.Exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message + " conflict!");
    }

}

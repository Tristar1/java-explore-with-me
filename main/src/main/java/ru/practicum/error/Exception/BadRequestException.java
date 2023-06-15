package ru.practicum.error.Exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message + " bad parameter!");
    }

}

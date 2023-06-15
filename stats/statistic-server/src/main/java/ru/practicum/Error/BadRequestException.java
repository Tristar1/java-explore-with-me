package ru.practicum.Error;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message + " bad parameter!");
    }

}

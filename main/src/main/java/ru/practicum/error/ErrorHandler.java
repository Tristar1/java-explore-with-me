package ru.practicum.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.error.Exception.ObjectNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ObjectNotFoundException e) {
        return ErrorResponse.builder()
                .reason("The required object was not found.")
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
    }

}

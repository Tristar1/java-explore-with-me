package ru.practicum.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ValidationException.class, BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final RuntimeException e) {
        return ErrorResponse.builder()
                .errors(List.of(e.getClass().getName()))
                .reason("Bad request parameters!")
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse throwable(final Throwable e) {

        return ErrorResponse.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason("Internet server error.")
                .message(e.getLocalizedMessage())
                .build();
    }


}

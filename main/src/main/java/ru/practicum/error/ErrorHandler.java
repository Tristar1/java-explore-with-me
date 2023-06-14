package ru.practicum.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.practicum.error.Exception.AccessException;
import ru.practicum.error.Exception.BadRequestException;
import ru.practicum.error.Exception.ConflictException;
import ru.practicum.error.Exception.ObjectNotFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final ObjectNotFoundException e) {
        return ErrorResponse.builder()
                .errors(List.of(e.getClass().getName()))
                .reason("The required object was not found.")
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class,
            MissingServletRequestParameterException.class, TransactionSystemException.class,
            BadRequestException.class, MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final RuntimeException e) {
        return ErrorResponse.builder()
                .errors(List.of(e.getClass().getName()))
                .reason("Bad request parameters!")
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({ConflictException.class, AccessException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(final RuntimeException e) {

        return ErrorResponse.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.CONFLICT)
                .reason("Integrity constraint has been violated.")
                .message(e.getLocalizedMessage())
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

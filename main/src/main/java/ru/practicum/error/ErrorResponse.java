package ru.practicum.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String reason;
    private String message;
    private Timestamp timestamp;

}

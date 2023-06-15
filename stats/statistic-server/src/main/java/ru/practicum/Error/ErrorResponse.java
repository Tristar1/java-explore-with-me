package ru.practicum.Error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {

    private List<String> errors;
    private HttpStatus status;
    private String reason;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Timestamp timestamp = Timestamp.from(Instant.now());

}

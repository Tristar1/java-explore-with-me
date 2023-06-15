package ru.practicum.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.practicum.constants.DateTimeFormat;

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
    @JsonFormat(pattern = DateTimeFormat.DATE_TIME_FORMAT)
    private final Timestamp timestamp = Timestamp.from(Instant.now());

}

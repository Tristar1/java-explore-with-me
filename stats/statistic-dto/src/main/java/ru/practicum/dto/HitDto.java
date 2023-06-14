package ru.practicum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HitDto {

    @NotBlank
    private String app;
    @NotBlank
    private String uri;
    @NotBlank
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp;

    public HitDto (HttpServletRequest httpServletRequest, String app) {
        this.app = app;
        this.ip = httpServletRequest.getRemoteAddr();
        this.uri = httpServletRequest.getRequestURI();
        this.timestamp = Timestamp.from(Instant.now());
    }

}

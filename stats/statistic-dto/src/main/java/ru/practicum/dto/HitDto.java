package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Builder
public class HitDto {

    @NotBlank
    private String app;
    @NotBlank
    private String uri;
    @NotBlank
    private String ip;
    private Timestamp timestamp;

}

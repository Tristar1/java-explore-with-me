package ru.practicum.dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.constants.DateTimeFormat;
import ru.practicum.entity.State;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ParticipationRequestDto {
    private Long id;
    @NotNull
    private Long event;
    @NotNull
    private Long requester;
    private State status;
    @JsonFormat(pattern = DateTimeFormat.DATE_TIME_FORMAT)
    private LocalDateTime created;
}

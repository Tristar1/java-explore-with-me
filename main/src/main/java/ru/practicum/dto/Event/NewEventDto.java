package ru.practicum.dto.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.constants.DateTimeFormat;
import ru.practicum.dto.Location.LocationDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    @Size(max = 2000, min = 20)
    @NotBlank
    private String annotation;
    @NotNull
    private Long category;
    @Size(max = 7000, min = 20)
    @NotBlank
    private String description;
    @NotNull
    @JsonFormat(pattern = DateTimeFormat.DATE_TIME_FORMAT)
    private Timestamp eventDate;
    @NotNull
    private LocationDto location;
    @Builder.Default
    private Boolean paid = false;
    private Integer participantLimit;
    private Boolean requestModeration;
    @Size(max = 120, min = 3)
    @NotBlank
    private String title;
    private String state;

}

package ru.practicum.dto.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.constants.DateTimeFormat;
import ru.practicum.dto.Location.LocationDto;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest {

    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(pattern = DateTimeFormat.DATE_TIME_FORMAT)
    private Timestamp eventDate;
    private LocationDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String stateAction;
    private String title;

}

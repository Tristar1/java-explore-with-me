package ru.practicum.dto.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.practicum.constants.DateTimeFormat;
import ru.practicum.dto.Category.CategoryDto;
import ru.practicum.dto.User.UserDto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NotNull
public class EventShortDto {

    public Long id;
    @NotNull
    @Length(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private CategoryDto category;
    private Integer confirmedRequests;
    @NotNull
    @JsonFormat(pattern = DateTimeFormat.DATE_TIME_FORMAT)
    private Timestamp eventDate;
    private UserDto initiator;
    private Boolean paid;
    @NotNull
    @Length(min = 3, max = 120)
    private String title;
    private Integer views;

}

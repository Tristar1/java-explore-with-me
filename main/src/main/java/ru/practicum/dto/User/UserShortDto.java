package ru.practicum.dto.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserShortDto {

    private Long id;
    private String name;

}

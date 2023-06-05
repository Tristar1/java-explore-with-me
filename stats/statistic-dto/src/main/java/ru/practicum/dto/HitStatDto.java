package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class HitStatDto {

    private String app;
    private String uri;
    private Long hits;

}

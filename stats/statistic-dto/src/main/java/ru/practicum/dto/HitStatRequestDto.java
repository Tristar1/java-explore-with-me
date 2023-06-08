package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class HitStatRequestDto {

    private Timestamp start;
    private Timestamp end;
    private List<String> uris;
    private boolean unique;

}

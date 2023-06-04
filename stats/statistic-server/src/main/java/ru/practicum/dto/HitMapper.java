package ru.practicum.dto;

import ru.practicum.entity.Hit;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .app(hitDto.getApp())
                .timestamp(hitDto.getTimestamp())
                .build();
    }

}

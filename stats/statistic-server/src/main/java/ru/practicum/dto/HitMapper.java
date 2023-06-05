package ru.practicum.dto;

import ru.practicum.entity.Hit;
import ru.practicum.entity.HitStat;

import java.util.Collection;
import java.util.stream.Collectors;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .app(hitDto.getApp())
                .timestamp(hitDto.getTimestamp())
                .build();
    }

    public static Collection<HitStatDto> toCollectionHitStatDto(Collection<HitStat> hitStatDtoCollection) {
        return hitStatDtoCollection.stream()
                .map(HitMapper::toHitStatDto)
                .collect(Collectors.toList());
    }

    private static HitStatDto toHitStatDto(HitStat hitStat) {
        return HitStatDto.builder()
                .app(hitStat.getApp())
                .uri(hitStat.getUri())
                .hits(hitStat.getHits())
                .build();
    }

}

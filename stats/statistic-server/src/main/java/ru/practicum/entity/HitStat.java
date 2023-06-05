package ru.practicum.entity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HitStat {

    private String app;
    private String uri;
    private long hits;

}

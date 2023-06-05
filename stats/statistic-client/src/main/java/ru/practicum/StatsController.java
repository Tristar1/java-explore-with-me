package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.HitStatRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class StatsController {

    private final StatsClient statsClient;

    @PostMapping("/hit")
    public ResponseEntity<Object> create(HttpServletRequest request) {

        HitDto hitDto = HitDto.builder()
                .uri("/events")
                .ip(request.getRemoteAddr())
                .app(request.getParameter("app") == null || request.getParameter("app").isBlank()
                        ? "UNKNOWN" : request.getParameter("app"))
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        return statsClient.createHit(hitDto);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStat(@Nullable @RequestParam("start") Timestamp start,
                                         @Nullable @RequestParam("end") Timestamp end,
                                         @RequestParam("uris") ArrayList<String> uris,
                                         @RequestParam(name = "unique", defaultValue = "false") boolean unique,
                                          HttpServletRequest request) {

        log.info("GET stats start={}, end={}, uris={}, unique={}", start, end, uris, unique);

        HitDto hitDto = HitDto.builder()
                .uri("/events")
                .ip(request.getRemoteAddr())
                .app(request.getParameter("app") == null || request.getParameter("app").isBlank()
                        ? "UNKNOWN" : request.getParameter("app"))
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        statsClient.createHit(hitDto);

        HitStatRequestDto hitStatRequest = HitStatRequestDto.builder()
                .start(start)
                .end(end)
                .uris(uris)
                .unique(unique)
                .build();

        return statsClient.getStat(hitStatRequest);
    }

}

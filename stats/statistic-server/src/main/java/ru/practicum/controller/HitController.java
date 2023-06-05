package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.*;
import ru.practicum.service.HitService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class HitController {

    private final HitService hitService;

    @PostMapping("/hit")
    public ResponseEntity<HashMap<String, String>> create(HttpServletRequest request) {

        HitDto hitDto = HitDto.builder()
                .uri("/events")
                .ip(request.getRemoteAddr())
                .app(request.getParameter("app") == null || request.getParameter("app").isBlank()
                        ? "UNKNOWN" : request.getParameter("app"))
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        return hitService.create(hitDto);
    }

    @GetMapping("/stats")
    public Collection<HitStatDto> getStat(@RequestParam("start") Timestamp start,
                                          @RequestParam("end") Timestamp end,
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

        hitService.create(hitDto);

        return hitService.getStatistic(start, end, uris, unique);
    }

}

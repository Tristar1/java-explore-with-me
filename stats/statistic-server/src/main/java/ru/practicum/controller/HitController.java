package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.*;
import ru.practicum.service.HitService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class HitController {

    private final HitService hitService;

    @PostMapping("/hit")
    public ResponseEntity<HashMap<String, String>> create(@Valid @RequestBody HitDto hitDto) {

        log.info("POST EndpointHit {}", hitDto);

        return hitService.create(hitDto);
    }

    @GetMapping("/stats")
    public List<HitStatDto> create(@RequestHeader("start") Timestamp start,
                                   @RequestHeader("end") Timestamp end,
                                   @RequestParam("uris") Set<String> uris,
                                   @RequestParam("unique") boolean unique) {

        log.info("GET stats start={}, end={}, uris={}, unique={}", start, end, uris, unique);

        return hitService.getStatistic(start, end, new ArrayList<>(uris), unique);
    }

}

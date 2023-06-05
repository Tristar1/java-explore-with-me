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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class HitController {

    private final HitService hitService;

    @PostMapping("/hit")
    public ResponseEntity<HashMap<String, String>> create(@RequestBody HitDto hitDto) {

        return hitService.create(hitDto);

    }


    @GetMapping("/stats")
    public Collection<HitStatDto> getStat(@RequestParam("start") Timestamp start,
                                          @RequestParam("end") Timestamp end,
                                          @RequestParam(name = "uris", defaultValue = "") List<String> uris,
                                          @RequestParam(name = "unique", defaultValue = "false") boolean unique,
                                          HttpServletRequest request) {

        request.getParameterMap();
        log.info("GET stats start={}, end={}, uris={}, unique={}", start, end, uris, unique);

        return hitService.getStatistic(start, end, uris, unique);
    }

}

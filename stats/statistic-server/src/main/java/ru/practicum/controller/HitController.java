package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.*;
import ru.practicum.service.HitService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
public class HitController {

    private final HitService hitService;

    @PostMapping("/hit")
    public ResponseEntity<HashMap<String, String>> create(@Valid @RequestBody HitDto hitDto) {

        return hitService.create(hitDto);
    }

    @PostMapping("/stats")
    public Collection<HitStatDto> getStat(@RequestBody HitStatRequestDto hitStatRequestDto) {

        return hitService.getStatistic(hitStatRequestDto.getStart(), hitStatRequestDto.getEnd(),
                                        hitStatRequestDto.getUris(), hitStatRequestDto.isUnique());

    }

}

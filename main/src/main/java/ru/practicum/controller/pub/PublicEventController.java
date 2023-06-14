package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatsClient;
import ru.practicum.constants.EventSorts;
import ru.practicum.dto.Event.EventFullDto;
import ru.practicum.dto.Event.EventShortDto;
import ru.practicum.dto.HitDto;
import ru.practicum.error.Exception.BadRequestException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.Event.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;

@Validated
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class PublicEventController {

    private final EventService eventService;
    private final StatsClient statsClient;

    @GetMapping
    public List<EventShortDto> getFilteredEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) Timestamp rangeStart,
            @RequestParam(required = false) Timestamp rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) EventSorts sort,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            HttpServletRequest request) {

        statsClient.createHit(new HitDto(request, "ewm-main-service"));

        if (rangeStart != null && rangeEnd != null && rangeStart.after(rangeEnd)) {
            throw new BadRequestException("range start ");
        }

        return EventMapper.toEventShortDtoList(
                eventService.getAllWithParametersPublic(text, categories, paid, rangeStart,
                        rangeEnd, onlyAvailable, sort, from, size));
    }

    @GetMapping("{id}")
    public EventFullDto getById(@PathVariable @Min(0) long id, HttpServletRequest request) {

        statsClient.createHit(new HitDto(request, "ewm-main-service"));
        return EventMapper.toEventFullDto(eventService.getByIdPublic(id, request.getRemoteAddr()));
    }


}

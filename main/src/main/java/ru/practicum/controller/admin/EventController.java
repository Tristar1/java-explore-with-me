package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Event.EventFullDto;
import ru.practicum.dto.Event.UpdateEventAdminRequest;
import ru.practicum.entity.EventState;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.Event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

    @PatchMapping("{eventId}")
    public EventFullDto update(@PathVariable @Min(0) long eventId,
                               @Valid @RequestBody UpdateEventAdminRequest updateRequest) {
        return EventMapper.toEventFullDto(eventService.updateByAdmin(eventId,
                EventMapper.toEvent(updateRequest)));
    }

    @GetMapping
    public List<EventFullDto> getAllByParameters(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<EventState> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Timestamp rangeStart,
            @RequestParam(required = false) Timestamp rangeEnd,
            @RequestParam(defaultValue = "0", required = false) @Min(0) final int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) final int size) {

        return EventMapper.toEventFullDtoList(eventService.getAllWithParameters(users, states, categories,
                rangeStart, rangeEnd, from, size));
    }

}

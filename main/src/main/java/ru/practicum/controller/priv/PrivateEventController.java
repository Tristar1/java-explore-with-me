package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Event.*;
import ru.practicum.dto.Request.ParticipationRequestDto;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.service.Event.EventService;
import ru.practicum.service.Request.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class PrivateEventController {

    private final EventService eventService;
    private final RequestService requestService;

    @GetMapping
    public List<EventShortDto> getEvents(
            @PathVariable @Min(0) Long userId,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size) {

        return EventMapper.toEventShortDtoList(
                eventService.getInitiatorEvents(userId, from, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(
            @PathVariable @Min(0) long userId,
            @RequestBody @Valid NewEventDto eventDto) {

        return EventMapper.toEventFullDto(
                eventService.create(userId, EventMapper.toEvent(eventDto, false)));
    }

    @GetMapping("{eventId}")
    public EventFullDto getInitiatorEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId) {

        return EventMapper.toEventFullDto(eventService.getInitiatorEventById(eventId, userId));
    }

    @PatchMapping("{eventId}")
    public EventFullDto updateEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId,
            @RequestBody NewEventDto eventDto) {

        return EventMapper.toEventFullDto(
                eventService.update(userId, eventId, EventMapper.toEvent(eventDto, false)));
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getUserEventRequests(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId) {
        return RequestMapper.toParticipationRequestDtoList(
                requestService.getEventRequests(userId, eventId));
    }

    @PatchMapping("{eventId}/requests")
    public EventRequestStatusUpdateResult updateEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId,
            @Valid @RequestBody EventRequestStatusUpdateRequest requestStatusUpdate) {

        return RequestMapper.toEventRequestStatusUpdateResult(
                requestService.updateStatus(userId, eventId, requestStatusUpdate));

    }

}

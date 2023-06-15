package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Request.ParticipationRequestDto;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.service.Request.RequestService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
@Slf4j
public class PrivateRequestController {

    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createRequest(@PathVariable @Min(0) long userId,
                                                 @RequestParam @Min(0) long eventId) {

        return RequestMapper.toParticipationRequestDto(requestService.create(userId, eventId));
    }

    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable @Min(0) long userId) {

        return RequestMapper.toParticipationRequestDtoList(requestService.getAll(userId));
    }

    @PatchMapping("{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable @Min(0) long userId,
                                                 @PathVariable @Min(0) long requestId) {

        return RequestMapper.toParticipationRequestDto(requestService.cancelRequest(userId, requestId));
    }

}

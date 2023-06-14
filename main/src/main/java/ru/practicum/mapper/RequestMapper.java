package ru.practicum.mapper;

import ru.practicum.dto.Request.ParticipationRequestDto;
import ru.practicum.entity.Request;

import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {

        return ParticipationRequestDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus())
                .created(request.getCreated().toLocalDateTime())
                .build();
    }

    public static List<ParticipationRequestDto> toParticipationRequestDtoList(
            List<Request> requests) {
        return requests.stream()
                .map(RequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

}

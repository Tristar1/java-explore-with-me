package ru.practicum.mapper;

import ru.practicum.dto.Event.EventFullDto;
import ru.practicum.dto.Event.EventShortDto;
import ru.practicum.dto.Event.NewEventDto;
import ru.practicum.entity.Category;
import ru.practicum.entity.Event;
import ru.practicum.entity.State;

import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public static Event toEvent(NewEventDto eventDto, boolean update) {
        if (!update) {
            return Event.builder()
                    .annotation(eventDto.getAnnotation())
                    .category(Category.builder().id(eventDto.getCategory()).build())
                    .description(eventDto.getDescription())
                    .eventDate(eventDto.getEventDate())
                    .location(LocationMapper.toLocation(eventDto.getLocation()))
                    .paid(eventDto.getPaid())
                    .participantLimit(eventDto.getParticipantLimit())
                    .requestModeration(eventDto.getRequestModeration())
                    .title(eventDto.getTitle())
                    .build();
        } else {

            Event event = new Event();
            if (eventDto.getAnnotation() != null) event.setLocation(
                    LocationMapper.toLocation(eventDto.getLocation()));
            if (eventDto.getCategory() != null) event.setCategory(
                    Category.builder().id(eventDto.getCategory()).build());
            if (eventDto.getDescription() != null) event.setDescription(
                    eventDto.getDescription());
            if (eventDto.getEventDate() != null) event.setEventDate(
                    eventDto.getEventDate());
            if (eventDto.getLocation() != null) event.setLocation(LocationMapper.toLocation(
                    eventDto.getLocation()));
            if (eventDto.getPaid() != null) event.setPaid(
                    eventDto.getPaid());
            if (eventDto.getParticipantLimit() != null) event.setParticipantLimit(
                    eventDto.getParticipantLimit());
            if (eventDto.getRequestModeration() != null) event.setRequestModeration(
                    eventDto.getRequestModeration());
            if (eventDto.getState() != null) event.setState(State.valueOf(eventDto.getState()));
            if (eventDto.getTitle() != null) event.setTitle(eventDto.getTitle());
            return event;

        }

    }

    public static EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn().toLocalDateTime())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(LocationMapper.toLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn().toLocalDateTime())
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .eventDate(event.getEventDate())
                .confirmedRequests(event.getConfirmedRequests())
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .initiator(UserMapper.toUserDto(event.getInitiator()))
                .annotation(event.getAnnotation())
                .id(event.getId())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .build();
    }

    public static List<EventShortDto> toEventShortDtoList(List<Event> eventList) {
        return eventList.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }


}

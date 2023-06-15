package ru.practicum.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.practicum.dto.Event.*;
import ru.practicum.entity.Category;
import ru.practicum.entity.Event;
import ru.practicum.entity.EventState;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventMapper {

    public static Event toEvent(NewEventDto eventDto) {

        log.info("Event {} mapped", eventDto);

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
    }

    public static Event toEvent(UpdateEventAdminRequest eventAdminDto) {

        Event event = new Event();
        if (eventAdminDto.getAnnotation() != null) event.setAnnotation(eventAdminDto.getAnnotation());
        if (eventAdminDto.getCategory() != null) event.setCategory(
                Category.builder().id(eventAdminDto.getCategory()).build());
        if (eventAdminDto.getDescription() != null) event.setDescription(
                eventAdminDto.getDescription());
        if (eventAdminDto.getEventDate() != null) event.setEventDate(
                eventAdminDto.getEventDate());
        if (eventAdminDto.getLocation() != null) event.setLocation(LocationMapper.toLocation(
                eventAdminDto.getLocation()));
        if (eventAdminDto.getPaid() != null) event.setPaid(
                eventAdminDto.getPaid());
        if (eventAdminDto.getParticipantLimit() != null) event.setParticipantLimit(
                eventAdminDto.getParticipantLimit());
        if (eventAdminDto.getRequestModeration() != null) event.setRequestModeration(
                eventAdminDto.getRequestModeration());
        if (eventAdminDto.getStateAction() != null)
            event.setState(getEventStateByActionState(eventAdminDto.getStateAction()));
        if (eventAdminDto.getTitle() != null) event.setTitle(eventAdminDto.getTitle());
        return event;

    }

    public static Event toEvent(UpdateEventUserRequest eventUserDto) {

        Event event = new Event();
        if (eventUserDto.getAnnotation() != null) event.setAnnotation(event.getAnnotation());
        if (eventUserDto.getCategory() != null) event.setCategory(
                Category.builder().id(eventUserDto.getCategory()).build());
        if (eventUserDto.getDescription() != null) event.setDescription(
                eventUserDto.getDescription());
        if (eventUserDto.getEventDate() != null) event.setEventDate(
                eventUserDto.getEventDate());
        if (eventUserDto.getLocation() != null) event.setLocation(LocationMapper.toLocation(
                eventUserDto.getLocation()));
        if (eventUserDto.getPaid() != null) event.setPaid(
                eventUserDto.getPaid());
        if (eventUserDto.getParticipantLimit() != null) event.setParticipantLimit(
                eventUserDto.getParticipantLimit());
        if (eventUserDto.getRequestModeration() != null) event.setRequestModeration(
                eventUserDto.getRequestModeration());
        if (eventUserDto.getStateAction() != null)
            event.setState(getEventStateByActionState(eventUserDto.getStateAction()));
        if (eventUserDto.getTitle() != null) event.setTitle(eventUserDto.getTitle());
        return event;

    }

    public static EventFullDto toEventFullDto(Event event) {

        log.info("Event {} mapped to full event", event);

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
                .views((event.getEventViews() == null) ? 0 : event.getEventViews().size())
                .build();
    }

    public static EventShortDto toEventShortDto(Event event) {

        log.info("Event {} mapped to short event", event);

        return EventShortDto.builder()
                .eventDate(event.getEventDate())
                .confirmedRequests(event.getConfirmedRequests())
                .paid(event.getPaid())
                .title(event.getTitle())
                .views((event.getEventViews() == null) ? 0 : event.getEventViews().size())
                .initiator(UserMapper.toUserDto(event.getInitiator()))
                .annotation(event.getAnnotation())
                .id(event.getId())
                .category(CategoryMapper.toCategoryDto(event.getCategory()))
                .build();
    }

    public static List<EventShortDto> toEventShortDtoList(List<Event> eventList) {
        return eventList.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }

    public static List<EventFullDto> toEventFullDtoList(List<Event> eventList) {
        return eventList.stream().map(EventMapper::toEventFullDto).collect(Collectors.toList());
    }

    public static Event updateEvent(Event source, Event receiver) {

        if (source.getAnnotation() != null) receiver.setAnnotation(source.getAnnotation());
        if (source.getCategory() != null) receiver.setCategory(source.getCategory());
        if (source.getDescription() != null) receiver.setDescription(source.getDescription());
        if (source.getEventDate() != null) receiver.setEventDate(source.getEventDate());
        if (source.getLocation() != null) receiver.setLocation(source.getLocation());
        if (source.getPaid() != null) receiver.setPaid(source.getPaid());
        if (source.getParticipantLimit() != null) receiver.setParticipantLimit(source.getParticipantLimit());
        if (source.getRequestModeration() != null) receiver.setRequestModeration(source.getRequestModeration());
        if (source.getState() != null) receiver.setState(source.getState());
        if (source.getTitle() != null) receiver.setTitle(source.getTitle());
        return receiver;
    }

    private static EventState getEventStateByActionState(String str) {
        if (str == null) return null;
        if (str.equals("CANCEL_REVIEW")) return EventState.CANCELED;
        if (str.equals("PUBLISH_EVENT")) return EventState.PUBLISHED;
        if (str.equals("REJECT_EVENT")) return EventState.CANCELED;
        if (str.equals("SEND_TO_REVIEW")) return EventState.PENDING;
        return null;
    }

}

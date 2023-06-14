package ru.practicum.service.Event;

import ru.practicum.constants.EventSorts;
import ru.practicum.entity.Event;
import ru.practicum.entity.EventState;

import java.sql.Timestamp;
import java.util.List;

public interface EventService {

    Event create(long userId, Event event);

    Event save(Event event);

    Event update(long userId, long eventId, Event source);

    List<Event> getInitiatorEvents(long userId, int from, int size);

    List<Event> getAll(List<Long> eventIds);

    Event getInitiatorEventById(long eventId, long userId);

    Event getById(long eventId);

    Event getByIdPublic(long eventId, String remoteIp);

    Event updateByAdmin(long eventId, Event event);

    List<Event> getAllWithParameters(List<Long> users, List<EventState> states, List<Long> categories,
                                     Timestamp rangeStart, Timestamp rangeEnd, Integer from, Integer size);

    List<Event> getAllWithParametersPublic(String text, List<Long> categories, Boolean paid,
                                           Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable,
                                           EventSorts sort, int from, int size);

}

package ru.practicum.service.Event;

import ru.practicum.entity.Event;

import java.util.List;

public interface EventService {

    Event create(long userId, Event event);

    Event save(Event event);

    Event update(long userId, long eventId, Event donor);

    List<Event> getInitiatorEvents(long userId, int from, int size);

    List<Event> getAll(List<Long> eventIds);

    /*List<Event> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                   Timestamp rangeStart, Timestamp rangeEnd, int from, int size);

    List<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart,
                                               Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                               int from, int size);*/

    Event getInitiatorEventById(long eventId, long userId);

    Event getById(long eventId);

    Event getByIdForPublic(long eventId);

    Event updateByAdmin(long eventId, Event event);

}

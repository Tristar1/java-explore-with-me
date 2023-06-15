package ru.practicum.service.Event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.constants.EventSorts;
import ru.practicum.entity.*;
import ru.practicum.error.Exception.AccessException;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.EventViewRepository;
import ru.practicum.repository.LocationRepository;
import ru.practicum.service.Category.CategoryService;
import ru.practicum.service.User.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventViewRepository eventViewRepository;
    private final LocationRepository locationRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public Event create(long userId, Event event) {

        log.info("Create event {}", event);

        User user = userService.getById(userId);
        Location location = getLocation(event.getLocation());
        Category category = categoryService.getById(event.getCategory().getId());
        event.setInitiator(user);
        event.setConfirmedRequests(0);
        event.setLocation(location);
        event.setCategory(category);
        event.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setPublishedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setState(EventState.PENDING);
        event.setViews(0);

        return save(event);
    }

    @Override
    public Event save(Event event) {

        return eventRepository.save(event);

    }

    @Override
    public Event update(long userId, long eventId, Event source) {

        Event receiver = getInitiatorEventById(eventId, userId);
        if (receiver.getState() == EventState.PUBLISHED) {
            throw new AccessException("Error: event state");
        }
        receiver = updateEvent(source, receiver);

        return save(receiver);

    }

    @Override
    public List<Event> getInitiatorEvents(long userId, int from, int size) {

        log.info("Event service get with parameters {} {} {}", userId, from, size);

        return eventRepository.findAllByInitiator(userService.getById(userId))
                .stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAll(List<Long> eventIds) {
        return eventRepository.findAllById(eventIds);
    }

    @Override
    public Event getInitiatorEventById(long eventId, long userId) {
        return eventRepository.findByInitiatorAndId(userService.getById(userId), eventId);
    }

    @Override
    public Event getById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new ObjectNotFoundException("Event with id= " + eventId));
    }

    @Override
    public Event getByIdPublic(long eventId, String remoteIp) {

        Event event = getById(eventId);

        if (event.getState() != EventState.PUBLISHED) {
            throw new ObjectNotFoundException("Event with id = " + eventId);
        }

        if (eventViewRepository.getByIpAndEvent_id(remoteIp, eventId).isEmpty()) {
            EventView view = EventView.builder().event(event).ip(remoteIp).build();
            event.addView(view);
        }

        return event;

    }

    @Override
    public Event updateByAdmin(long eventId, Event source) {
        Event receiver = getById(eventId);
        if (!receiver.getState().equals(EventState.PENDING)) throw new AccessException("Event not pending");
        receiver = updateEvent(source, receiver);

        return save(receiver);
    }

    @Override
    public List<Event> getAllWithParameters(List<Long> users, List<EventState> states, List<Long> categories,
                                            Timestamp rangeStart, Timestamp rangeEnd, Integer from, Integer size) {

        rangeStart = (rangeStart == null) ? Timestamp.valueOf(LocalDateTime.now().minusYears(50)) : rangeStart;
        rangeEnd = (rangeEnd == null) ? Timestamp.valueOf(LocalDateTime.now().plusYears(50)) : rangeEnd;

        return eventRepository.getAllWithParameters(users, states, categories,
                rangeStart, rangeEnd).stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllWithParametersPublic(String text, List<Long> categories, Boolean paid,
                                                  Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable,
                                                  EventSorts sort, int from, int size) {

        log.info("Event service getAllWithParametersPublic {}, {}, {}, {}, {}, {}, {}, {}, {},",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);

        rangeStart = (rangeStart == null) ? Timestamp.valueOf(LocalDateTime.now().minusYears(50)) : rangeStart;
        rangeEnd = (rangeEnd == null) ? Timestamp.valueOf(LocalDateTime.now().plusYears(50)) : rangeEnd;

        text = (text == null) ? "" : text.toLowerCase();
        String sortFields = (sort == EventSorts.VIEWS) ? "views" : "eventDate";
        Sort resultSort = Sort.by(Sort.Direction.ASC, sortFields);

        return eventRepository.getAllWithParametersPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, resultSort)
                .stream().skip(from).limit(size).collect(Collectors.toList());

    }

    private Location getLocation(Location location) {
        return locationRepository.findByLatAndLon(location.getLat(),
                location.getLon()).orElse(locationRepository.save(location));
    }

    private Event updateEvent(Event source, Event receiver) {
        if (source.getLocation() != null) {
            Location location = getLocation(source.getLocation());
            source.setLocation(location);
        }
        return EventMapper.updateEvent(source, receiver);
    }
}

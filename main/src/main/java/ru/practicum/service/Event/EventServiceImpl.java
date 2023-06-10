package ru.practicum.service.Event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.entity.Event;
import ru.practicum.repository.EventRepository;
import ru.practicum.service.User.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserService userService;
    private final EventRepository eventRepository;


    @Override
    public Event create(long userId, Event event) {
        return null;
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public Event update(long userId, long eventId, Event donor) {
        return null;
    }

    @Override
    public List<Event> getInitiatorEvents(long userId, int from, int size) {
        return eventRepository.findAllByInitiator(userService.getById(userId))
                .stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAll(List<Long> eventIds) {
        return null;
    }

    @Override
    public Event getInitiatorEventById(long eventId, long userId) {
        return eventRepository.findByInitiatorAndId(userService.getById(userId), eventId);
    }

    @Override
    public Event getById(long eventId) {
        return null;
    }

    @Override
    public Event getByIdForPublic(long eventId) {
        return null;
    }

    @Override
    public Event updateByAdmin(long eventId, Event event) {
        return null;
    }
}

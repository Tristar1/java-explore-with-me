package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.entity.Event;
import ru.practicum.entity.User;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiator(User initiator);

    Event findByInitiatorAndId (User initiator, long id);


}

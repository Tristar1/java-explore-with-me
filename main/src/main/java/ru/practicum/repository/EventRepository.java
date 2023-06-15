package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Event;
import ru.practicum.entity.EventState;
import ru.practicum.entity.User;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiator(User initiator);

    Event findByInitiatorAndId(User initiator, long id);

    @Query("FROM Event e where " +
            "(e.eventDate >= :rangeStart and e.eventDate <= :rangeEnd) and " +
            "((:initiators is null) or e.initiator.id in :initiators) and " +
            "((:categories is null) or e.category.id in :categories) and" +
            "((:states is null) or e.state in :states)")
    List<Event> getAllWithParameters(List<Long> initiators,
                                     List<EventState> states,
                                     List<Long> categories,
                                     Timestamp rangeStart,
                                     Timestamp rangeEnd);

    @Query("FROM Event e WHERE " +
            "(e.state = 'PUBLISHED') and " +
            "((e.eventDate >= :rangeStart) and (e.eventDate <=:rangeEnd)) and " +
            "(:text is null) or ((lower(e.annotation) like %:text% or lower(e.description) like %:text%)) and " +
            "((:categories is null) or e.category.id in :categories) and " +
            "((:paid is null) or e.paid = :paid) and " +
            "((:onlyAvailable is null) or e.participantLimit > e.confirmedRequests)")
    List<Event> getAllWithParametersPublic(String text,
                                           List<Long> categories,
                                           Boolean paid,
                                           Timestamp rangeStart,
                                           Timestamp rangeEnd,
                                           Boolean onlyAvailable,
                                           Sort sort);


}

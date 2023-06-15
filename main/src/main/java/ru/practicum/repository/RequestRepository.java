package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Event;
import ru.practicum.entity.Request;
import ru.practicum.entity.RequestState;
import ru.practicum.entity.User;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByRequesterId(long id);

    List<Request> findAllByEventId(long eventId);

    @Query("SELECT  count(r.id) " +
            "from Request as r " +
            "WHERE r.event.id = :eventId " +
            "and r.status = :status")
    Optional<Integer> findCountRequestByEventIdAndStatus(long eventId, RequestState status);

    Optional<Request> findByRequesterAndAndEvent(User requester, Event event);

}

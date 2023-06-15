package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.EventView;

import java.util.Optional;

public interface EventViewRepository extends JpaRepository<EventView, Long> {

    @Query("select count (distinct ip) from EventView where event_id = :eventId")
    Integer getEventViewsByEvent_id(Long eventId);

    Optional<EventView> getByIpAndEvent_id(String ip, Long eventId);

}

package ru.practicum.repository;

import ru.practicum.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.HitStat;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT new ru.practicum.entity.HitStat(e.app, e.uri, " +
            "(CASE when :unique=true then COUNT(DISTINCT e.ip) else COUNT(e.ip) end) " +
            ") " +
            "FROM Hit AS e " +
            "WHERE e.timestamp BETWEEN :start AND :end AND e.uri IN (:uris) " +
            "GROUP BY e.app, e.uri " +
            "ORDER BY COUNT(e.ip) DESC")
    Collection<HitStat> getStatistic(Timestamp start, Timestamp end, List<String> uris, boolean unique);

}

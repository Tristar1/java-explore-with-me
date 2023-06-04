package ru.practicum.repository;

import ru.practicum.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.dto.*;

import java.sql.Timestamp;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(value = "select case when ?4=true then count(DISTINCT h.ip)  else count(h.ip) end hits, " +
            "h.app app, h.uri uri from hits h where h.timestamp between ?1 and ?2 and h.uri in ?3" +
            "group by uri, app", nativeQuery = true)
    List<HitStatDto> getStatistic(Timestamp start, Timestamp end, List<String> uris, boolean unique);

}

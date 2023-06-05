package ru.practicum.service;

import org.springframework.http.ResponseEntity;
import ru.practicum.dto.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface HitService {

    ResponseEntity<HashMap<String, String>> create(HitDto hitDto);

    Collection<HitStatDto> getStatistic(Timestamp start, Timestamp end,
                                        List<String> uris, boolean unique);

}

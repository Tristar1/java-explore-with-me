package ru.practicum.service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.HitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.dto.*;
import ru.practicum.repository.HitRepository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    @Override
    public ResponseEntity<HashMap<String, String>> create(HitDto hitDto) {
        hitRepository.save(HitMapper.toHit(hitDto));
        HashMap<String, String> answer = new HashMap<>();
        answer.put("Description", "Информация сохранена");
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @Override
    public Collection<HitStatDto> getStatistic(Timestamp start, Timestamp end, List<String> uris, boolean unique) {
        return HitMapper.toCollectionHitStatDto(hitRepository.getStatistic(start, end, uris, unique));
    }

}

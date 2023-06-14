package ru.practicum.service.Compilation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.repository.CompilationRepository;
import ru.practicum.service.Event.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompilationsServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventService eventService;

    @Override
    public List<Compilation> getCompilations(Boolean pinned, int from, int size) {
        return compilationRepository.findCompilationsByPinned(pinned).stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public Compilation getCompilationById(Long compId) {
        return compilationRepository.findById(compId).orElseThrow(() -> new ObjectNotFoundException("Compilation with ID " + compId));
    }

    @Override
    public Compilation create(Compilation compilation) {
        compilation.setEvents(eventService.getAll(
                compilation.getEvents().stream()
                        .map(Event::getId)
                        .collect(Collectors.toList())
        ));

        return compilationRepository.save(compilation);
    }

    @Override
    public Compilation update(long compId, Compilation compilation) {
        Compilation receiver = getCompilationById(compId);
        return compilationRepository.save(CompilationMapper.update(receiver, compilation));
    }

    @Override
    public void delete(long compId) {
        getCompilationById(compId);
        compilationRepository.deleteById(compId);
    }
}

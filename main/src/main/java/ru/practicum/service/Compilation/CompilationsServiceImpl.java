package ru.practicum.service.Compilation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.entity.Compilation;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.repository.CompilationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompilationsServiceImpl implements CompilationService{

    private final CompilationRepository compilationRepository;


    @Override
    public List<Compilation> getCompilations(boolean pinned, int from, int size) {
        return compilationRepository.findCompilationsByPinned(pinned).stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public Compilation getCompilationById(Long compId) {
        return compilationRepository.findById(compId).orElseThrow(() -> new ObjectNotFoundException("Compilation with ID " + compId));
    }
}

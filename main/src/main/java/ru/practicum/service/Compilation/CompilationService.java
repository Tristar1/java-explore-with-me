package ru.practicum.service.Compilation;

import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationService {

    List<Compilation> getCompilations(boolean pinned, int from, int size);

    Compilation getCompilationById(Long compId);

}

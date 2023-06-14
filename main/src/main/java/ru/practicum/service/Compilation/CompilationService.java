package ru.practicum.service.Compilation;

import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationService {

    List<Compilation> getCompilations(Boolean pinned, int from, int size);

    Compilation getCompilationById(Long compId);

    Compilation create(Compilation toCompilation);

    Compilation update(long compId, Compilation compilation);

    void delete(long compId);

}

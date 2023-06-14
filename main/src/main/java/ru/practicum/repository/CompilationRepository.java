package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Query("from Compilation as c where :pinned is null or c.pinned = :pinned order by c.id")
    List<Compilation> findCompilationsByPinned(Boolean pinned);

}

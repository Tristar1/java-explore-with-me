package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Query("from Compilation as c where c.pinned = :pinned or true order by c.id")
    List<Compilation> findCompilationsByPinned(boolean pinned);

}

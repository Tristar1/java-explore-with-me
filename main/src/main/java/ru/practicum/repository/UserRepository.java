package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE :ids is null or id in :ids")
    Collection<User> findAllByIds(List<Long> ids);

    Optional<User> getByNameIgnoreCase(String name);

}

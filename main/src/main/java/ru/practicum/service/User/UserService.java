package ru.practicum.service.User;

import ru.practicum.dto.User.NewUserDto;
import ru.practicum.entity.User;

import java.util.List;

public interface UserService {

    User create(NewUserDto user);

    List<User> get(List<Long> ids, Integer from, Integer size);

    void delete(Long userId);

    User getById(Long userId);

}

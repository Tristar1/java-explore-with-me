package ru.practicum.service.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.User.NewUserDto;
import ru.practicum.dto.User.UserDto;
import ru.practicum.entity.User;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.UserMapper;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(NewUserDto user) {
        return userRepository.save(UserMapper.toUser(user));
    }

    @Override
    public List<User> get(List<Long> ids, Integer from, Integer size) {
        return userRepository.findAllById(ids).stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long userId) {
        userRepository.delete(getById(userId));
        return true;

    }

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User with ID " + userId));
    }
}

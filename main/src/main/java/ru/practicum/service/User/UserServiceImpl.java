package ru.practicum.service.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.User.NewUserDto;
import ru.practicum.entity.User;
import ru.practicum.error.Exception.ConflictException;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.UserMapper;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(NewUserDto user) {
        log.info("Create user {} service", user);

        if (userRepository.getByNameIgnoreCase(user.getName()).isPresent()) {
            throw new ConflictException("Category with name " + user.getName());
        }

        return userRepository.save(UserMapper.toUser(user));
    }

    @Override
    public List<User> get(List<Long> ids, Integer from, Integer size) {
        return userRepository.findAllByIds(ids).stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(getById(userId));
    }

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User with ID " + userId));
    }
}

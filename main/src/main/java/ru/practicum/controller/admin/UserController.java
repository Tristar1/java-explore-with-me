package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.User.NewUserDto;
import ru.practicum.dto.User.UserDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.service.User.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create (@RequestBody @Valid NewUserDto newUser) {

        return UserMapper.toUserDto(userService.create(newUser));

    }

    @GetMapping
    public List<UserDto> getUsers (@RequestParam(name = "ids") Set<Long> ids,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {

        return UserMapper.userListToUserDtoList(userService.get(new ArrayList<>(ids), from, size));

    }

    @DeleteMapping("/<userId>")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteUser (@PathVariable Long userId) {

        return userService.delete(userId);

    }

}

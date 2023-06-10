package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.entity.Request;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
@Slf4j
public class PrivateRequestController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Request create(@PathVariable("userId") @Min(0) Long userId,
                          @RequestParam @Min(0) Long eventId) {

    }

}

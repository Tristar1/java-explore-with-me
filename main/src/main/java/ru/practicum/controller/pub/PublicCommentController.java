package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Comment.CommentDto;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.Comment.CommentService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class PublicCommentController {

    private final CommentService commentService;

    @GetMapping("/events/{eventId}")
    public List<CommentDto> getEventComments(
            @PathVariable @Min(0) Long eventId,
            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Min(0) Integer size) {

        return CommentMapper.toCommentDtoList(
                commentService.getCommentsWithParameters(eventId, null, null, null,
                        true, null, from, size, "date"));

    }

    @GetMapping("/users/{userId}/events/{eventId}")
    public List<CommentDto> getEventCommentsByUser(
            @PathVariable @Min(0) Long userId,
            @PathVariable @Min(0) Long eventId,
            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Min(0) Integer size) {

        return CommentMapper.toCommentDtoList(
                commentService.getCommentsWithParameters(eventId, List.of(userId), null, null,
                        true, null, from, size, "date"));

    }

    @GetMapping("/events/{eventId}/{commentId}")
    public CommentDto getVisibleCommentByEvent(@PathVariable @Min(0) Long eventId,
                                               @PathVariable @Min(0) Long commentId) {

        return CommentMapper.toCommentDto(commentService.getCommentByIdAndEventIdVisible(commentId, eventId, true));

    }

    @GetMapping("/{commentId}")
    public CommentDto getVisibleComment(@PathVariable @Min(0) Long commentId) {

        return CommentMapper.toCommentDto(commentService.getCommentByIdAndVisible(commentId, true));

    }

}

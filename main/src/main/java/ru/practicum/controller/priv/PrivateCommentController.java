package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Comment.CommentDto;
import ru.practicum.dto.Comment.NewCommentDto;
import ru.practicum.dto.Comment.UpdateCommentDto;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.Comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
@Slf4j
public class PrivateCommentController {

    private final CommentService commentService;

    @PostMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@PathVariable @Min(0) Long userId,
                             @PathVariable @Min(0) Long eventId,
                             @RequestBody @Valid NewCommentDto comment) {

        comment.setAuthorId(userId);
        comment.setEventId(eventId);
        return CommentMapper.toCommentDto(commentService.create(comment));
    }

    @PatchMapping("/{commentId}")
    public CommentDto update(@PathVariable @Min(0) Long userId,
                             @PathVariable @Min(0) Long commentId,
                             @Valid @RequestBody UpdateCommentDto updateComment) {

        updateComment.setAuthorId(userId);
        return CommentMapper.toCommentDto(commentService.update(commentId, updateComment, true));
    }

    @GetMapping("/{commentId}")
    public CommentDto getVisibleComment(@PathVariable @Min(0) Long commentId) {

        return CommentMapper.toCommentDto(commentService.getCommentById(commentId));

    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Min(0) Long userId,
                              @PathVariable @Min(0) Long commentId) {

        commentService.delete(userId, commentId, true);

    }

}

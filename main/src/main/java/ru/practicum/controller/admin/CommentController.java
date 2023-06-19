package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Comment.CommentDto;
import ru.practicum.dto.Comment.UpdateCommentDto;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.Comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments(
            @RequestParam(name = "eventId", required = false) @Min(0) Long eventId,
            @RequestParam(name = "authorIds", required = false) List<Long> authorIds,
            @RequestParam(name = "startDate", required = false) Timestamp startDate,
            @RequestParam(name = "endDate", required = false) Timestamp endDate,
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Min(0) Integer size) {

        return CommentMapper.toCommentDtoList(
                commentService.getCommentsWithParameters(eventId, authorIds, startDate, endDate,
                        null, text, from, size, ""));

    }

    @PatchMapping("{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto update(@PathVariable @Min(0) Long commentId,
                             @Valid @RequestBody UpdateCommentDto updateComment) {

        return CommentMapper.toCommentDto(commentService.update(commentId, updateComment, false));
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @Min(0) Long commentId) {

        commentService.delete(null, commentId, false);

    }

}

package ru.practicum.service.Comment;

import ru.practicum.dto.Comment.NewCommentDto;
import ru.practicum.dto.Comment.UpdateCommentDto;
import ru.practicum.entity.Comment;

import java.sql.Timestamp;
import java.util.List;

public interface CommentService {

    List<Comment> getCommentsWithParameters(Long eventId, List<Long> authorIds, Timestamp startDate, Timestamp endDate,
                                            Boolean visible, String text, int from, int size, String sort);

    Comment getCommentById(Long commId);

    Comment getCommentByIdAndVisible(Long commId, Boolean visible);

    Comment getCommentByIdAndEventIdVisible(Long commId, Long eventId, Boolean visible);

    Comment create(NewCommentDto comment);

    Comment update(Long commId, UpdateCommentDto comment, Boolean byUser);

    void delete(Long userId, Long commId, Boolean byUser);

}

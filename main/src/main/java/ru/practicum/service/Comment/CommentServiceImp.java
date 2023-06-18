package ru.practicum.service.Comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.dto.Comment.NewCommentDto;
import ru.practicum.dto.Comment.UpdateCommentDto;
import ru.practicum.entity.Comment;
import ru.practicum.error.Exception.AccessException;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.repository.CommentRepository;
import ru.practicum.service.Event.EventService;
import ru.practicum.service.User.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final UserService userService;

    @Override
    public List<Comment> getCommentsWithParameters(Long eventId, List<Long> authorIds, Timestamp startDate, Timestamp endDate,
                                                   Boolean visible, String text, int from, int size, String sort) {

        startDate = (startDate == null) ? Timestamp.valueOf(LocalDateTime.now().minusYears(50)) : startDate;
        endDate = (endDate == null) ? Timestamp.valueOf(LocalDateTime.now().plusYears(50)) : endDate;

        List<Sort.Order> orders = new ArrayList<>();
        if (sort.equalsIgnoreCase("date")) {
            orders.add(Sort.Order.desc("publicationDate"));
        }
        else {
            orders.add(Sort.Order.asc("author"));
            orders.add(Sort.Order.desc("publicationDate"));
        }

        Sort resultSort = Sort.by(orders);

        return commentRepository.getCommentsWithParameters(visible, eventId, authorIds, startDate, endDate, text, resultSort)
                .stream().skip(from).limit(size).collect(Collectors.toList());
    }

    @Override
    public Comment getCommentById(Long commId) {
        return commentRepository.findById(commId).orElseThrow(
                () -> new ObjectNotFoundException("Comment with id= " + commId));
    }

    @Override
    public Comment getCommentByIdAndVisible(Long commId, Boolean visible) {
        return commentRepository.getByIdAndVisible(commId, visible).orElseThrow(
                () -> new ObjectNotFoundException("Comment with id= " + commId));
    }

    @Override
    public Comment getCommentByIdAndEventIdVisible(Long commId, Long eventId, Boolean visible) {

        return commentRepository.getByIdAndEventIdAndVisible(commId, eventId, visible).orElseThrow(
                () -> new ObjectNotFoundException("Comment with id= " + commId));

    }

    @Override
    public Comment create(NewCommentDto commentDto) {

        Comment comment = CommentMapper.toComment(commentDto);
        comment.setModifyDate(comment.getPublicationDate());

        comment.setAuthor(userService.getById(commentDto.getAuthorId()));
        comment.setEvent(eventService.getById(commentDto.getEventId()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long commId, UpdateCommentDto comment, Boolean byUser) {

        Comment receiver = getCommentById(commId);

        if (byUser && !Objects.equals(comment.getAuthorId(), receiver.getAuthor().getId())) {
            throw new AccessException("Access error!");
        }

        CommentMapper.updateComment(receiver, comment);
        return commentRepository.save(receiver);
    }

    @Override
    public void delete(Long userId, Long commId, Boolean byUser) {

        Comment comment = getCommentById(commId);

        if (byUser && !Objects.equals(comment.getAuthor().getId(), userId)) {
            throw new AccessException("Access error!");
        }

        commentRepository.delete(comment);

    }
}

package ru.practicum.mapper;

import ru.practicum.dto.Comment.CommentDto;
import ru.practicum.dto.Comment.NewCommentDto;
import ru.practicum.dto.Comment.UpdateCommentDto;
import ru.practicum.entity.Comment;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment toComment(NewCommentDto newCommentDto) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .publicationDate(Timestamp.from(Instant.now()))
                .visible(true)
                .build();
    }

    public static void updateComment(Comment receiver, UpdateCommentDto source) {
        if (source.getVisible() != null) {
            receiver.setVisible(source.getVisible());
        }
        if (source.getText() != null) {
            receiver.setText(source.getText());
        }
        receiver.setModifyDate(Timestamp.from(Instant.now()));
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .visible(comment.getVisible())
                .author(UserMapper.toUserShortDto(comment.getAuthor()))
                .event(EventMapper.toEventShortDto(comment.getEvent()))
                .modifyDate(comment.getModifyDate())
                .modified(!comment.getPublicationDate().equals(comment.getModifyDate()))
                .publicationDate(comment.getPublicationDate())
                .build();
    }

    public static List<CommentDto> toCommentDtoList(List<Comment> commentList) {
        return commentList.stream().map(CommentMapper::toCommentDto).collect(Collectors.toList());
    }

}

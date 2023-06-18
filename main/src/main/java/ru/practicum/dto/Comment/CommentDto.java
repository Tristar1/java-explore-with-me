package ru.practicum.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.Event.EventShortDto;
import ru.practicum.dto.User.UserShortDto;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String text;
    private EventShortDto event;
    private UserShortDto author;
    private Timestamp modifyDate;
    private Boolean modified;
    private Boolean visible;
    private Timestamp publicationDate;

}

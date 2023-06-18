package ru.practicum.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Comment")
@Table(name = "comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "publication_date", nullable = false)
    private Timestamp publicationDate;

    @Column(name = "modify_date", nullable = false)
    private Timestamp modifyDate;

    @Column(nullable = false)
    private Boolean visible;

}

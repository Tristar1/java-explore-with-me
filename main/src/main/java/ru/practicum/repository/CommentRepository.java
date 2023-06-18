package ru.practicum.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Comment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("from Comment where (:visible is null or visible = :visible) " +
            "and (:authorIds is null or author.id in (:authorIds)) " +
            "and (:eventId is null or event.id = :eventId)" +
            "and (publicationDate >= :startDate or modifyDate >= :startDate) " +
            "and (publicationDate <= :endDate or modifyDate <= :endDate) " +
            "and (:text is null or lower(text) like lower(concat('%',:text,'%')))")
    List<Comment> getCommentsWithParameters(Boolean visible, Long eventId, List<Long> authorIds, Timestamp startDate,
                                            Timestamp endDate, String text, Sort sort);

    Optional<Comment> getByIdAndEventIdAndVisible(Long commId, Long eventId, Boolean visible);

    Optional<Comment>getByIdAndVisible(Long commId, Boolean visible);

}

package ru.practicum.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentDto {

    @Length(min = 5, max = 3000)
    private String text;
    private Boolean visible;
    private Long authorId;

}

package ru.practicum.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDto {

    @NotBlank
    @NotNull
    @Length(min = 5, max = 3000)
    private String text;
    private Long eventId;
    private Long authorId;

}

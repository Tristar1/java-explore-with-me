package ru.practicum.dto.Compilation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCompilationDto {

    @Builder.Default
    private List<Long> events = new ArrayList<>();
    @NotNull
    @Builder.Default
    private Boolean pinned = false;
    @NotNull
    @NotBlank
    @Length(max = 50)
    private String title;

}

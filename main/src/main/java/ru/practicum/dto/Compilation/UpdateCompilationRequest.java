package ru.practicum.dto.Compilation;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Data
public class UpdateCompilationRequest {

    private List<Long> events;
    private Boolean pinned;
    @Length(max = 50)
    private String title;
    private String description;

}

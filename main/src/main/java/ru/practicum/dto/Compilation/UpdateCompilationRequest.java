package ru.practicum.dto.Compilation;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UpdateCompilationRequest {

    private Set<Long> events;
    private Boolean pinned;
    private String title;
    private String description;

}

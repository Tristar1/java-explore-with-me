package ru.practicum.dto.Compilation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class NewCompilationDto {

    @NotNull
    private Set<Long> events;
    @NotNull
    private Boolean pinned;
    @NotNull
    private String title;

}

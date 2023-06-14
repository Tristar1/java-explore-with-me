package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Compilation.CompilationDto;
import ru.practicum.dto.Compilation.NewCompilationDto;
import ru.practicum.dto.Compilation.UpdateCompilationRequest;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.service.Compilation.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Slf4j
public class CompilationController {

    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto createCompilation(@Valid @RequestBody NewCompilationDto dto) {

        return CompilationMapper.toCompilationDto(
                compilationService.create(CompilationMapper.toCompilation(dto)));
    }

    @DeleteMapping("{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable @Min(0) long compId) {

        compilationService.delete(compId);
    }

    @PatchMapping("{compId}")
    public CompilationDto update(@Valid @RequestBody UpdateCompilationRequest dto,
                                 @PathVariable @Min(0) long compId) {
        log.info("Update by id={}, for {}", compId, dto.toString());
        return CompilationMapper.toCompilationDto(
                compilationService.update(compId, CompilationMapper.toCompilation(dto)));
    }

}

package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Compilation.CompilationDto;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.service.Compilation.CompilationService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(
            @RequestParam(required = false) boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @Positive @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {

        return CompilationMapper.toCompilationDtoList(compilationService.getCompilations(pinned, from, size));
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilation(@PathVariable("compId") Long compId ){

        return CompilationMapper.toCompilationDto(compilationService.getCompilationById(compId));

    }



}

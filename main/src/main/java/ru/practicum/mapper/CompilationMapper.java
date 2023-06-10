package ru.practicum.mapper;

import ru.practicum.dto.Compilation.CompilationDto;
import ru.practicum.entity.Compilation;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static CompilationDto toCompilationDto (Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(EventMapper.toEventShortDtoList(compilation.getEvents()))
                .build();
    }

    public static List<CompilationDto> toCompilationDtoList (List<Compilation> compilationList) {
        return compilationList.stream().map(CompilationMapper::toCompilationDto).collect(Collectors.toList());
    }

}

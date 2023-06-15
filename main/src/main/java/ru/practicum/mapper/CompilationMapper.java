package ru.practicum.mapper;

import ru.practicum.dto.Compilation.CompilationDto;
import ru.practicum.dto.Compilation.NewCompilationDto;
import ru.practicum.dto.Compilation.UpdateCompilationRequest;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .events(EventMapper.toEventShortDtoList(compilation.getEvents()))
                .build();
    }

    public static Compilation toCompilation(NewCompilationDto dto) {
        return Compilation.builder()
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .events(dto.getEvents().stream().map(CompilationMapper::makeEvent).collect(Collectors.toList()))
                .build();
    }

    public static Compilation toCompilation(UpdateCompilationRequest dto) {
        return Compilation.builder()
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .events((dto.getEvents() == null) ? null :
                        dto.getEvents().stream().map(CompilationMapper::makeEvent).collect(Collectors.toList()))
                .build();
    }

    public static List<CompilationDto> toCompilationDtoList(List<Compilation> compilationList) {
        return compilationList.stream().map(CompilationMapper::toCompilationDto).collect(Collectors.toList());
    }

    public static Compilation update(Compilation receiver, Compilation source) {
        if (source.getEvents() != null) receiver.setEvents(source.getEvents());
        if (source.getPinned() != null) receiver.setPinned(source.getPinned());
        if (source.getTitle() != null) receiver.setTitle(source.getTitle());
        return receiver;
    }

    private static Event makeEvent(Long id) {
        return Event.builder()
                .id(id)
                .build();
    }

}

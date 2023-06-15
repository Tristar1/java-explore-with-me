package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Category.CategoryDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.service.Category.CategoryService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryDto> getAll(@RequestParam(defaultValue = "0") @Min(0) int from,
                                    @RequestParam(defaultValue = "10") @Min(1) int size) {

        return CategoryMapper.toCategoryDtoList(categoryService.getAll(from, size));
    }

    @GetMapping("{catId}")
    public CategoryDto getById(@PathVariable @Min(0) long catId) {

        return CategoryMapper.toCategoryDto(categoryService.getById(catId));
    }
}

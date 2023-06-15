package ru.practicum.mapper;

import ru.practicum.dto.Category.CategoryDto;
import ru.practicum.dto.Category.NewCategoryDto;
import ru.practicum.entity.Category;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category toCategory(NewCategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryDto> toCategoryDtoList(Collection<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }
}

package ru.practicum.service.Category;

import ru.practicum.dto.Category.NewCategoryDto;
import ru.practicum.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(NewCategoryDto category);

    List<Category> getAll(Integer from, Integer size);

    Boolean delete(Long catId);

    Category update(Long catId, Category category);

    Category getById(Long catId);

}

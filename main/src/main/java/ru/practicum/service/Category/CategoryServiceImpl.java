package ru.practicum.service.Category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.Category.NewCategoryDto;
import ru.practicum.entity.Category;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(NewCategoryDto category) {
        return categoryRepository.save(CategoryMapper.toCategory(category));
    }

    @Override
    public List<Category> get(List<Long> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public Boolean delete(Long catId) {
        categoryRepository.delete(getById(catId));
        return true;
    }

    @Override
    public Category update(Long catId, NewCategoryDto categoryDto) {
        Category category = getById(catId);
        category.setName(category.getName());
        return categoryRepository.save(category);
    }

    public Category getById(Long catId) {
        return categoryRepository.findById(catId).orElseThrow(() -> new ObjectNotFoundException("Category with ID " + catId));
    }

}

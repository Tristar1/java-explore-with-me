package ru.practicum.service.Category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.Category.NewCategoryDto;
import ru.practicum.entity.Category;
import ru.practicum.error.Exception.ConflictException;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.repository.CategoryRepository;
import ru.practicum.repository.EventRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Override
    public Category create(NewCategoryDto category) {
        log.info("Create category {} service", category);

        if (categoryRepository.getByNameIgnoreCase(category.getName()).isPresent()) {
            throw new ConflictException("Category with name " + category.getName());
        }

        return categoryRepository.save(CategoryMapper.toCategory(category));
    }

    @Override
    public List<Category> getAll(Integer from, Integer size) {

        return categoryRepository.findAll().stream().skip(from).limit(size).collect(Collectors.toList());

    }

    @Override
    public Boolean delete(Long catId) {
        if (!eventRepository.getAllWithParameters(
                null, null, List.of(catId), Timestamp.valueOf(LocalDateTime.now().minusYears(50)),
                Timestamp.valueOf(LocalDateTime.now().plusYears(50))).isEmpty()) {
            throw new ConflictException("Deleting categories in use!");
        }
        categoryRepository.delete(getById(catId));
        return true;
    }

    @Override
    public Category update(Long catId, Category category) {

        if (categoryRepository.getByNameIgnoreCase(category.getName()).isPresent()
                && categoryRepository.getByNameIgnoreCase(category.getName()).get().getId() != catId) {
            throw new ConflictException("Category with name " + category.getName());
        }

        category.setId(catId);
        return categoryRepository.save(category);
    }

    public Category getById(Long catId) {
        return categoryRepository.findById(catId).orElseThrow(() -> new ObjectNotFoundException("Category with ID " + catId));
    }

}

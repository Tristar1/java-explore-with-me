package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.Category.CategoryDto;
import ru.practicum.dto.Category.NewCategoryDto;
import ru.practicum.dto.User.NewUserDto;
import ru.practicum.dto.User.UserDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.mapper.UserMapper;
import ru.practicum.service.Category.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create (@RequestBody @Valid NewCategoryDto newCategory) {

        return CategoryMapper.toCategoryDto(categoryService.create(newCategory));

    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteCategory (@PathVariable Long catId) {

        return categoryService.delete(catId);

    }

    @PatchMapping("{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@Valid @RequestBody NewCategoryDto newCategoryDto,
                                      @PathVariable @Min(0) long catId) {

        return CategoryMapper.toCategoryDto(categoryService.update(catId, newCategoryDto));
    }

    /*@GetMapping
    public List<CategoryDto> getUsers (@RequestParam(name = "ids") Set<Long> ids,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {

        return UserMapper.userListToUserDtoList(userService.get(new ArrayList<>(ids), from, size));

    }*/



}

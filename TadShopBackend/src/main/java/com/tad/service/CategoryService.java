package com.tad.service;

import com.tad.dto.model.CategoryDto;
import com.tad.dto.model.CreateCategoryDto;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CreateCategoryDto categoryDto);
}

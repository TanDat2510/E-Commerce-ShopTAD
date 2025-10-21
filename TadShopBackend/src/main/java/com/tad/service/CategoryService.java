package com.tad.service;

import com.tad.dto.model.CategoryDto;
import com.tad.dto.model.CreateCategoryDto;
import com.tad.dto.response.CategoryResponseDto;
import com.tad.dto.response.ObjectResponse;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);

    CategoryDto createCategory(CreateCategoryDto categoryDto);

    ObjectResponse<CategoryResponseDto> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDto updateCategory(CreateCategoryDto categoryDto, Long id);

    void deleteCategory(Long id);
}

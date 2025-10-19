package com.tad.service.impl;

import com.tad.dto.exception.ResourceNotFoundException;
import com.tad.dto.mapper.CategoryMapper;
import com.tad.dto.mapper.CreateCategoryMapper;
import com.tad.dto.model.CategoryDto;
import com.tad.dto.model.CreateCategoryDto;
import com.tad.entity.Category;
import com.tad.repositories.CategoryRepository;
import com.tad.service.CategoryService;
import com.tad.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CreateCategoryMapper createCategoryMapper;

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));//custom xu ly ngoai le

        return this.categoryMapper.mapToDto(category);
    }

    @Override
    public CategoryDto createCategory(CreateCategoryDto categoryDto) {
        Category newCategory = this.createCategoryMapper.mapToEntity(categoryDto);

        newCategory.setUrlKey(SlugConvert.convert(categoryDto.getName()));

        Category responseCategory = this.categoryRepository.save(newCategory);

        return this.categoryMapper.mapToDto(responseCategory);
    }
}

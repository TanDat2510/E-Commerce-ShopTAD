package com.tad.service.impl;

import com.tad.dto.exception.ResourceNotFoundException;
import com.tad.dto.mapper.CategoryMapper;
import com.tad.dto.mapper.CreateCategoryMapper;
import com.tad.dto.model.CategoryDto;
import com.tad.dto.model.CreateCategoryDto;
import com.tad.dto.response.CategoryResponseDto;
import com.tad.dto.response.ObjectResponse;
import com.tad.entity.Category;
import com.tad.repositories.CategoryRepository;
import com.tad.service.CategoryService;
import com.tad.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ObjectResponse<CategoryResponseDto> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Category> pages = this.categoryRepository.findAll(pageable);

        // Lay ra gia tri (content) cua page
        List<Category> categories = pages.getContent();

        // Ep kieu sang dto
        List<CategoryResponseDto> content = categories.stream().map(category -> categoryMapper.mapToResponseDto(category)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<CategoryResponseDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        response.setTotalPages(pages.getTotalPages());
        return response;
    }

    @Override
    public CategoryDto updateCategory(CreateCategoryDto categoryDto, Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDto.getName());
        category.setThumbnaiUrl(categoryDto.getThumbnailUrl());
        category.setParentId(categoryDto.getParentId());
        category.setUrlKey(SlugConvert.convert(categoryDto.getName()));

        Category responseCategory = this.categoryRepository.save(category);

        return this.categoryMapper.mapToDto(responseCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        this.categoryRepository.delete(category);
    }
}

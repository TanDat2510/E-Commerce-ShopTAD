package com.tad.controller;

import com.tad.dto.model.CategoryDto;
import com.tad.dto.model.CreateCategoryDto;
import com.tad.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tad/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "categoryId") Long id){
        return new ResponseEntity<>(this.categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto){
        return new ResponseEntity<>(this.categoryService.createCategory(createCategoryDto), HttpStatus.CREATED);
    }

}

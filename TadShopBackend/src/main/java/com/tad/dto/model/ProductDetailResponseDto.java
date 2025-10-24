package com.tad.dto.model;

import com.tad.dto.response.CategoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDetailResponseDto {
    private Long id;


    private String name;


    private String brand;

    private String description;

    private double price;

    private double discountRate;
    private String thumbnailUrl;
    private int reviewCount;
    private double ratingAverage;
    private int quantitySold;
    private String productSlug;
    private String categoryUrl;
    private CategoryResponseDto category;

    private List<ProductOptionDto> options;
    private Set<ProductSpecificationDto> specifications;
}

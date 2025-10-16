package com.tad.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductOptionDto {
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

    private Set<ProductOptionDto> options;
    private Set<ProductSpecificationDto> specifications;
}

package com.tad.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private String urlKey;
    private String thumbnailUrl;
    private Long parentId;
    private Set<ProductDto> products;
}

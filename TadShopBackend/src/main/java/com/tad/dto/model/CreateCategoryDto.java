package com.tad.dto.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCategoryDto {
    private String name;
    private String thumbnailUrl;
    private Long parentId;
    private boolean isPrimary;
}

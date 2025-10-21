package com.tad.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String urlKey;
    private String thumbnailUrl;
    private Long parentId;
}

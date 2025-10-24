package com.tad.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductWithOptionForCartDto {
    private Long id;

    private String name;

    private String brand;

    private double price;

    private double discountRate;

    private String thumbnailUrl;

    private List<ProductOptionDto> option;
}

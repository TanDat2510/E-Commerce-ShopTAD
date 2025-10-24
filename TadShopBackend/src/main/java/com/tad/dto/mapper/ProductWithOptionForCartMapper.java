package com.tad.dto.mapper;

import com.tad.dto.model.ProductWithOptionForCartDto;
import com.tad.entity.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductWithOptionForCartMapper {
    private final ModelMapper mapper;

    public ProductWithOptionForCartDto mapToProductOptionDto(Product product){
        ProductWithOptionForCartDto productWithOptionDto = mapper.map(product, ProductWithOptionForCartDto.class);
        return productWithOptionDto;
    }
}

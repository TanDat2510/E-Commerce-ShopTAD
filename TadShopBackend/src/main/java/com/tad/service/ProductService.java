package com.tad.service;

import com.tad.dto.model.ProductDetailResponseDto;
import com.tad.dto.model.ProductDto;
import com.tad.dto.model.ProductWithOptionForCartDto;
import com.tad.dto.response.ObjectResponse;
import com.tad.entity.ProductOption;
import com.tad.entity.ProductSpecification;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ObjectResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDetailResponseDto getProductById(Long id);
    ProductDto updateProduct(ProductDto productDto, Long productId);
    void deleteProduct(Long productId);
    ObjectResponse<ProductDto> searchProduct(String name, int pageNo, int pageSize, String sortBy, String sortDir);
    ProductWithOptionForCartDto getProductByProductOptionId(String productOptionId);
}

package com.tad.service.impl;

import com.tad.dto.mapper.*;
import com.tad.dto.model.ProductDetailResponseDto;
import com.tad.dto.model.ProductDto;
import com.tad.dto.model.ProductWithOptionForCartDto;
import com.tad.dto.response.ObjectResponse;
import com.tad.entity.Category;
import com.tad.entity.Product;
import com.tad.entity.ProductOption;
import com.tad.entity.ProductSpecification;
import com.tad.repositories.CategoryRepository;
import com.tad.repositories.ProductOptionRepository;
import com.tad.repositories.ProductRepository;
import com.tad.repositories.ProductSpecificationRepository;
import com.tad.service.ProductService;
import com.tad.utils.SlugConvert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    //Repositories
    private ProductRepository productRepository;
    private ProductOptionRepository optionRepository;
    private ProductSpecificationRepository specificationRepository;
    private CategoryRepository categoryRepository;

    //Mappers
    private ProductMapper productMapper;
    private OptionMapper optionMapper;
    private SpecificationMapper specificationMapper;
    private ProductWithOptionForCartMapper productWithOptionMapper;
    private CategoryMapper categoryMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product newProduct = productMapper.mapToEntity(productDto);
        if(!newProduct.getCategoryUrl().equals("")){
            String categoryUrl = SlugConvert.convert(newProduct.getCategoryUrl());
            Category category = this.categoryRepository.findByUrlKey(categoryUrl);
            newProduct.setCategory(category);
        }
        String productSlug = SlugConvert.convert(productDto.getName());
        newProduct.setProductSlug(productSlug);

//        Set<ProductOption> options = new HashSet<>(newProduct.getOptions());
//        for(ProductOption option : options){
//            newProduct.addOption(option);
//        }
//
//        Set<ProductSpecification> specifications = newProduct.getSpecifications();
//        for(ProductSpecification specification : specifications){
//            newProduct.addSpecification(specification);
//        }
        attachOptions(newProduct);
        attachSpecifications(newProduct);

        Product productResponse = this.productRepository.save(newProduct);

        return productMapper.mapToDto(productResponse);
    }

    @Override
    public ObjectResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Tao sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Tao 1 pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tao 1 mang cac trang product su dung find all voi tham so la pageable
        Page<Product> pages = this.productRepository.findAll(pageable);

        // Lay ra gia tri (content) cua page
        List<Product> products = pages.getContent();

        for(Product product : products){

        }

        // Ep kieu sang dto
        List<ProductDto> content = products.stream().map(product -> productMapper.mapToDto(product)).collect(Collectors.toList());

        // Gan gia tri (content) cua page vao ProductResponse de tra ve
        ObjectResponse<ProductDto> response = new ObjectResponse();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        response.setTotalPages(pages.getTotalPages());
        return response;
    }

    @Override
    public ProductDetailResponseDto getProductById(Long id) {
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public ObjectResponse<ProductDto> searchProduct(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public ProductWithOptionForCartDto getProductByProductOptionId(String productOptionId) {
        return null;
    }

    private void attachOptions(Product product) {
        Set<ProductOption> options = product.getOptions();
        if (options != null) {
            for (ProductOption option : options) {
                option.setProduct(product);
            }
        }
    }

    private void attachSpecifications(Product product) {
        Set<ProductSpecification> specs = product.getSpecifications();
        if (specs != null) {
            for (ProductSpecification spec : specs) {
                spec.setProduct(product);
            }
        }
    }

}

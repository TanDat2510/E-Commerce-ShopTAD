package com.tad.repositories;

import com.tad.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository
        extends JpaRepository<ProductSpecification, Long> {
}

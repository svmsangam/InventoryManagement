package com.sbmsangam.project1.project1.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    public Long countById(Integer id);

}

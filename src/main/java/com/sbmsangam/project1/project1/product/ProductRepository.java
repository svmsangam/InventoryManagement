package com.sbmsangam.project1.project1.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Long countById(Integer id);

}

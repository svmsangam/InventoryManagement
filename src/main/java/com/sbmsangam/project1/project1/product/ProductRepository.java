package com.sbmsangam.project1.project1.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Long countById(Integer id);

}

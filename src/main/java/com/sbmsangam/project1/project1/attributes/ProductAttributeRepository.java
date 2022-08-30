package com.sbmsangam.project1.project1.attributes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Integer> {
    public Long countById(Integer id);
}

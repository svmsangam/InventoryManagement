package com.sbmsangam.project1.project1.attributes;

import com.sbmsangam.project1.project1.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Integer> {
    public Long countById(Integer id);

    public List<ProductAttribute> getProductAttributesByProduct(Product product);
}

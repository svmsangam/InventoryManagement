package com.sbmsangam.project1.project1.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    public Long countById(Integer id);
    @Query("SELECT p from Product p where " +
            "CONCAT(p.id,p.name,p.detail,p.sdesc,p.brand.name)" +
            " LIKE %?1%")
    public Page<Product> findAllByName(String keyword, Pageable pageable);

}

package com.sbmsangam.project1.project1.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository  extends JpaRepository<Brand, Integer> {
    public Long countById(Integer id);
}

package com.sbmsangam.project1.project1.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SizeRepository extends JpaRepository<Size,Integer> {
    public Long countById(Integer id);

    public Size findSizeById(Integer id);
}

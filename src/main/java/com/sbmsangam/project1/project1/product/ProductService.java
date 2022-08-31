package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public Page<Product> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return repo.findAll(pageable);
    }


    public void save(Product product) {
        repo.save(product);

    }
    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw  new ProductNotFoundException("Could not find the product");
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if(count==0 || count == null){
            throw new ProductNotFoundException("Could not find the product.");
        }
        repo.deleteById(id);
    }


}

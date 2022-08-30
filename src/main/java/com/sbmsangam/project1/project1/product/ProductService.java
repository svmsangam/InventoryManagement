package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
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

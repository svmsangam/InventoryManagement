package com.sbmsangam.project1.project1.ProductAttributes;

import com.sbmsangam.project1.project1.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAttributeService {
    @Autowired
    private ProductAttributeRepository repo;

    public List<ProductAttribute> listAll(){
        return (List<ProductAttribute>) repo.findAll();
    }


    public void save(ProductAttribute productAttribute) {
        repo.save(productAttribute);

    }
    public ProductAttribute get(Integer id) throws ProductAttributeNotFoundException {
        Optional<ProductAttribute> productAttribute = repo.findById(id);
        if(productAttribute.isPresent()){
            return productAttribute.get();
        }
        throw  new ProductAttributeNotFoundException("Could not find the product attribute");
    }

    public void delete(Integer id) throws ProductAttributeNotFoundException {
        Long count = repo.countById(id);
        if(count==0 || count == null){
            throw new ProductAttributeNotFoundException("Could not find the product.");
        }
        repo.deleteById(id);
    }
}

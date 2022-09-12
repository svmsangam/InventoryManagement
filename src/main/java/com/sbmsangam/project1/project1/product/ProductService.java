package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.attributes.ProductAttributeNotFoundException;
import com.sbmsangam.project1.project1.attributes.ProductAttributeService;
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
    @Autowired ProductAttributeService service;

    public Page<Product> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return repo.findAll(pageable);
    }


    public Product save(Product product) {
        repo.save(product);
        return product;
    }
    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw  new ProductNotFoundException("Could not find the product");
    }

    public void delete(Integer id) throws ProductNotFoundException, ProductAttributeNotFoundException {
        Long count = repo.countById(id);
        if(count==0 || count == null){
            throw new ProductNotFoundException("Could not find the product.");
        }
        Product product = get(id);
        List<ProductAttribute> attributes = service.getProductAttributeByProduct(product);
        for (ProductAttribute attribute: attributes) {
           product.removeAttribute(attribute);
        }
        repo.deleteById(id);
    }
    public Page<Product> listAllSearch(int pageNumber,String keyword){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return repo.findAllByName(keyword,pageable);
    }
}

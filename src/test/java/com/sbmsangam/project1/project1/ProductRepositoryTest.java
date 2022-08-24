package com.sbmsangam.project1.project1;

import com.sbmsangam.project1.project1.product.Product;
import com.sbmsangam.project1.project1.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
    @Autowired private ProductRepository repo;

    @Test
    public void testAddNew(){
        for (int i = 0;i<=10;i++) {
            Product product = new Product();
            product.setName("Product"+(i+1));
            product.setSdesc("Product: "+(i+1));
            product.setDetail("This is a test for inserting product "+(i+1));
            product.setStatus(true);
            Product savedProduct = repo.save(product);

            Assertions.assertThat(savedProduct).isNotNull();
            Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
        }
    }

    @Test
    public void testListProducts(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
        for (Product product: products){
            System.out.println(product);
        }
    }
    @Test
    public void testUpdateProduct(){
        Integer productId = 2;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();
        product.setName("New product");

        Product updatedProduct = repo.findById(productId).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("New product");
    }
    @Test
    public void testGetSpecificProduct(){
        Integer productId = 2;
        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());

    }
    @Test
    public void testDeleteProduct(){
        Integer productId = 2;
        repo.deleteById(2);
        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
    @Test
    public void testDeleteAll(){
        repo.deleteAll();
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeLessThan(1);
    }
}

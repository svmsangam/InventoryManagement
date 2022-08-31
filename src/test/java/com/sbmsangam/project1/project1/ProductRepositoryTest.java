package com.sbmsangam.project1.project1;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.product.Product;
import com.sbmsangam.project1.project1.product.ProductRepository;
import com.sbmsangam.project1.project1.size.Size;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
    @Autowired private ProductRepository repo;
    @Autowired private TestEntityManager entityManager;

    @Test
    public void testAddNew(){
        for (int i = 0;i<=20;i++) {
            Product product = new Product();
            product.setName("Product"+(i+1));
            product.setSdesc("Product: "+(i+1));
            product.setDetail("This is a test for inserting product "+(i+1));
            product.setStatus(true);
            Size size = entityManager.find(Size.class,1);
            ProductAttribute productAttribute = new ProductAttribute(10+i,20+i,40+i,product,
                    size);
            Set<ProductAttribute> attributeSet = new HashSet<>();
            attributeSet.add(productAttribute);
            product.setProductAttribute(attributeSet);
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

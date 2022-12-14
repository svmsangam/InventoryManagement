package com.sbmsangam.project1.project1;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.attributes.ProductAttributeRepository;
import com.sbmsangam.project1.project1.product.Product;
import com.sbmsangam.project1.project1.product.ProductRepository;
import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Set;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
public class ProductAttributeTest {
        @Autowired private ProductRepository pRepo;
        @Autowired private SizeRepository sRepo;
        @Autowired private ProductAttributeRepository prodAttributeRepo;
        @Autowired private  TestEntityManager entityManager;

        @Test
        public void testSize(){
            Size size1 = new Size("Small",true);
            Size size2 = new Size("Medium",false);
            sRepo.save(size1);
            sRepo.save(size2);
        }

        @Test
        public void testProduct(){
            Product product = new Product("Product1","Product1","Product1",false,null);
            pRepo.save(product);
        }

        @Test
        public void testProdAttribAdd(){
            Product product = entityManager.find(Product.class,1);
            Size size = entityManager.find(Size.class,1);
            ProductAttribute productAttribute = new ProductAttribute("100","110","20",product,size);
            prodAttributeRepo.save(productAttribute);

        }
        @Test
        public void getProductAttribute(){
//            Product product = entityManager.find(Product.class,5);
//            Set<ProductAttribute> attributes = product.getProductAttribute();
//            Assertions.assertThat(attributes).hasSizeGreaterThan(0);
//            for (ProductAttribute attribute: attributes){
//                System.out.println(attribute);
//            }
        }



}

package com.sbmsangam.project1.project1;

import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback(false)
public class SizeRepositoryTest {
    @Autowired SizeRepository repo;
    @Test
    public void testAddSize(){
        for(int i =1;i<=4;++i) {
            Size size = new Size();
            switch (i){
                case 1:
                    size.setName("Small");
                    size.setStatus(true);
                    break;
                case 2:
                    size.setName("Medium");
                    size.setStatus(false);
                    break;
                case 3:
                    size.setName("Large");
                    size.setStatus(true);
                case 4:
                    size.setName("Xl");
                    size.setStatus(false);
                default:
                    break;
            }
            size.setName("Small");
            size.setStatus(true);
            Size savedSize = repo.save(size);
            Assertions.assertThat(savedSize).isNotNull();
            Assertions.assertThat(savedSize.getId()).isGreaterThan(0);
        }
    }
    @Test
    public void testViewSizeList(){
        Iterable<Size> sizes = repo.findAll();
        Assertions.assertThat(sizes).hasSizeGreaterThan(0);
        for(Size size: sizes){
            System.out.println(size);
        }

    }
    @Test
    public void testViewSpecificSize(){
        Integer id = 3;
        Optional<Size> optionalSize= repo.findById(id);

        Assertions.assertThat(optionalSize).isPresent();
        System.out.println(optionalSize.get());
    }

    @Test
    public void testUpdateSize(){
        Integer id = 1;
        Optional<Size> optionalSize= repo.findById(id);
        Size size = optionalSize.get();
        size.setName("Small");

        Size updateSize = repo.findById(id).get();
        Assertions.assertThat(updateSize.getName()).isEqualTo("Small");
    }

    @Test
    public void testDeleteSize(){
        Integer id = 4;
        repo.deleteById(id);
        Optional<Size> optionalSize = repo.findById(id);
        Assertions.assertThat(optionalSize).isNotPresent();
    }
}

package com.sbmsangam.project1.project1.brand;

import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired private BrandRepository repo;
    public List<Brand> listAll(){
        return (List<Brand>) repo.findAll();

    }
    public void save(Brand brand){
        repo.save(brand);
    }
    public Brand get(Integer id) throws BrandNotFoundException {
        Optional<Brand> optionalBrand = repo.findById(id);
        if(optionalBrand.isPresent()){
            return optionalBrand.get();
        }throw new BrandNotFoundException("Could not find the brand");
    }
    public void delete(Integer id) throws BrandNotFoundException{
        Long count = repo.countById(id);
        if(count==null || count==0){
            throw new BrandNotFoundException("Brand not found");
        }
        repo.deleteById(id);
    }

}

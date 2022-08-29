package com.sbmsangam.project1.project1.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {
    @Autowired SizeRepository repo;

    public List<Size> listAll(){
         return (List<Size>) repo.findAll();

    }
    public void save(Size size){
        repo.save(size);
    }
    public Size get(Integer id) throws SizeNotFoundException {
        Optional<Size> optionalSize = repo.findById(id);
        if(optionalSize.isPresent()){
            return optionalSize.get();
        }throw new SizeNotFoundException("Could not find the product");
    }
    public void delete(Integer id) throws SizeNotFoundException{
        Long count = repo.countById(id);
        if(count==null || count==0){
            throw new SizeNotFoundException("Size not found");
        }
        repo.deleteById(id);
    }

}

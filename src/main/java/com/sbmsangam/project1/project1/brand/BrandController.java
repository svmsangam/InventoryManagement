package com.sbmsangam.project1.project1.brand;

import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeNotFoundException;
import com.sbmsangam.project1.project1.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    BrandService service;

    @GetMapping("/brands")
    public String showSizeList(Model model) {
        List<Brand> brandList = service.listAll();
        model.addAttribute("brandList", brandList);
        return "brands";
    }

    @GetMapping("/brand/create")
    public String showCreateBrand(Model model) {
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Create Brand");
        model.addAttribute("buttonName", "Add");
        return "brand_create";
    }

    @PostMapping("/brand/save")
    public String saveSize(Brand brand, RedirectAttributes ra) {
//        System.out.println(size.toString());
        service.save(brand);
        ra.addFlashAttribute("message", "New brand created");
        return "redirect:/brands";
    }

    @GetMapping("/brand/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Brand brand = service.get(id);
            model.addAttribute("brand", brand);
            model.addAttribute("pageTitle", "Edit Brand");
            model.addAttribute("buttonName", "Update");
            return "brand_create";
        } catch (BrandNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/brands";
        }
    }
    @GetMapping("/brand/delete/{id}")
    public String deleteSize(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Brand Deleted");
        }catch (BrandNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/brands";
    }

}

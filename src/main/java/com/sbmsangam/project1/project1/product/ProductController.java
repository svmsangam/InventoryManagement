package com.sbmsangam.project1.project1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;

    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> productList = service.listAll();
        model.addAttribute("productList",productList);
        return "products";
    }
    @GetMapping("/product/create")
    public String showCreateProduct(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("pageTitle","Add Product");
        model.addAttribute("buttonName","Add");
        return "product_create";
    }
    @PostMapping("/product/save")
    public String saveProduct(Product product, RedirectAttributes ra){
        service.save(product);
        ra.addFlashAttribute("message","Product has been added successfully");
        return "redirect:/products";
    }
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Product product = service.get(id);
            model.addAttribute("product",product);
            model.addAttribute("pageTitle","Edit Product");
            model.addAttribute("buttonName","Update");
            return "product_create";
        }catch (ProductNotFoundException e){
            ra.addFlashAttribute("message","Product has been added successfully");
            return "redirect:/products";
        }

    }
}

package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.attributes.ProductAttributeNotFoundException;
import com.sbmsangam.project1.project1.attributes.ProductAttributeService;
import com.sbmsangam.project1.project1.brand.Brand;
import com.sbmsangam.project1.project1.brand.BrandService;
import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class ProductController {
    @Autowired private ProductService service;
    @Autowired private SizeService sizeService;
    @Autowired private ProductAttributeService productAttributeService;
    @Autowired private BrandService brandService;
    @Autowired private ProductRepository repo;

    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> productList = service.listAll();
        model.addAttribute("productList",productList);
        return "products";
    }
    @GetMapping("/product/create")
    public String showCreateProduct(Model model){
        List<Size> sizeList = sizeService.listAll();
        List<Brand> brandList = brandService.listAll();
        model.addAttribute("sizeList",sizeList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("product",new Product());
        model.addAttribute("attribute", new ProductAttribute());
        model.addAttribute("pageTitle","Create Product");
        model.addAttribute("buttonName","Add");
        return "product_create";
    }
    @PostMapping("/product/save")
    public String saveProduct(Product product, RedirectAttributes ra, ProductAttribute attribute){
        service.save(product);
        attribute.setProduct(product);
        productAttributeService.save(attribute);
        ra.addFlashAttribute("message","Product has been added successfully");
        return "redirect:/products";
    }
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Product product = service.get(id);
            ProductAttribute attribute = productAttributeService.getProductAttributeByProduct(product);
            List<Size> sizeList = sizeService.listAll();
            List<Brand> brandList = brandService.listAll();
            model.addAttribute("sizeList",sizeList);
            model.addAttribute("brandList",brandList);
            model.addAttribute("product",product);
            model.addAttribute("attribute",attribute);
            model.addAttribute("pageTitle","Edit Product");
            model.addAttribute("buttonName","Update");
            return "product_create";
        }catch (ProductNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/products";
        }

    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Product Deleted");
        }catch (ProductNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/products";
    }

}

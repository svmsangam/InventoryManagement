package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.attributes.ProductAttributeService;
import com.sbmsangam.project1.project1.brand.Brand;
import com.sbmsangam.project1.project1.brand.BrandService;
import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ProductController {
    @Autowired private ProductService service;
    @Autowired private SizeService sizeService;
    @Autowired private ProductAttributeService productAttributeService;
    @Autowired private BrandService brandService;

    @GetMapping("/products")
    public String showProductList(Model model,@Param("name") String name){
        return listByPage(model,1,name);
    }
    @GetMapping("page/{pageNumber}/{keyword}")
    public String listByPage(Model model,
             @PathVariable("pageNumber") int currentPage,
             @PathVariable("keyword") String keyword
            )
    {
        Page<Product> page;
        if(keyword==null) {
            page = service.listAll(currentPage);
        }else{
            page = service.listAllSearch(currentPage,keyword);
        }
        List<Product> productList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("productList",productList);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("keyword",keyword);
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

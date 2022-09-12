package com.sbmsangam.project1.project1.product;

import com.sbmsangam.project1.project1.attributes.ProductAttribute;
import com.sbmsangam.project1.project1.attributes.ProductAttributeNotFoundException;
import com.sbmsangam.project1.project1.attributes.ProductAttributeService;
import com.sbmsangam.project1.project1.brand.Brand;
import com.sbmsangam.project1.project1.brand.BrandService;
import com.sbmsangam.project1.project1.markdown.HTMLService;
import com.sbmsangam.project1.project1.size.Size;
import com.sbmsangam.project1.project1.size.SizeService;
import com.sbmsangam.project1.project1.markdown.HTMLServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;



@Controller
public class ProductController {
    @Autowired private ProductService service;
    @Autowired private SizeService sizeService;
    @Autowired private ProductAttributeService productAttributeService;
    @Autowired private BrandService brandService;
    @Autowired HTMLService htmlService;
    @GetMapping("/products")
    public String showProductList(Model model,@Param("name") String name){
        if(name !=null) {
            return listByPage(model, 1, name);
        }
        return listByPage(model,1);
    }
    @GetMapping("page/{pageNumber}/{keyword}")
    public String listByPage(Model model,
             @PathVariable("pageNumber") int currentPage,
             @PathVariable("keyword") String keyword
            )
    {

        Page<Product> page= service.listAllSearch(currentPage,keyword);
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
    @GetMapping("page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage)
    {
        Page<Product> page = service.listAll(currentPage);
        List<Product> productList = page.getContent();
        for (Product product: productList) {
                product.setDetail(htmlService.markdownToHtml(product.getDetail()));
                model.addAttribute("product", product);
        }
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("productList",productList);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        return "products";
    }
    @GetMapping("/product/create")
    public String showCreateProduct(Model model){
        Product product = new Product();
        List<Size> sizeList = sizeService.listAll();
        List<Brand> brandList = brandService.listAll();
        model.addAttribute("sizeList",sizeList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("product",product);
//        model.addAttribute("attribute",attributes);
        model.addAttribute("pageTitle","Create Product");
        model.addAttribute("buttonName","Add");
        return "product_create";
    }
    @PostMapping("/product/save")
    public String saveProduct(Product product,
                              RedirectAttributes ra,
                              @RequestParam("fileImage") MultipartFile multipartFile,
                            HttpServletRequest request) throws IOException, ProductNotFoundException {
        Integer productId = product.getId();
        String productImage = null;
        if(productId !=null) {
            Product product1 = service.get(productId);
            productImage = product1.getImage();
        }
        String[] attributeId = request.getParameterValues("attributeId");
        String[] attrQty = request.getParameterValues("attrQty");
        String[] attrMrp = request.getParameterValues("attrMrp");
        String[] attrPrice = request.getParameterValues("attrPrice");
        String[] attrSize = request.getParameterValues("attrSize");
        Integer l = attrQty.length;
        for (int i = 0; i < attrQty.length; i++) {
            Size size = sizeService.findById((Integer.parseInt(attrSize[i])));
            if (attributeId != null && attributeId[i] != null && attributeId[i].length() > 0) {
                product.updateAttribute(Integer.parseInt(attributeId[i]), attrPrice[i], attrMrp[i], attrQty[i], size);
//                ra.addFlashAttribute("message","Product Updated");
//                return "redirect:/product/edit/"+productId;
            } else {
                product.addAttributes(size, attrQty[i], attrMrp[i], attrPrice[i]);
            }
        }
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (!multipartFile.isEmpty()) {
            product.setImage(filename);
        } else {
            product.setImage(productImage);
        }

        Product savedProduct = service.save(product);
        if(!multipartFile.isEmpty()) {
            String uploadDir = "./product-image/" + savedProduct.getId();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            } else {

            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(filename);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Image upload failed");
            }
        }
        ra.addFlashAttribute("message","Product has been added successfully");
        return "redirect:/products";
    }
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Product product = service.get(id);
            List<Size> sizeList = sizeService.listAll();
            List<Brand> brandList = brandService.listAll();
            model.addAttribute("sizeList",sizeList);
            model.addAttribute("brandList",brandList);
            model.addAttribute("product",product);
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
        } catch (ProductAttributeNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

}

package com.sbmsangam.project1.project1.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SizeController {
    @Autowired
    SizeService service;

    @GetMapping("/sizes")
    public String showSizeList(Model model) {
        List<Size> sizeList = service.listAll();
        model.addAttribute("sizeList", sizeList);
        return "sizes";
    }

    @GetMapping("/size/create")
    public String showCreateSize(Model model) {
        model.addAttribute("size", new Size());
        model.addAttribute("pageTitle", "Create Size");
        model.addAttribute("buttonName", "Add");
        return "size_create";
    }

    @PostMapping("/size/save")
    public String saveSize(Size size, RedirectAttributes ra) {
//        System.out.println(size.toString());
        service.save(size);
        ra.addFlashAttribute("message", "New size created");
        return "redirect:/sizes";
    }

    @GetMapping("/size/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Size size = service.get(id);
            model.addAttribute("size", size);
            model.addAttribute("pageTitle", "Edit Size");
            model.addAttribute("buttonName", "Update");
            return "size_create";
        } catch (SizeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/sizes";
        }
    }
    @GetMapping("/size/delete/{id}")
    public String deleteSize(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","Size Deleted");
        }catch (SizeNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/sizes";
    }
}

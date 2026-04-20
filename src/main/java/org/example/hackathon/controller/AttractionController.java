package org.example.hackathon.controller;

import org.example.hackathon.model.Attraction;
import org.example.hackathon.service.AttractionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService service;

    @GetMapping({"", "/list"})
    public String list(Model model, @RequestParam(value = "search", required = false) String search) {
        model.addAttribute("items", service.search(search));
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new Attraction());
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("attraction") Attraction attraction,
                       BindingResult result,
                       @RequestParam("imageFile") MultipartFile file) {
        if (result.hasErrors()) return "form";

        if (!file.isEmpty()) {
            try {
                File uploadDir = new File("C:/data/uploads/");
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                file.transferTo(new File(uploadDir.getAbsolutePath() + File.separator + fileName));
                attraction.setCoverImage(fileName);
            } catch (IOException e) { e.printStackTrace(); }
        }
        service.save(attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Attraction a = service.getById(id);
        if (a == null) return "redirect:/attraction/list";
        model.addAttribute("attraction", a);
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/attraction/list";
    }
}
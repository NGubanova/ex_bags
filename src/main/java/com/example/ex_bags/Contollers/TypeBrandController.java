package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Models.Type;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TypeBrandController {
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/brand")
    public String brandMain(Model model){
        Iterable<Brand> ListBrand = brandRepository.findAll();
        model.addAttribute("listBrand", ListBrand);
        return "brand/index";
    }

    @GetMapping("/brand/add")
    public String brandAdd(Brand brand, Model model){
        return "brand/add";
    }

    @PostMapping("/brand/add")
    public String brandAdd(@Valid Brand brand,
                          BindingResult result, Model model){
        if(result.hasErrors())
            return ("brand/add");
        else if (brandRepository.findByName(brand.getName()) != null) {
            model.addAttribute("error", "Бренд уже добавлен");
            return ("brand/add");
        }

        brandRepository.save(brand);
        return "redirect:/brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String brandEdit(Model model,
                           @PathVariable long id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        model.addAttribute("brand", brand);

        return("/brand/edit");
    }

    @PostMapping("/brand/edit/{id}")
    public String brandEdit(@Valid Brand brand,
                           BindingResult result, Model model) {
        if (result.hasErrors())
            return("/brand/edit");

        brandRepository.save(brand);

        return("redirect:/brand");
    }
    @GetMapping("/brand/delete/{id}")
    public String brandDelete(@PathVariable long id) {
        brandRepository.deleteById(id);
        return("redirect:/brand");
    }

    @GetMapping("/brand/filter")
    public String brandFilter(@RequestParam String searchBrand,
                                 Model model){
        List<Brand> brands =brandRepository.findByNameContaining(searchBrand);
        model.addAttribute("listBrand", brands);
        return "brand/index";
    }


    @GetMapping("/type")
    public String typeMain(Type type,
                           Model model){
        Iterable<Type> ListType = typeRepository.findAll();
        model.addAttribute("listType", ListType);
        return "type";
    }

    @PostMapping("/type")
    public String typeAdd(@Valid Type type,
                          BindingResult result){
        if(result.hasErrors())
            return ("type");

        typeRepository.save(type);
        return "redirect:/type";
    }

    @GetMapping("/type/delete/{id}")
    public String typeDelete(@PathVariable long id) {
        typeRepository.deleteById(id);
        return("redirect:/type");
    }
}

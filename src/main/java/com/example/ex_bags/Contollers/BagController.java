package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Bag;
import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Repository.BagRepository;
import com.example.ex_bags.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/bag")
public class BagController {
    @Autowired
    BagRepository bagRepository;
    @Autowired
    BrandRepository brandRepository;

    @GetMapping("")
    public String bagMain(Model model) {
        Iterable<Bag> ListBag = bagRepository.findAll();
        model.addAttribute("listBag", ListBag);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        return "bag/index";
    }

    @GetMapping("/filter")
    public  String bagFilterBrand(@RequestParam String listBrand,
                                  Model model){
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        List<Bag> bagList = bagRepository.findByPurchase_BrandName(listBrand);
        model.addAttribute("listBag", bagList);
        return "bag/index";
    }
}

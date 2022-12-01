package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Models.Purchase;
import com.example.ex_bags.Models.Type;
import com.example.ex_bags.Models.User;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.PurchaseRepository;
import com.example.ex_bags.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    BrandRepository brandRepository;

    @GetMapping("")
    public String purchaseMain(Model model) {
        Iterable<Purchase> ListPurchase = purchaseRepository.findAll();
        model.addAttribute("listPurchase", ListPurchase);
        return "purchase/index";
    }

    @GetMapping("/add")
    public String purchaseAddView(Purchase purchase, Model model) {
        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("listType", types);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        return "purchase/add";
    }

    @PostMapping("/add")
    public String purchaseAdd(@Valid Purchase purchase,
                              BindingResult result,
                              @RequestParam String listType,
                              @RequestParam String listBrand,
                              Model model) {

        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("listType", types);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);

        if (result.hasErrors())
            return ("purchase/add");

        purchase.setType(typeRepository.findByName(listType));
        purchase.setBrand(brandRepository.findByName(listBrand));
        purchaseRepository.save(purchase);
        return "redirect:/purchase";
    }

    @GetMapping("/details/{id}")
    public String purchaseDetails(Model model,
                                  @PathVariable long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        model.addAttribute("purchase", purchase);
        return ("/purchase/details");
    }

    @GetMapping("/edit/{id}")
    public String purchaseEdit(Model model,
                               @PathVariable long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        model.addAttribute("purchase", purchase);
        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("listType", types);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        return ("/purchase/edit");
    }

    @PostMapping("/edit/{id}")
    public String purchaseEdit(@Valid Purchase purchase,
                               BindingResult result, Model model,
                               @RequestParam String listType,
                               @RequestParam String listBrand) {

        Iterable<Type> types = typeRepository.findAll();
        model.addAttribute("listType", types);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);

        if (result.hasErrors())
            return ("/purchase/edit");

        purchase.setType(typeRepository.findByName(listType));
        purchase.setBrand(brandRepository.findByName(listBrand));
        purchaseRepository.save(purchase);

        return ("redirect:/purchase");
    }

    @GetMapping("/delete/{id}")
    public String purchaseDelete(@PathVariable long id) {
        purchaseRepository.deleteById(id);
        return ("redirect:/purchase");
    }

    @GetMapping("/filter")
    public String purchaseFilter(@RequestParam String searchModel,
                                 Model model) {
        List<Purchase> purchases = purchaseRepository.findByModelContaining(searchModel);
        model.addAttribute("listPurchase", purchases);
        return "purchase/index";
    }

}

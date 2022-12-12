package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.*;
import com.example.ex_bags.Repository.BagRepository;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.CellRepository;
import com.example.ex_bags.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bag")
@PreAuthorize("hasAuthority('STOCKMAN')")
public class BagController {
    @Autowired
    BagRepository bagRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CellRepository cellRepository;
    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping("")
    public String bagMain(Model model) {
        Iterable<Bag> ListBag = bagRepository.findAll();
        model.addAttribute("listBag", ListBag);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        return "bag/index";
    }

    @GetMapping("/filter")
    public String bagFilterBrand(@RequestParam String listBrand,
                                 Model model) {
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        List<Bag> bagList = bagRepository.findByPurchase_BrandName(listBrand);
        model.addAttribute("listBag", bagList);
        return "bag/index";
    }

    @GetMapping("/add")
    public String bagAdd(Bag bag, Model model) {
        Iterable<Cell> cells = cellRepository.findAll();
        model.addAttribute("listCell", cells);
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        ArrayList<Purchase> purchaseArrayList = new ArrayList<>();
        for (Purchase pur : purchases) {
            if (pur.getBags() == null) {
                purchaseArrayList.add(pur);
            }
        }
        model.addAttribute("listPurchase", purchaseArrayList);
        return "bag/add";
    }

    @PostMapping("/add")
    public String bagAdd(@Valid Bag bag,
                         BindingResult result,
                         @RequestParam Long listCell,
                         @RequestParam String listPurchase,
                         Model model) {

        Iterable<Cell> cells = cellRepository.findAll();
        model.addAttribute("listCell", cells);
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        ArrayList<Purchase> purchaseArrayList = new ArrayList<>();
        for (Purchase pur : purchases) {
            if (pur.getBags() == null) {
                purchaseArrayList.add(pur);
            }
        }
        model.addAttribute("listPurchase", purchaseArrayList);

        if (result.hasErrors())
            return ("bag/add");
        bag.setStatus(true);
        bag.setCell(cellRepository.findById(listCell).orElseThrow());
        bag.setPurchase(purchaseRepository.findByModel(listPurchase));
        bagRepository.save(bag);
        return "redirect:/bag";
    }

    @GetMapping("/details/{id}")
    public String bagDetails(Model model,
                             @PathVariable long id) {
        Bag bag = bagRepository.findById(id).orElseThrow();
        model.addAttribute("bag", bag);
        return ("/bag/details");
    }

    @GetMapping("/edit/{id}")
    public String bagEdit(Model model,
                          @PathVariable long id) {
        Bag bag = bagRepository.findById(id).orElseThrow();
        model.addAttribute("bag", bag);
        Iterable<Cell> cells = cellRepository.findAll();
        model.addAttribute("listCell", cells);
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("listPurchase", purchases);
        return ("/bag/edit");
    }

    @PostMapping("/edit/{id}")
    public String bagEdit(@Valid Bag bag,
                          BindingResult result, Model model,
                          @RequestParam Long id,
                          @RequestParam Long listCell) {

        Iterable<Cell> cells = cellRepository.findAll();
        model.addAttribute("listCell", cells);

        if (result.hasErrors())
            return ("/bag/edit");

        Cell cell = cellRepository.findById(listCell).orElseThrow();
        bag.setCell(cell);
        bag.setPurchase(bagRepository.findById(id).get().getPurchase());
        bagRepository.save(bag);

        return ("redirect:/bag");
    }
}

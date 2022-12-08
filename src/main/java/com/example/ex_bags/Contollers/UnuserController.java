package com.example.ex_bags.Contollers;


import com.example.ex_bags.Models.Bag;
import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Models.User;
import com.example.ex_bags.Repository.BagRepository;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UnuserController {

    @Autowired
    BagRepository bagRepository;

    @Autowired
    BrandRepository brandRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping("/catalog")
    public String catalog(Model model) {
        Iterable<Bag> ListBag = bagRepository.findAll();
        model.addAttribute("listBag", ListBag);
        Iterable<Brand> brands = brandRepository.findAll();
        model.addAttribute("listBrand", brands);
        return "unuser/catalog";
    }



    @GetMapping("catalog/{id}")
    public String bagDetails(Model model,
                             @PathVariable long id) {
        Bag bag = bagRepository.findById(id).orElseThrow();
        model.addAttribute("bag", bag);
        return ("/unuser/bagDetails");
    }

    @PostMapping("catalog/{id}")
    public String bagDetails(Bag bag, @PathVariable long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        if (user == null){
            return "redirect:/login";
        }

        user.getBags().add(bag);
        employeeRepository.save(user);
        return "unuser/catalog";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
//        Iterable<Brand> brands = brandRepository.findAll();
//        model.addAttribute("listBrand", brands);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        if (user == null){
            return "redirect:/login";
        }

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        model.addAttribute("listBag", ListBag);
        return "unuser/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String bagDelete(@PathVariable long id, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        bagRepository.de

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        for (Bag bags: ListBag){
            if(bags.getUsers() == user ){
                user.getBags().add(bags);
            }
        }

        employeeRepository.save(user);
        ListBag = bagRepository.findBagsByUsersId(user.getId());
        model.addAttribute("listBag", ListBag);
        return "unuser/cart";
    }
}

package com.example.ex_bags.Contollers;


import com.example.ex_bags.Models.*;
import com.example.ex_bags.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class UnuserController {

    @Autowired
    BagRepository bagRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/catalog")
    public String catalog(Model model) {
        Iterable<Bag> ListBag = bagRepository.findBagsByStatusTrue();
        model.addAttribute("listBag", ListBag);
//        Iterable<Brand> brands = brandRepository.findAll();
//        model.addAttribute("listBrand", brands);
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
    public String bagDetails(Bag bag, @PathVariable long id,  Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        if (user == null){
            return "redirect:/login";
        }

        user.getBags().add(bag);
        employeeRepository.save(user);

        bag = bagRepository.findById(id).orElseThrow();
        model.addAttribute("bag", bag);
        return "/unuser/bagDetails";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        if (user == null){
            return "redirect:/login";
        }

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        model.addAttribute("listBag", ListBag);

        Integer sum = 0;
        for (Bag bag : ListBag){
            sum = sum+bag.getPrice();
        }
        Iterable<Integer> summ = List.of(sum);
        model.addAttribute("amount", summ);
        return "unuser/cart";
    }

    @PostMapping("/cart")
    public String bagDelete(@RequestParam long id, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        user.removeBag(bagRepository.findById(id).orElseThrow());
        employeeRepository.save(user);

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        model.addAttribute("listBag", ListBag);

        Integer sum = 0;
        for (Bag bag : ListBag){
            sum = sum+bag.getPrice();
        }
        Iterable<Integer> summ = List.of(sum);
        model.addAttribute("amount", summ);
        return "unuser/cart";
    }

    @GetMapping("/process")
    public String process(Delivery delivery, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        model.addAttribute("listBag", ListBag);

        Iterable<User> u = List.of(user);
        model.addAttribute("user", u);

        Integer sum = 0;
        for (Bag bag : ListBag){
            sum = sum+bag.getPrice();
        }
        Iterable<Integer> summ = List.of(sum);
        model.addAttribute("amount", summ);

        return "/unuser/orderAdd";
    }

    @PostMapping("/process")
    public String process(@Valid Delivery delivery,
                          BindingResult result,
                          Model model){
        if (result.hasErrors())
            return ("unuser/orderAdd");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        delivery.setStatus("В сборке");
        delivery.setUser(user);

        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
        List<Bag> bag_list = bagRepository.findBagsByUsersId(user.getId());
        for (Bag bag : ListBag){
            bag.setStatus(false);
            bagRepository.save(bag);
            user.removeBag(bagRepository.findById(bag.getId()).orElseThrow());
            employeeRepository.save(user);
            if (bag.getDeliveries().size() == 0){
                delivery.setBags(bag_list);
            }
        }

        Integer sum = 0;
        for (Bag bag : ListBag){
            sum = sum+bag.getPrice();
        }
        delivery.setAmount(sum);

        deliveryRepository.save(delivery);
        return "redirect:/catalog";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = employeeRepository.findByUsername(auth.getName());

        if (user == null){
            return "redirect:/login";
        }

//        Iterable<Bag> ListBag = bagRepository.findBagsByUsersId(user.getId());
//        model.addAttribute("listBag", ListBag);

        Iterable<Delivery> ListDelivery = deliveryRepository.findDeliveryByUserId(user.getId());
        List <Delivery> list_Delivery = deliveryRepository.findDeliveryByUserId(user.getId());

        model.addAttribute("listDelivery", ListDelivery);
//        Iterable<Bag> b = bagRepository.findAll();
//        model.addAttribute("listBag", b);

//        for(Delivery delivery : list_Delivery){
//            Iterable<Bag> ListBag = bagRepository.findBagsByDeliveriesId(delivery.getId());
//
//        }

        model.addAttribute("user", user);
        return "/profile";
    }

    @PostMapping("/profile")
    public String editProfile(User user,
                              BindingResult result){
        if (user.getPhone() == ""){
            user.setPhone("+7(___)-___-__-__");
        }
        if (user.getPassword() == ""){
            user.setPassword(employeeRepository.findById(user.getId()).orElseThrow()
                    .getPassword());
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        Post post = postRepository.findById(2L).orElseThrow();
        user.setPost(post);

        employeeRepository.save(user);
        return "/profile";
    }
}

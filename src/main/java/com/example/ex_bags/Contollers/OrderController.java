package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.*;
import com.example.ex_bags.Repository.BagRepository;
import com.example.ex_bags.Repository.DeliveryRepository;
import com.example.ex_bags.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BagRepository bagRepository;

    @GetMapping("")
    public String deliveryMain(Model model){
        Iterable<Delivery> ListDelivery = deliveryRepository.findAll();
        model.addAttribute("listDelivery", ListDelivery);
                return "order/index";
    }

    @GetMapping("/details/{id}")
    public String deliveryDetails(Model model,
                             @PathVariable long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        return ("/order/details");
    }

//    @GetMapping("/add")
//    public String deliveyAdd(Delivery delivery, Model model){
//        Iterable<User> users = employeeRepository.findAll();
//        model.addAttribute("listUser", users);
//        Iterable<Bag> bags = bagRepository.findBagsByStatusTrue();
//        model.addAttribute("bagList", bags);
//        return ("/order/add");
//    }
//
//    @PostMapping("/add")
//    public String deliveryAdd(@Valid Delivery delivery,
//                         BindingResult result,
//                         @RequestParam String listStatus,
//                         @RequestParam String listUser,
//                         Model model) {
//
//        Iterable<User> users = employeeRepository.findAll();
//        model.addAttribute("listUser", users);
//        Iterable<Bag> bags = bagRepository.findBagsByStatusTrue();
//        model.addAttribute("bagList", bags);
//
//        if (result.hasErrors())
//            return ("order/add");
//
//        delivery.setStatus(listStatus);
//        delivery.setUser(employeeRepository.findByName(listUser));
//
//        Iterable<Bag> bag_list = bagRepository.findAll();
//
//        for (Bag bag: bag_list){
//            if (bag.getDeliveries() == null){
//                delivery.getBags().add(bag);
//            }
//        }
//
//        deliveryRepository.save(delivery);
//        return "redirect:/order";
//    }

    @GetMapping("/edit/{id}")
    public String deliveryEdit(Model model,
                          @PathVariable long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        Iterable<User> users = employeeRepository.findAll();
        model.addAttribute("listUser", users);
        Iterable<Bag> bags = bagRepository.findAll();
        model.addAttribute("bagList", bags);
        return ("/order/edit");
    }
    @PostMapping("/edit/{id}")
    public String deliveryEdit(@Valid Delivery delivery,
                              BindingResult result,
                              @RequestParam String listStatus,
                              @RequestParam String listUser,
                              Model model) {

        Iterable<User> users = employeeRepository.findAll();
        model.addAttribute("listUser", users);

        if (result.hasErrors())
            return ("order/edit");

        delivery.setStatus(listStatus);
        delivery.setUser(employeeRepository.findByName(listUser));

        Iterable<Bag> bag_list = bagRepository.findAll();

        for (Bag bag: bag_list){
            if (bag.getDeliveries() == null){
                delivery.getBags().add(bag);
            }
        }

        deliveryRepository.save(delivery);
        return "redirect:/order";
    }
//
//    @GetMapping("/delete/{id}")
//    public String bagDelete(@PathVariable long id) {
//        deliveryRepository.deleteById(id);
//        return ("redirect:/order");
//    }
}

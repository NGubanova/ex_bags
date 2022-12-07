package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.*;
import com.example.ex_bags.Repository.DeliveryRepository;
import com.example.ex_bags.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("")
    public String deliveryMain(Model model){
        Iterable<Delivery> ListDelivery = deliveryRepository.findAll();
        model.addAttribute("listDelivery", ListDelivery);
                return "delivery/index";
    }

    @GetMapping("/details/{id}")
    public String deliveryDetails(Model model,
                             @PathVariable long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        return ("/delivery/details");
    }

    @GetMapping("/add")
    public String deliveyAdd(Delivery delivery, Model model){
        Iterable<User> users = employeeRepository.findAll();
        model.addAttribute("listUser", users);
        return ("/delivery/add");
    }

    @PostMapping("/add")
    public String deliveryAdd(@Valid Delivery delivery,
                         BindingResult result,
                         @RequestParam String listPayment,
                         @RequestParam String listStatus,
                         @RequestParam String listUser,
                         Model model) {

        Iterable<User> users = employeeRepository.findAll();
        model.addAttribute("listUser", users);

        if (result.hasErrors())
            return ("delivery/add");

        delivery.setPayment(listPayment);
        delivery.setStatus(listStatus);
        delivery.setUser(employeeRepository.findByName(listUser));
        deliveryRepository.save(delivery);
        return "redirect:/delivery";
    }

    @GetMapping("/delete/{id}")
    public String bagDelete(@PathVariable long id) {
        deliveryRepository.deleteById(id);
        return ("redirect:/delivery");
    }
}

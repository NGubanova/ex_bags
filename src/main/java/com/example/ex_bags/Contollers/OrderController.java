package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.*;
import com.example.ex_bags.Repository.BagRepository;
import com.example.ex_bags.Repository.DeliveryRepository;
import com.example.ex_bags.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('COURIER')")
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
        Iterable<Status> values = List.of(Status.values());
        model.addAttribute("status", values);
        return ("/order/details");
    }


    @GetMapping("/edit/{id}")
    public String deliveryEdit(Model model,
                          @PathVariable long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        Iterable<Bag> bags = bagRepository.findAll();
        model.addAttribute("bagList", bags);
        Iterable<Status> values = List.of(Status.values());
        model.addAttribute("status", values);
        return ("/order/edit");
    }
    @PostMapping("/details/{id}")
    public String deliveryEdit(@Valid Delivery delivery,
                              BindingResult result,
                              @RequestParam String listStatus,
                              Model model) {
        Iterable<Bag> bags = bagRepository.findAll();
        model.addAttribute("bagList", bags);
        Iterable<Status> values = List.of(Status.values());
        model.addAttribute("status", values);

        delivery = deliveryRepository.findById(delivery.getId()).orElseThrow();
        delivery.setStatus(listStatus);
        deliveryRepository.save(delivery);
        return "redirect:/order";
    }
}

package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Cell;
import com.example.ex_bags.Repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cell")
public class CellController {

    @Autowired
    CellRepository cellRepository;

    @GetMapping("")
    public String cellMain(Cell cell, Model model){
        Iterable<Cell> ListCell = cellRepository.findAll();
        model.addAttribute("listCell", ListCell);
        return "cell/index";
    }

    @PostMapping("")
    public String cellAdd(@Valid Cell cell,
                          BindingResult result, Model model){
        Iterable<Cell> ListCell = cellRepository.findAll();
        model.addAttribute("listCell", ListCell);

        if(result.hasErrors())
            return ("cell/index");

        cellRepository.save(cell);
        return "redirect:/cell";
    }

    @GetMapping("/edit/{id}")
    public String cellEdit(Model model,
                            @PathVariable long id) {
        Cell cell = cellRepository.findById(id).orElseThrow();
        model.addAttribute("cell", cell);

        return("/cell/edit");
    }

    @PostMapping("/edit/{id}")
    public String cellEdit(@Valid Cell cell,
                            BindingResult result, Model model) {
        if (result.hasErrors())
            return("/cell/edit");

        cellRepository.save(cell);

        return("redirect:/cell");
    }
    @GetMapping("/delete/{id}")
    public String cellDelete(@PathVariable long id) {
        cellRepository.deleteById(id);
        return("redirect:/cell");
    }
}

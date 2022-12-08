package com.example.ex_bags.Contollers;
import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Models.Statistic;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @GetMapping("")
    public String Statistic(Model model)
    {
        return ("statistic");
    }

    @GetMapping("/api/user/")
    public ResponseEntity<ArrayList> userallapi() {

        ArrayList<Statistic> a = new ArrayList<>();
        Iterable<Brand> brands= brandRepository.findAll();
        for (Brand br:brands)
        {
            Statistic stat = new Statistic();
            stat.setBrand(purchaseRepository.countByBrand_Id(br.getId()));
            stat.setName(br.getName());
            a.add(stat);
        }
        return ResponseEntity.ok().body(a);
    }

    @PostMapping("")
    public String backup()
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s --password=%s --add-drop-table --databases %s -r %s",
                "root", "",
                "ex_bags",
                "D:\\JetBrains\\project\\ex_bags\\bec.sql");
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
        if(processComplete==0)
        {

            return("main");}
        else
        { return("statistic");    }
    }
}

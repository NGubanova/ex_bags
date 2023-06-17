package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Brand;
import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Models.Statistic;
import com.example.ex_bags.Models.User;
import com.example.ex_bags.Repository.BrandRepository;
import com.example.ex_bags.Repository.PostRepository;
import com.example.ex_bags.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/api/brand/")
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
//
//
//    @DeleteMapping("/api/users/del/{id}")
//    public ResponseEntity DeleteUserApi(@PathVariable long id) throws Exception {
//
//        User book = userRepositorie.findById(id)
//                .orElseThrow(() -> new Exception());
//        userRepositorie.delete(book);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/api/users/view")
//    public ResponseEntity<List<String>> getAllNotes() {
//        List<String> users = new ArrayList<>();
//        Iterable<User> iu = userRepositorie.findAll();
//        for (User us : iu
//        ) {
//            users.add(us.toString());
//        }
//        return ResponseEntity.ok(users);
//    }
//
//    @PostMapping("/api/provider")
//    public ResponseEntity<Provider> CreateUserApi(@Valid @RequestBody Provider user) {
//        return ResponseEntity.ok(providerRepositorie.save(user));
//    }
}

package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Models.User;
import com.example.ex_bags.Models.Role;
import com.example.ex_bags.Repository.EmployeeRepository;
import com.example.ex_bags.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg(User employee) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(@Valid User employee, BindingResult result,
                      Model model) {

        if (result.hasErrors())
            return ("registration");
        else if (employeeRepository.findByUsername(employee.getUsername()) != null) {
            model.addAttribute("error", "Эл. почта занята!");
            return ("registration");
        }
        employee.setPhone("+7(___)-___-__-__");
        employee.setActive(true);
        employee.setRoles(Collections.singleton(Role.USER));
        Post post = postRepository.findById(2L).orElseThrow();
        employee.setPost(post);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employeeRepository.save(employee);

        return ("redirect:/login");
    }
}

package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.User;
import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Models.Role;
import com.example.ex_bags.Repository.EmployeeRepository;
import com.example.ex_bags.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('HR')")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    PostRepository postRepository;
    @GetMapping("")
    public String employeeMain(Model model){
        Iterable<User> listEmployee = employeeRepository.findAll();
        model.addAttribute("listPeople", listEmployee);
        return "employee/index";
    }

    @GetMapping("/add")
    public String employeeAddView(User employee, Model model){
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("roleName", roles);
        Iterable<Post> ListPost = postRepository.findAll();
        model.addAttribute("listPost", ListPost);
        return "employee/action";
    }

    @PostMapping("/add")
    public String employeeAdd(@Valid User user,
                              BindingResult result, Model model,
                              @RequestParam String[] roles,
                              @RequestParam String listPost){
        Iterable<Role> roleList = List.of(Role.values());
        model.addAttribute("roleName", roleList);
        Iterable<Post> ListPost = postRepository.findAll();
        model.addAttribute("listPost", ListPost);
        Post post = postRepository.findByName(listPost);

        if (result.hasErrors())
            return ("employee/action");


        if (user.getPhone() == ""){
            user.setPhone("+7(___)-___-__-__");
        }

        user.getRoles().clear();
        for(String role: roles){
            user.getRoles().add(Role.valueOf(role));
        }
        user.setPost(post);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        employeeRepository.save(user);
        return "redirect:/employee";
    }
    @GetMapping("/details/{id}")
    public String employeeDetails(Model model,
                             @PathVariable long id) {
        User employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("people", employee);
        return ("/employee/details");
    }

    @GetMapping("/edit/{id}")
    public String employeeEdit(Model model,
                          @PathVariable long id) {
        User user = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("roleName", roles);
        Iterable<Post> ListPost = postRepository.findAll();
        model.addAttribute("listPost", ListPost);

        return("/employee/edit");
    }

    @PostMapping("/edit/{id}")
    public String employeeEdit(@Valid User user,
                               BindingResult result, Model model,
                               @RequestParam Long id,
                               @RequestParam String[] roles,
                               @RequestParam String listPost) {
        user.setPassword(employeeRepository.findById(id).get().getPassword());
        Iterable<Role> roleList = List.of(Role.values());
        model.addAttribute("roleName", roleList);
        Iterable<Post> ListPost = postRepository.findAll();
        model.addAttribute("listPost", ListPost);
        Post post = postRepository.findByName(listPost);

        if (result.hasErrors())
            return ("/employee/edit");

        if (user.getPhone() == ""){
            user.setPhone("+7(___)-___-__-__");
        }

        user.getRoles().clear();
        for(String role: roles){
            user.getRoles().add(Role.valueOf(role));
        }
        user.setPost(post);
        user.setActive(true);
        employeeRepository.save(user);
        return("redirect:/employee/details/" + user.getId());
    }

    @GetMapping("/delete/{id}")
    public String employeeDelete(@PathVariable long id) {
        User employee= employeeRepository.findById(id).orElseThrow();
        employee.setActive(false);
        employeeRepository.save(employee);
        return("redirect:/employee");
    }

    @GetMapping("/filter")
    public String employeeFilter(@RequestParam String searchName,
                            Model model){
        List<User> employee =employeeRepository.findByNameContaining(searchName);
        model.addAttribute("listPeople", employee);
        return "employee/index";
    }
}

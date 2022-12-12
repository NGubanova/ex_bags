package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
@PreAuthorize("hasAuthority('HR')")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("")
    public String postMain(Model model){
        Iterable<Post> ListPost = postRepository.findAll();
        model.addAttribute("listPost", ListPost);
        return "post/index";
    }

    @GetMapping("/add")
    public String postAddView(Post post, Model model){
        return "post/add";
    }

    @PostMapping("/add")
    public String postAdd(@Valid Post post,
                              BindingResult result, Model model){
        if(result.hasErrors())
            return ("post/add");
        else if (postRepository.findByName(post.getName()) != null) {
            model.addAttribute("error", "Должность уже существует");
            return ("post/add");
        }

        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping("/edit/{id}")
    public String postEdit(Model model,
                               @PathVariable long id) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);

        return("/post/edit");
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@Valid Post post,
                           BindingResult result, Model model) {
        if (result.hasErrors())
        return("/post/edit");

        postRepository.save(post);

        return("redirect:/post");
    }
    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable long id) {
        postRepository.deleteById(id);
        return("redirect:/post");
    }
}

package com.example.ex_bags.Contollers;

import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Controller
public class api {


    @Autowired
    PostRepository postRepository;

    @PostMapping("/api/post")
    public ResponseEntity<Post> CreateUserApi(@Valid @RequestBody Post user)
    {
        return ResponseEntity.ok(postRepository.save(user));
    }

    @GetMapping("/api/post/view")
    public ResponseEntity<List<String>> getAllNotes() {
        List<String> users = new ArrayList<>();
        Iterable<Post> iu=postRepository.findAll();
        for (Post us:iu
        ) {
            users.add(us.toString());
        }
        return ResponseEntity.ok(users);
    }
}

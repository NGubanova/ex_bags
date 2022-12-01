package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByName(String name);

}

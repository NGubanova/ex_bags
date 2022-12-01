package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
    Type findByName(String name);
}

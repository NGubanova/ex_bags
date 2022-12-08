package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<User, Long> {

    public List<User> findByNameContaining(String name);
    User findByName(String name);
    User findByUsername(String username);

//    public List<User> findByBag

}

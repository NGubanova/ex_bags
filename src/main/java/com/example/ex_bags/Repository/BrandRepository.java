package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {
Brand findByName(String name);
public List<Brand> findByNameContaining(String mame);
}

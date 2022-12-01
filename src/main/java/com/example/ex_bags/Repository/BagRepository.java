package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Bag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BagRepository extends CrudRepository<Bag, Long> {

    public List<Bag> findByPurchase_BrandName(String brand);
}

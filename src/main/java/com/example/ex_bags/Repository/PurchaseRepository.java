package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
 public List<Purchase> findByModelContaining(String model);
 Purchase findByModel(String model);
 long countByBrand_Id(long id);
}

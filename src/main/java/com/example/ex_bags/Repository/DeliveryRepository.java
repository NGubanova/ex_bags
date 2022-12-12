package com.example.ex_bags.Repository;

import com.example.ex_bags.Models.Delivery;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    public List<Delivery> findDeliveryByUserId(long id);
}

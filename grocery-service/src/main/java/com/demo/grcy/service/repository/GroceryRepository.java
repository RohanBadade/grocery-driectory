package com.demo.grcy.service.repository;



import com.demo.grcy.service.domain.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;


public interface GroceryRepository extends PanacheRepository<Item> {

    Optional<Item> findByName(String name);

    void deleteByItemName(String itemName);

    List<Item> listAllAvailableItems();
}

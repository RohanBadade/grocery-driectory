package com.demo.grcy.service.repository;

import com.demo.grcy.service.domain.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

/**
 * Grocery Repository interface class.
 **/
public interface GroceryRepository extends PanacheRepository<Item> {

    /**
     * Fetches item details based on item name.
     *
     * @param itemName grocery item name
     * @return optional Item domain containing grocery item details
     **/
    Optional<Item> findByName(String itemName);

    /**
     * Deletes grocery item using item name
     *
     * @param itemName grocery item name
     **/
    void deleteByItemName(String itemName);

    /**
     * Fetches list of available item details.
     *
     * @return  list of item details.
     **/
    List<Item> listAllAvailableItems();
}

package com.demo.grcy.service.repository.impl;

import com.demo.grcy.model.enumeration.GroceryStatus;
import com.demo.grcy.service.constants.Errors;
import com.demo.grcy.service.domain.Item;
import com.demo.grcy.service.repository.GroceryRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

/**
 * Grocery Repository Implementation class implementing GroceryRepository interface.
 * **/
@ApplicationScoped
public class GroceryRepositoryImpl implements GroceryRepository {

    /**
     * Fetches item details based on item name.
     *
     * @param itemName grocery item name
     * @return optional Item domain containing grocery item details
     **/
    @Override
    public Optional<Item> findByName(String itemName) {
        try {
            return find("item_name = :name", Parameters.with("name", itemName)).firstResultOptional();
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToRetrieveGroceryItem();
        }
    }

    /**
     * Deletes grocery item using item name
     *
     * @param itemName grocery item name
     **/
    @Override
    public void deleteByItemName(String itemName) {
        try {
            delete("item_name", itemName);
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToDeleteGroceryItem();
        }
    }

    /**
     * Fetches list of available item details.
     *
     * @return  list of item details.
     **/
    @Override
    public List<Item> listAllAvailableItems() {
        try {
            return find("status = :status", Parameters.with("status", GroceryStatus.IN_STOCK)).list();
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToRetrieveGroceryItemList();
        }
    }
}

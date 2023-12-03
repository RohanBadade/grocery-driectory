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

@ApplicationScoped
public class GroceryRepositoryImpl implements GroceryRepository {

    @Override
    public Optional<Item> findByName(String itemName) {
        try {
            return find("item_name = :name", Parameters.with("name", itemName)).firstResultOptional();
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToRetrieveGroceryItem();
        }
    }

    @Override
    public void deleteByItemName(String itemName) {
        try {
            delete("item_name", itemName);
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToDeleteGroceryItem();
        }
    }

    @Override
    public List<Item> listAllAvailableItems() {
        try {
            return find("status = :status", Parameters.with("status", GroceryStatus.IN_STOCK.getValue())).list();
        } catch (PersistenceException persistenceException) {
            throw Errors.failedToRetrieveGroceryItemList();
        }
    }
}

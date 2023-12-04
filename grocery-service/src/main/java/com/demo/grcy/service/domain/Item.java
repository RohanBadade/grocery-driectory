package com.demo.grcy.service.domain;

import com.demo.grcy.model.convertor.GroceryStatusConvertor;
import com.demo.grcy.model.enumeration.GroceryStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Item domain class
 **/
@Entity(name = "items")
@Data
public class Item {

    @Id
    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private int itemPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "no_of_available_items")
    private int numberOfAvailableItems;

    @Column(name = "no_of_purchased_items")
    private int numberOfSoldItems;

    @Column(name = "status")
    @Convert(converter = GroceryStatusConvertor.class)
    private GroceryStatus status;

    /**
     * Updates number of available items after booking grocery items.
     *
     * @param purchasedGoods count of purchased goods
     **/
    public void updateNumberOfAvailableItems(int purchasedGoods) {
        setNumberOfAvailableItems(getNumberOfAvailableItems() - purchasedGoods);
        if (getNumberOfAvailableItems() == 0) {
            updateOutOfStockStatus();
        }
    }

    /**
     * Updates grocery item status as out of stock.
     **/
    private void updateOutOfStockStatus() {
        setStatus(GroceryStatus.OUT_OF_STOCK);
    }

    /**
     * Updates number of sold items after booking grocery items.
     *
     * @param purchasedGoods count of purchased goods
     **/
    public void updateNumberOfSoldItems(int purchasedGoods) {
        setNumberOfSoldItems(getNumberOfSoldItems() + purchasedGoods);
    }
}

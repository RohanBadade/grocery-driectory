package com.demo.grcy.service.domain;

import com.demo.grcy.model.enumeration.GroceryStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    private GroceryStatus status;

    public void updateNumberOfAvailableItems(int purchasedGoods) {
        setNumberOfAvailableItems(getNumberOfAvailableItems() - purchasedGoods);
    }

    public void updateNumberOfSoldItems(int purchasedGoods) {
        setNumberOfSoldItems(getNumberOfSoldItems() + purchasedGoods);
    }
}

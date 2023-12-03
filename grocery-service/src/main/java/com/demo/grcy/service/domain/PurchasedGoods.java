package com.demo.grcy.service.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "purchased_goods")
@Data
public class PurchasedGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName="test_sequence", name="1")
    private long id;

    @Column(name = "receipt_id")
    private UUID receiptId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "no_of_purchased_units")
    private int numberOfPurchasedUnits;

    @Column(name = "total_price")
    private int totalPrice;
}

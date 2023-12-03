package com.demo.grcy.service.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "booking_details")
@Data
public class BookingDetails {

    @Id
    @Column(name = "receipt_id")
    private UUID receiptId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "bill_amount")
    private int billAmount;

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "receipt_id", referencedColumnName = "receipt_id")
    private List<PurchasedGoods> purchasedGoods;
}

package com.programmingmukesh.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Order extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float subTotal = 0.0f;

    @Column(nullable = false)
    private Float itemDiscount = 0.0f;

    @Column(nullable = false)
    private Float tax = 0.0f;

    @Column(nullable = false)
    private Float shipping = 0.0f;

    @Column(nullable = false)
    private Float total = 0.0f;

    private String promo;

    @Column(nullable = false)
    private Float discount = 0.0f;

    @Column(nullable = false)
    private Float grandTotal = 0.0f;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Transaction> transactions;


    private String remarks;

    @ManyToOne
    @JoinColumn(name = "order_type_id", insertable = false, updatable = false)
    private CommonObject orderType;

    @ManyToOne
    @JoinColumn(name = "order_status_id", insertable = false, updatable = false)
    private CommonObject orderStatus;

    @ManyToOne
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
}

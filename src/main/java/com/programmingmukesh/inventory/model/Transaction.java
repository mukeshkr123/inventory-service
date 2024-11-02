package com.programmingmukesh.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotBlank(message = "Transaction code must not be empty")
    private String transactionCode;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private CommonObject transactionType;

    @ManyToOne
    @JoinColumn(name = "transaction_mode_id", nullable = false)
    private CommonObject transactionMode;

    @ManyToOne
    @JoinColumn(name = "transaction_status_id", nullable = false)
    private CommonObject transactionStatus;

    @Column(columnDefinition = "TEXT")
    private String content;
}

package com.solona.modal;


import com.solona.domain.PaymentMethod;
import com.solona.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.security.PrivilegedAction;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;
    private String paymentLinkId;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order>orders = new HashSet<>();
}

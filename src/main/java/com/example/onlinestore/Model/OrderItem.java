package com.example.onlinestore.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order_id;
    @OneToOne
    @JoinColumn(name = "kombucha_id")
    private Kombucha kombucha_id;
    @OneToOne
    @JoinColumn(name = "price_kombucha")
    private Kombucha price_kombucha;
    @OneToOne
    @JoinColumn(name = "count_kombucha")
    private Kombucha count_kombucha;
}

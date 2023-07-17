package com.example.onlinestore.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client_id;
    private Date date_of_order;
    private Status status;
    private Boolean paid;
}

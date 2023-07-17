package com.example.onlinestore.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    private Long id;
    private String fio;
    private String address;
    private String email;
    private String phone;
}

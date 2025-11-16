package com.banking.billing_service.entities;

import com.banking.billing_service.models.Customer;
import com.banking.billing_service.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date date;
    private Double amount;
    
    // ID du customer (stocké dans la base)
    private Long customerId;
    
    // Customer récupéré via Feign (non stocké en base)
    @Transient
    private Customer customer;
    
    // Liste des IDs de produits
    @ElementCollection
    private List<Long> productIds;
    
    // Produits récupérés via Feign (non stockés en base)
    @Transient
    private List<Product> products;
}

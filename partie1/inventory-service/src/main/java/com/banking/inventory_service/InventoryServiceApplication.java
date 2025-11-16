package com.banking.inventory_service;

import com.banking.inventory_service.entities.Product;
import com.banking.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Laptop HP")
                    .price(5000.0)
                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smartphone Samsung")
                    .price(3000.0)
                    .quantity(25)
                    .build());
            productRepository.save(Product.builder()
                    .name("Imprimante Canon")
                    .price(1200.0)
                    .quantity(15)
                    .build());

            System.out.println("=== Products Loaded ===");
            productRepository.findAll().forEach(p -> 
                System.out.println("Product: " + p.getName() + " - " + p.getPrice() + " DH - Qty: " + p.getQuantity())
            );
        };
    }
}
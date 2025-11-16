package com.banking.billing_service;

import com.banking.billing_service.entities.Invoice;
import com.banking.billing_service.repositories.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceRepository invoiceRepository) {
        return args -> {
            // Créer des factures de test
            invoiceRepository.save(Invoice.builder()
                    .date(new Date())
                    .amount(15000.0)
                    .customerId(1L)  // Mohamed
                    .productIds(Arrays.asList(1L, 2L))  // Laptop + Smartphone
                    .build());
            
            invoiceRepository.save(Invoice.builder()
                    .date(new Date())
                    .amount(6200.0)
                    .customerId(2L)  // Fatima
                    .productIds(Arrays.asList(2L, 3L))  // Smartphone + Imprimante
                    .build());

            System.out.println("=== Invoices Loaded ===");
            invoiceRepository.findAll().forEach(i -> 
                System.out.println("Invoice ID: " + i.getId() + " - Amount: " + i.getAmount())
            );
        };
    }
}

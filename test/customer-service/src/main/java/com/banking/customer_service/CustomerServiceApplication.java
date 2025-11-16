package com.banking.customer_service;

import com.banking.customer_service.entities.Customer;
import com.banking.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Mohamed")
                    .email("mohamed@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Fatima")
                    .email("fatima@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Ahmed")
                    .email("ahmed@gmail.com")
                    .build());

            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
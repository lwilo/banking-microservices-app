package com.banking.billing_service.clients;

import com.banking.billing_service.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable Long id);
    
    @GetMapping("/api/products")
    List<Product> getAllProducts();
}

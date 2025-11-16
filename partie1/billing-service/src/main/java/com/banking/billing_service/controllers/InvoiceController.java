package com.banking.billing_service.controllers;

import com.banking.billing_service.clients.CustomerRestClient;
import com.banking.billing_service.clients.ProductRestClient;
import com.banking.billing_service.entities.Invoice;
import com.banking.billing_service.models.Customer;
import com.banking.billing_service.models.Product;
import com.banking.billing_service.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private CustomerRestClient customerRestClient;
    
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        
        // Pour chaque facture, récupérer le client et les produits
        invoices.forEach(invoice -> {
            Customer customer = customerRestClient.getCustomerById(invoice.getCustomerId());
            invoice.setCustomer(customer);
            
            List<Product> products = invoice.getProductIds().stream()
                    .map(productId -> productRestClient.getProductById(productId))
                    .collect(Collectors.toList());
            invoice.setProducts(products);
        });
        
        return invoices;
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        // Récupérer le client
        Customer customer = customerRestClient.getCustomerById(invoice.getCustomerId());
        invoice.setCustomer(customer);
        
        // Récupérer les produits
        List<Product> products = invoice.getProductIds().stream()
                .map(productId -> productRestClient.getProductById(productId))
                .collect(Collectors.toList());
        invoice.setProducts(products);
        
        return invoice;
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}

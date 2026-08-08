package cursoSpringBoot.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

import cursoSpringBoot.domain.Customer;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "John Doe", "johndoe", "password123"),
            new Customer(2, "Jane Smith", "janesmith", "password456"),
            new Customer(3, "Bob Johnson", "bobjohnson", "password789")));

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }
}

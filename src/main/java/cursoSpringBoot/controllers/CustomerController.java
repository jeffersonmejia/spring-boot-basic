package cursoSpringBoot.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

import cursoSpringBoot.domain.Customer;

@RestController
public class CustomerController {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "John", "johndoe", "password123"),
            new Customer(2, "Jane", "janesmith", "password456"),
            new Customer(3, "Bob", "bobjohnson", "password789")));

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/customers/{username}")
    public Customer GetCliente(@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return customer;
            }
        }
        return null;
    }
}

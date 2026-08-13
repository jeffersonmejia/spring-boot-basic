package cursoSpringBoot.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import cursoSpringBoot.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "John", "johndoe", "password123"),
            new Customer(2, "Jane", "janesmith", "password456"),
            new Customer(3, "Bob", "bobjohnson", "password789")));

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> GetCliente(@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontro un cliente con el username: " + username);
    }

    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer) {
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se pudo actualizar: no existe un cliente con el ID " + customer.getID());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        boolean removed = customers.removeIf(customer -> customer.getID() == id);
        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se pudo eliminar: no existe un cliente con el ID " + id);
    }

    @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }
                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }
                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se pudo actualizar: no existe un cliente con el ID " + customer.getID());
    }
}

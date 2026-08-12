package cursoSpringBoot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductsServiceImpl productsService = new ProductsServiceImpl();

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productsService.getProducts();
        return ResponseEntity.ok(products);
    }
}

package cursoSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cursoSpringBoot.configurations.ExternalizedConfiguration;
import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    // @Qualifier("jsonResourceService")
    @Lazy
    private ProductsService productsService;

    @Autowired
    private ExternalizedConfiguration externalizedConfiguration;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productsService.getProducts();
        System.out.println("Externalized Configuration: " + externalizedConfiguration.toString());
        return ResponseEntity.ok(products);
    }
}

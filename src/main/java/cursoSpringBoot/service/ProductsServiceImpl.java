package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "service.products", havingValue = "list")
public class ProductsServiceImpl implements ProductsService {

    List<Product> Products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", 10.99, 100),
            new Product(2, "Tablet", 19.99, 50),
            new Product(3, "Smartphone", 5.99, 200),
            new Product(4, "Smartwatch", 15.99, 75),
            new Product(5, "Headphones", 7.99, 150)));

    @Override
    public List<Product> getProducts() {
        return Products;
    }

}

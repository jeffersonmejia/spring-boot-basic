package cursoSpringBoot.service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cursoSpringBoot.domain.Product;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Primary /* ESTRATEGIA #1 */
@Service /* ("jsonResourceService") ESTRATEGIA #2 */
@ConditionalOnProperty(name = "service.products", havingValue = "json") /* ESTRATEGIA #3 */
@Lazy /* ESTRATEGIA #4 */
public class ProductsServiceJSONImpl implements ProductsService {

    public ProductsServiceJSONImpl() {
        System.out.println("Instancia de ProductsServiceJSONImpl");
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products;

        try {
            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {
                            });
            return products;
        } catch (JacksonException e) {
            throw new RuntimeException("Error al leer el archivo JSON de productos", e);
        }
    }

}

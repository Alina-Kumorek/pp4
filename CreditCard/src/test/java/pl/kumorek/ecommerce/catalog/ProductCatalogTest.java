package pl.kumorek.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsToListAvailableProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assertThat(products).hasSize(0);
    }

    @Test
    void itAllowsToAddProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.createProduct("Lego set 8084", "niece one");

        List<Product> products = catalog.allProducts();

        assertThat(products).hasSize(1);
    }

    @Test
    void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.createProduct("Lego set 8084", "niece one");

        Product loaded = catalog.getProductDSetails(id);

        assertThat(loaded.getName()).isEqualTo("Lego set 8084");
    }

    @Test
    void itChangesPriceForProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.createProduct("Lego set 8084", "niece one");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductDSetails(id);

        assertThat(loaded.getPrice()).isEqualTo(BigDecimal.valueOf(10.10));
    }

    private ProductCatalog thereIsProductCatalog() {return new ProductCatalog();}
}

package pl.kumorek.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProductCatalog {
    public List<Product> allProducts() {
    return Collections.emptyList();
    }

    public String createProduct(String productName, String productDescription) {
        return productName;
    }

    public Product getProductDSetails(String id) {
        return null;
    }

    public void changePrice(String id, BigDecimal bigDecimal) {
    }
}

package pl.kumorek.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ProductCatalog {
    private ArrayList<Product> products;

    public List<Product> allProducts() {
        products = new ArrayList<>();
        return products;
    }

    public String createProduct(String productName, String productDescription) {
        var myProduct = new Product(UUID.randomUUID(), productName, productDescription);

        products.add(myProduct);
        return myProduct.getId();
    }

    public Product getProductDSetails(String id) {
        return null;
    }

    public void changePrice(String id, BigDecimal bigDecimal) {
    }

    // Uzupełnić
    // Biznesowa-Techniczna
}

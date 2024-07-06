package pl.kumorek.ecommerce.catalog;

import java.util.Collections;
import java.util.List;

public class SQLProductStorage implements ProductStorage {


    public SQLProductStorage() {

    }

    @Override
    public List<Product> allProducts() {
        return Collections.emptyList();
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product getProductBy(String id) {
        return new Product();
    }
}

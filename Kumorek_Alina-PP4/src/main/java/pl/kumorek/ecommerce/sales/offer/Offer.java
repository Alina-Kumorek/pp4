package pl.kumorek.ecommerce.sales.offer;

import pl.kumorek.ecommerce.sales.payu.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Offer {
    private final BigDecimal total;
    private final int itemsCount;
    private final List<OfferProduct> products;

    public Offer(BigDecimal total, int itemsCount, List<OfferProduct> products) {
        this.total = total;
        this.itemsCount = itemsCount;
        this.products = products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public List<OfferProduct> getOfferProducts() {
        return products;
    }

    public List<Product> getProducts() {
        //converts form OfferProducts(that have an id) to Products from PayU part
        ArrayList<Product> items = new ArrayList<Product>();

        for (OfferProduct p: products) {
            Product item = new Product();
            item.setName(p.getName()).setUnitPrice(p.getUnitPrice()).setQuantity(p.getQuantity());
        }

        return items;
    }

}

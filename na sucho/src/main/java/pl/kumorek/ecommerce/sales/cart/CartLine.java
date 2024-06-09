package pl.kumorek.ecommerce.sales.cart;

public class CartLine {
    private String productId;
    private Integer qty;

    public CartLine(String productId, Integer qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return qty;
    }
}

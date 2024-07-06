package pl.kumorek.ecommerce.sales.offer;

import pl.kumorek.ecommerce.catalog.ProductCatalog;
import pl.kumorek.ecommerce.sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferCalculator {
    private static final int ITEM_COUNT_DISCOUNT = 5; // every 5th item is free
    private static final int TOTAL_DISCOUNT = 100; // total 100 PLN (-> 10 PLN Discount)
    private static final BigDecimal TOTAL_DISCOUNT_AMOUNT = BigDecimal.valueOf(10); // 10 PLN Discount (for every 100 PLN total)

    ProductCatalog productCatalog;

    public OfferCalculator(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Offer calculate(List<CartLine> cartLines) {
        BigDecimal total = BigDecimal.ZERO;
        int itemsCount = 0;
        ArrayList<OfferProduct> products = new ArrayList<OfferProduct>();

        for (CartLine line: cartLines) {
            int lineCount = line.getQuantity();
            BigDecimal linePrice = productCatalog.getProductBy(line.getProductId()).getPrice();

            OfferProduct product = new OfferProduct();
            product
                    .setName(productCatalog.getProductBy(line.getProductId()).getName())
                    .setQuantity(lineCount)
                    .setUnitPrice(getAsGrosze(linePrice))
                    .setId(line.getProductId());
            products.add(product);

            int lineCountDiscounted = lineCount - (lineCount / ITEM_COUNT_DISCOUNT);
            BigDecimal lineCost = BigDecimal
                    .valueOf(lineCountDiscounted)
                    .multiply(linePrice);

            total = total.add(lineCost);
            itemsCount += lineCount;
        }

        BigDecimal totalDiscount = TOTAL_DISCOUNT_AMOUNT
                .multiply(BigDecimal.valueOf(total.intValue() / TOTAL_DISCOUNT));

        total = total.subtract(totalDiscount);

        return new Offer(total, itemsCount, products);
    }

    private static Integer getAsGrosze(BigDecimal money) {
        return money.multiply(BigDecimal.valueOf(100)).intValueExact();
    }

}

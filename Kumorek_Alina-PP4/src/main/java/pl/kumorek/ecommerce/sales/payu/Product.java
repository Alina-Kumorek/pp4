package pl.kumorek.ecommerce.sales.payu;

//     "products": [
//         {
//             "name": "Wireless Mouse for Laptop",
//             "unitPrice": "15000",
//             "quantity": "1"
//         },
//         {
//             "name": "HDMI cable",
//             "unitPrice": "6000",
//             "quantity": "1"
//         }
//     ]

public class Product {
    String name;
    Integer unitPrice;
    Integer quantity;

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Product setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}

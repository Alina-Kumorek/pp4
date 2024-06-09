package main.java.pl.kumorek.ecommerce.sales.payu;

// {
//     "notifyUrl": "https://your.eshop.com/notify",
//     "customerIp": "127.0.0.1",
//     "merchantPosId": "300746",
//     "description": "RTV market",
//     "currencyCode": "PLN",
//     "totalAmount": "21000",
//     "extOrderId":"2uikc6gjd99b4lxc75ip4k",
//     "buyer": {
//         "email": "john.doe@example.com",
//         "phone": "654111654",
//         "firstName": "John",
//         "lastName": "Doe",
//         "language": "pl"
//     },
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
// }

public class OrderCreateRequest {
    String notifyUrl;
    String customerIp;
    String merchantPostId;
    String description;
    String currencyCode;
    

}
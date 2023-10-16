package pl.myecommerce.payu;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class PayUTest {
    @Test
    void creattingNewPaymentOrder(){
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private PayU thereIsPayU() {

        return new PayU(PayUApiCredentials.sandbox(),new RestTemplate());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {

        OrderCreateRequest request = new OrderCreateRequest();
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300736")
                .setDescription("Example phone shop")
                .setCurrencyCode("PLN")
                .setTotalAmount(1700)
                .setBuyer(new Buyer()
                        .setEmail("norbert.adamkowski@example.com")
                        .setPhone("987654321")
                        .setFirstName("Norbert")
                        .setLastName("Adamkowski")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("repair")
                                .setUnitPrice(1700)
                                .setQuantity(1)
                ));
        return request;
//        curl -X POST https://secure.snd.payu.com/api/v2_1/orders \
//        -H "Content-Type: application/json" \
//        -H "Authorization: Bearer d9a4536e-62ba-4f60-8017-6053211d3f47" \
//        -d '{
//        "notifyUrl": "https://your.eshop.com/notify",
//                "customerIp": "127.0.0.1",
//                "merchantPosId": "300746",
//                "description": "RTV market",
//                "currencyCode": "PLN",
//                "totalAmount": "21000",
//                "buyer": {
//            "email": "john.doe@example.com",
//                    "phone": "654111654",
//                    "firstName": "John",
//                    "lastName": "Doe",
//                    "language": "pl"
//        },
//        "products": [
//        {
//            "name": "Wireless Mouse for Laptop",
//                "unitPrice": "15000",
//                "quantity": "1"
//        },
//        {
//            "name": "HDMI cable",
//                "unitPrice": "6000",
//                "quantity": "1"
//        }
//        ]
//    }'
    }


}

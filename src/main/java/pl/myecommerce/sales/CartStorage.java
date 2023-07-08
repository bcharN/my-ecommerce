package pl.myecommerce.sales;

import java.util.HashMap;
import java.util.Optional;

public class CartStorage {
    private HashMap<String,Cart> customerCarts = new HashMap<String,Cart>();

    public Cart getCustomerCart(String customerId) {
        if (customerCarts.containsKey(customerId)) {
            return customerCarts.get(customerId);
        }
        customerCarts.put(customerId,Cart.empty());
        return customerCarts.get(customerId);
    }


}

package pl.myecommerce.sales.cart;

import pl.myecommerce.sales.cart.CartItem;
import pl.myecommerce.sales.cart.Cart;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CartStorage {
    private final ConcurrentHashMap<String,Cart> carts;

    public CartStorage() {
        carts = new ConcurrentHashMap<>();
    }

    public Optional<Cart> loadCart(String customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }
    public void addForCustomer(String customerId, Cart cart){
        carts.put(customerId,cart);
    }



}

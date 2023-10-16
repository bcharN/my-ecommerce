package pl.myecommerce.sales.cart;

import pl.myecommerce.sales.cart.CartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
//    private ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
    private final HashMap<String ,Integer> productsQuantities;

    public Cart() {
        this.productsQuantities = new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void add(String product) {
        if(!isInCart(product)){
            putIntoCart(product);
        }
        else{
            increaseProductQuantity(product);
        }
    }

    private void increaseProductQuantity(String product) {
        productsQuantities.put(product, productsQuantities.get(product)+1);
    }

    private void putIntoCart(String product) {
        productsQuantities.put(product,1);
    }

    private boolean isInCart(String product) {
        return productsQuantities.containsKey(product);
    }

    public int getItemsCount() {
        return productsQuantities.values().size();
    }

    public boolean isEmpty() {
        return productsQuantities.values().isEmpty();
    }
    public List<CartItem> getCartItems(){
        return productsQuantities
                .entrySet()
                .stream()
                .map(es->new CartItem(es.getKey(),es.getValue()))
                .collect(Collectors.toList());
    }
}

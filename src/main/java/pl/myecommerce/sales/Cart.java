package pl.myecommerce.sales;

import pl.myecommerce.productcatalog.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();

    public static Cart empty() {
        return new Cart();
    }

    public void add(ProductDetails product) {
        products.add(product);
    }

    public int size() {
        return products.size();
    }
}

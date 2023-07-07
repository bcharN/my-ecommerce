package pl.myecommerce.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    private final HashMap<String, Product> products;

    public HashMapProductStorage(){
        this.products = new HashMap<>();
    }
    @Override
    public void add(Product product) {
        products.put(String.valueOf(product.getId()),product);
    }

    @Override
    public List<Product> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(Product::isOnline)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> allProducts() {
        return products.values()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Product> loadById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }
}

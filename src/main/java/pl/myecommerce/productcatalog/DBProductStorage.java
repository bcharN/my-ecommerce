package pl.myecommerce.productcatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBProductStorage implements ProductStorage {
    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public Optional<Product> loadById(String productId) {
        return null;
    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }
}

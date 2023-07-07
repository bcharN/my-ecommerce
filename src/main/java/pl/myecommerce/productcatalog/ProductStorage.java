package pl.myecommerce.productcatalog;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {
    void add(Product product);

    List<Product> allPublishedProducts();

    List<Product> allProducts();

    Optional<Product> loadById(String productId);
}

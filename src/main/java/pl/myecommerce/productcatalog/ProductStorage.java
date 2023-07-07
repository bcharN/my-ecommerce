package pl.myecommerce.productcatalog;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {
    void save(Product product);

    List<Product> allPublishedProducts();

    List<Product> allAvailableProducts();

    Optional<Product> load(String productId);
}

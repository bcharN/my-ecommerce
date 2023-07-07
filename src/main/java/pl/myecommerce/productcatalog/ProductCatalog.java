package pl.myecommerce.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductCatalog {
    HashMapProductStorage storage;


    public ProductCatalog(HashMapProductStorage storage) {
        return new
    }

    public String createProduct(String name) {
        Product product = new Product(UUID.randomUUID(),name);
        storage.save(product);
        return String.valueOf(product.getId());
    }

    public List<Product> allAvailableProducts() {
        return storage.allAvailableProducts();

    }

    public List<Product> allPublishedProducts() {
        return storage.allPublishedProducts();
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        Product product = storage.load(productId)
                        .orElseThrow(()->new ProductDoesNotExistException());
        product.changePrice(newPrice);
    }

    public Optional<Product> findProduct(String productId) {
        return storage.load(productId);
    }

    public void publish(String productId) {
        Product product = storage.load(productId)
                .orElseThrow(()->new ProductDoesNotExistException());

        if(product.getPrice() == null){
            throw new CantPublishProductException();
        }
        product.publish();
    }
}

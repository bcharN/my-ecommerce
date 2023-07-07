package pl.myecommerce.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductCatalog {
    private ProductStorage storage;


    public ProductCatalog(ProductStorage storage) {
        this.storage = storage;
    }

    public String addProduct(String name, String desc) {
        Product product = new Product(UUID.randomUUID(),name,desc);
        storage.add(product);
        return String.valueOf(product.getId());
    }

    public List<Product> allProducts() {
        return storage.allProducts();

    }

    public List<Product> allPublishedProducts() {
        return storage.allPublishedProducts();
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        Product product = storage.loadById(productId)
                        .orElseThrow(()->new ProductDoesNotExistException());
        product.changePrice(newPrice);
    }


    public void assignImage(String productId, String imageKey) {
        Product product = storage.loadById(productId)
                .orElseThrow(()->new ProductDoesNotExistException());

        product.setImage(imageKey);
    }
    public Optional<Product> findProduct(String productId) {
        return storage.loadById(productId);
    }

    public void publishProduct(String productId) {
        Product product = storage.loadById(productId)
                .orElseThrow(()->new ProductDoesNotExistException());

        if(product.getPrice() == null){
            throw new CantPublishProductException();
        }
        product.setOnline(true);
    }

    public Product loadById(String productId) {
        return storage.loadById(productId).orElseThrow(()->new ProductDoesNotExistException());
    }

}

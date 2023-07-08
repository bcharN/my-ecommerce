package pl.myecommerce.sales;

import pl.myecommerce.productcatalog.Product;
import pl.myecommerce.productcatalog.ProductCatalog;

import java.util.Optional;

public class ProductDetailsProvider {
    private ProductCatalog productCatalog;

    public ProductDetailsProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public ProductDetails load(String productId){
        Product product = productCatalog.loadById(productId);
        ProductDetails productDetails = new ProductDetails();
        productDetails
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDesc())
                .setPrice(product.getPrice())
                .setImage(product.getImage());
        return productDetails;
    }
}

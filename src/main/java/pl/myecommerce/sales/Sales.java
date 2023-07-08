package pl.myecommerce.sales;

import pl.myecommerce.productcatalog.HashMapProductStorage;
import pl.myecommerce.productcatalog.ProductCatalog;

import java.util.Optional;

public class Sales {

    private ProductCatalog productCatalog =new ProductCatalog(new HashMapProductStorage());
    private CartStorage cartStorage= new CartStorage();
    private ProductDetailsProvider productDetailsProvider= new ProductDetailsProvider(productCatalog);

    public Sales(){//CartStorage cartStorage, ProductCatalog productCatalog, ProductDetailsProvider productDetailsProvider) {
        //this.cartStorage = cartStorage;
        //this.productDetailsProvider = productDetailsProvider;
        //this.productCatalog = productCatalog;
    }
    public ProductCatalog getProductCatalog(){
        return this.productCatalog;
    }
    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId);
        ProductDetails product = loadProductDetails(productId).orElseThrow(()->new NoSuchProductException());
        customerCart.add(product);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return Optional.of(productDetailsProvider.load(productId))  ;
    }

    public Cart loadCartForCustomer(String customerId) {
        return cartStorage.getCustomerCart(customerId);
    }
}

package pl.myecommerce.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {
    @Test
    void itAllowsToAddProduct(){
        //A
        ProductCatalog catalog = thereIsProductCatalog();

        //A
        String productIs = catalog.addProduct("Lego Set","nice one");

        //A
        List<Product> products = catalog.allProducts();
        List<Product> publishedProducts = catalog.allPublishedProducts();
        assert products.size() == 1;
        assert publishedProducts.size() == 0;

    }



    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("Lego Set","nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        Product loaded = catalog.findProduct(productId)
                .get();
        assert loaded.getPrice().equals(BigDecimal.valueOf(20.20));

    }

    @Test
    void itDenyPublicationWithoutPriceAndAttributes(){
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("Lego Set","nice one");

        assertThrows(CantPublishProductException.class,()->catalog.publish(productId));

    }
    @Test
    void itAllowsProductPublication(){
        //AAA
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("Lego Set","nice one");
        catalog.changePrice(productId,BigDecimal.valueOf(20.30));
        catalog.publish(productId);

        List<Product> published = catalog.allPublishedProducts();
        assert published.size() == 1;

    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new HashMapProductStorage());
    }

}

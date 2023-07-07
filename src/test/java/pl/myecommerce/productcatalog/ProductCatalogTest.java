package pl.myecommerce.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {
    @Test
    void itAllowsToListMyProducts() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        List<Product> products = catalog.allProducts();
        //Assert
        assertListIsEmpty(products);
    }

    private void assertListIsEmpty(List<Product> products) {
        assert 0 == products.size();
    }

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
    void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("lego set 8083", "nice one");

        Product loadedProduct = catalog.loadById(productId);
        assert loadedProduct.getId().equals(productId);
        assert loadedProduct.getName().equals("lego set 8083");

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

        assertThrows(CantPublishProductException.class,()->catalog.publishProduct(productId));

    }
    @Test
    void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        catalog.assignImage(productId, "foo/boo/nice_image.jpeg");

        Product loadedProduct = catalog.loadById(productId);
        assertEquals("foo/boo/nice_image.jpeg", loadedProduct.getImage());
    }
    @Test
    void itAllowsProductPublication(){
        //AAA
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("Lego Set","nice one");
        catalog.changePrice(productId,BigDecimal.valueOf(20.30));
        catalog.publishProduct(productId);

        List<Product> published = catalog.allPublishedProducts();
        assert published.size() == 1;

    }
    @Test
    void publicationIsPossibleWhenPriceAndImageAreDefined() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        assertThrows(
                CantPublishProductException.class,
                () -> catalog.publishProduct(productId)
        );


    }
    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new HashMapProductStorage());
    }

}

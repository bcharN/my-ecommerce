package pl.myecommerce.sales;

import org.junit.jupiter.api.Test;
import pl.myecommerce.mock.FakeBookFactory;
import pl.myecommerce.productcatalog.HashMapProductStorage;
import pl.myecommerce.productcatalog.ProductCatalog;

public class CollectingProductsTest {
    @Test
    void itAllowsToAddProductToCart(){
        //A
        Sales sales = thereIsSalesModule();

        String product1 = getProduct(sales);
        String product2 = getProduct(sales);

        String customerId = thereIsCustomer("Franek");

        //A
        sales.addToCart(customerId,product1);
        sales.addToCart(customerId,product2);

        //A
        assertThereIsXProductsInCart(sales,2,customerId);
    }

    private static String getProduct(Sales sales) {
        return sales.getProductCatalog().addProduct(FakeBookFactory.getTitle(), FakeBookFactory.getDesc());
    }

    private void assertThereIsXProductsInCart(Sales sales,int numberOfItemsInCart, String customerId) {
        Cart cart = sales.loadCartForCustomer(customerId);
        int size = cart.size();
        assert size == numberOfItemsInCart;
    }

    private String thereIsCustomer(String customer) {
        return customer;
    }

    private Sales thereIsSalesModule() {
        return new Sales();
    }

}

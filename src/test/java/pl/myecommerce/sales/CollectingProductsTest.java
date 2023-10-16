package pl.myecommerce.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mock.mock.FakeDataFactory;
import pl.myecommerce.sales.cart.Cart;
import pl.myecommerce.sales.cart.CartStorage;
import pl.myecommerce.sales.offering.Offer;
import pl.myecommerce.sales.offering.OfferCalculator;
import pl.myecommerce.sales.offering.OfferLine;
import pl.myecommerce.sales.productdetails.InMemoryProductDetailsProvider;
import pl.myecommerce.sales.productdetails.ProductDetails;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.UUID;

public class CollectingProductsTest {

    private CartStorage cartStorage;
    private InMemoryProductDetailsProvider productDetails;

    @BeforeEach
    void setUp(){
        this.cartStorage = new CartStorage();
        this.productDetails = new InMemoryProductDetailsProvider();
    }

    @Test
    void itAllowsToAddProductsToCart(){
        //A
        Sales sales = thereIsSalesModule();

        String product1 = thereIsProduct(FakeDataFactory.getTitle(), FakeDataFactory.getPrice());//getProduct(sales);
        String product2 = thereIsProduct(FakeDataFactory.getTitle(), FakeDataFactory.getPrice());//getProduct(sales);

        String customerId = thereIsCustomer("Franek");

        //A
        sales.addToCart(customerId,product1);
        sales.addToCart(customerId,product2);

        //A
        assertThereIsXProductsInCart(2,customerId);
    }



    @Test
    public void itAllowsToAddProductToCartByMultipleCustomers(){
        //A
        Sales sales = thereIsSalesModule();

        String product1 = thereIsProduct();
        String product2 = thereIsProduct();

        String customerID1 = thereIsCustomer();
        String customerID2 = thereIsCustomer();

        //A
        sales.addToCart(customerID1,product1);
        sales.addToCart(customerID1,product2);

        sales.addToCart(customerID2,product2);

        //A
        assertThereIsXProductsInCart(2,customerID1);
        assertThereIsXProductsInCart(1,customerID2);
    }

   @Test
   public void itAllowsGenerateCurrentOfferBasedOnCurrentCart(){
        //A
        Sales sales = thereIsSalesModule();

        String name1 = FakeDataFactory.getTitle();
        String name2 = FakeDataFactory.getTitle();
        BigDecimal price1 = BigDecimal.valueOf(200);//FakeDataFactory.getPrice();
        BigDecimal price2 = BigDecimal.valueOf(300);;//FakeDataFactory.getPrice();

        String productID1 = thereIsProduct(name1,price1);
        String productID2 = thereIsProduct(name2,price2);

        String customerID = thereIsCustomer();
        //A
        sales.addToCart(customerID, productID1);
        sales.addToCart(customerID, productID1);
        sales.addToCart(customerID, productID2);
        Offer offer = sales.getCurrentOffer(customerID);
        //A
        assertThat(offer.getTotal()).isEqualByComparingTo(price1.add(price1).add(price2).subtract(BigDecimal.valueOf(50)));
        assertThat(offer.getOrderLines()).hasSize(2);
        assertThat(offer.getOrderLines())
                .filteredOn(orderLine -> orderLine.getProductId().equals(productID1))
                .extracting(OfferLine::getQuantity)
                .first()
                .isEqualTo(2);
   }

    private String thereIsProduct(){
        String id = UUID.randomUUID().toString();
        productDetails.add(new ProductDetails(id, FakeDataFactory.getTitle(), FakeDataFactory.getPrice()));
        return id;
    }
    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        productDetails.add(new ProductDetails(id, name , price));
        return id;
    }
    //private static String getProduct(Sales sales) {
    //    return sales.getProductCatalog().addProduct(FakeBookFactory.getTitle(), FakeBookFactory.getDesc());
    //}

    private void assertThereIsXProductsInCart(int numberOfItemsInCart, String customerId) {
        Cart cart = cartStorage.loadCart(customerId).get();

        assert cart.getItemsCount() == numberOfItemsInCart;
    }

    private String thereIsCustomer() {
        return FakeDataFactory.getName();
    }
    private String thereIsCustomer(String id) {
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetails, new OfferCalculator(productDetails));
    }

}

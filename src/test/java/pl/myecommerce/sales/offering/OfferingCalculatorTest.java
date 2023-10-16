package pl.myecommerce.sales.offering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mock.mock.FakeDataFactory;
import pl.myecommerce.sales.cart.CartItem;
import pl.myecommerce.sales.productdetails.InMemoryProductDetailsProvider;
import pl.myecommerce.sales.productdetails.ProductDetails;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferingCalculatorTest {

    private InMemoryProductDetailsProvider productDetails;
    @BeforeEach
    void setUp(){
        this.productDetails = new InMemoryProductDetailsProvider();
    }
    @Test
    public void itCreateOfferBasedOnCartItems(){
        //AAA
        String product1 = thereIsProduct(BigDecimal.valueOf(20.10));
        String product2 = thereIsProduct(BigDecimal.valueOf(30.40));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1,2),
                new CartItem(product2,1)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems, TotalDiscountPolicy.noDiscount());

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(70.6));


    }

    @Test
    public void itAllowsToApplyTotalDiscount(){
        //AAA
        String product1 = thereIsProduct(BigDecimal.valueOf(100.0));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, 1)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        TotalDiscountPolicy totalDiscount = thereIsTotalDiscount(BigDecimal.valueOf(50),BigDecimal.valueOf(10));

        Offer offer = offerCalculator.calculateOffer(cartItems, totalDiscount);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(90.0));


    }

    @ParameterizedTest
    @MethodSource("getTotalDiscounts")
    public void itAllowsToApplyTotalDiscount(TotalDiscountPolicy policy, BigDecimal result){
        String product = thereIsProduct(BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product,1)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems,policy);

        assertThat(offer.getTotal()).isEqualTo(result);
        
    }
    
    public static Stream<Arguments> getTotalDiscounts(){
        return Stream.of(
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(50),BigDecimal.valueOf(10)),BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(50),BigDecimal.valueOf(10)),BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(50),BigDecimal.valueOf(10)),BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(50),BigDecimal.valueOf(10)),BigDecimal.valueOf(90))
        );
    }
    
    @ParameterizedTest
    @MethodSource("getLinesDiscounts")
    public void itAllowsToApplyDicountPerLine(EveryNthItemDiscountPolicy policy, Integer itemsCount, BigDecimal result){
        String product = thereIsProduct(BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product,itemsCount)
        );
        
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        
        Offer offer = offerCalculator.calculateOffer(cartItems, TotalDiscountPolicy.noDiscount(), policy);
        
        assertThat(offer.getTotal()).isEqualTo(result);
        
    }
    
    public static Stream<Arguments> getLinesDiscounts(){
        return Stream.of(
                Arguments.of(thereIsPerLineDiscount(5),5,BigDecimal.valueOf(400)),
                Arguments.of(thereIsPerLineDiscount(5),6,BigDecimal.valueOf(500)),
                Arguments.of(thereIsPerLineDiscount(5),4,BigDecimal.valueOf(400))
        );
    }

    private static EveryNthItemDiscountPolicy thereIsPerLineDiscount(int quantityThreshold) {
        return new EveryNthItemDiscountPolicy(quantityThreshold);
    }


    private static TotalDiscountPolicy thereIsTotalDiscount(BigDecimal total, BigDecimal discountValue) {
        return new TotalDiscountPolicy(total,discountValue);
    }

    private OfferCalculator thereIsOfferCalculator() {
        return new OfferCalculator(productDetails);
    }

    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        this.productDetails.add(new ProductDetails(id, name, price));
        return id;
    }
    private String thereIsProduct(BigDecimal price) {
        String id = UUID.randomUUID().toString();
        this.productDetails.add(new ProductDetails(id, FakeDataFactory.getName(),price));
        return id;
    }
    private String thereIsProduct() {
        String id = UUID.randomUUID().toString();
        this.productDetails.add(new ProductDetails(id, FakeDataFactory.getName(),FakeDataFactory.getPrice()));
        return id;
    }

}

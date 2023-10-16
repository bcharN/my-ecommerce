package pl.myecommerce.sales.cart;

import org.junit.jupiter.api.Test;
import pl.mock.mock.FakeDataFactory;
import pl.myecommerce.sales.cart.Cart;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class CartTest {
    public static final String PRODUCT_1 = FakeDataFactory.getTitle();
    public static final String PRODUCT_2 = FakeDataFactory.getTitle();

    @Test
    public void newCartIsEmpty(){
        Cart cart = Cart.empty();
        assertThat(cart.isEmpty())
                .isTrue();
    }
    @Test
    public void cartWithProductsIsNotEmpty(){
        //A
        Cart cart =  Cart.empty();
        String productId = thereIsProduct();
        //A
        cart.add(productId);
        //A
        assertThat(cart.isEmpty())
                .isFalse();
    }
    @Test
    public void itExposeProductCount(){
        //A
        Cart cart = Cart.empty();
        String productId = thereIsProduct();
        String productId2 = thereIsProduct();
        //A
        cart.add(productId);
        cart.add(productId2);

        //A
        assertThat(cart.getItemsCount()).isEqualTo(2);
    }

    @Test
    public void itIncreasesSingleProductQuantity(){
        Cart cart = Cart.empty();
        String product = thereIsProduct();

        cart.add(product);
        cart.add(product);

        assertThat(cart.getItemsCount()).isEqualTo(1);
        assertProductWithQuantityInCart(cart,product,2);
    }

    private void assertProductWithQuantityInCart(Cart cart, String product, int expectedQuantity) {
        assertThat(cart.getCartItems())
                .filteredOn(cartItem -> cartItem.getProductId().equals(product))
                .extracting(CartItem::getQuantity)
                .first()
                .isEqualTo(expectedQuantity);

    }

    private String thereIsProduct() {
        return UUID.randomUUID().toString();
    }
}

package pl.myecommerce.sales;

import pl.myecommerce.sales.cart.Cart;
import pl.myecommerce.sales.cart.CartStorage;
import pl.myecommerce.sales.offering.EveryNthItemDiscountPolicy;
import pl.myecommerce.sales.offering.Offer;
import pl.myecommerce.sales.offering.OfferCalculator;
import pl.myecommerce.sales.offering.TotalDiscountPolicy;
import pl.myecommerce.sales.productdetails.NoSuchProductException;
import pl.myecommerce.sales.productdetails.ProductDetails;
import pl.myecommerce.sales.productdetails.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {

    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetails, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetails;
        this.offerCalculator = offerCalculator;
    }
    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId).orElse(Cart.empty());
        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(()->new NoSuchProductException());

        customerCart.add(product.getId());
        cartStorage.addForCustomer(customerId,customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    public Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.loadCart(customerId);
    }

    public Offer getCurrentOffer(String customerID) {
        Cart customerCart = loadCartForCustomer(customerID)
                .orElse(Cart.empty());
        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500),BigDecimal.valueOf(50)),
                new EveryNthItemDiscountPolicy(5)
        );
        return offer;
    }
    public void acceptOffer(){

    }
}

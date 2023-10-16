package pl.myecommerce.sales.offering;

import pl.myecommerce.sales.cart.CartItem;
import pl.myecommerce.sales.productdetails.ProductDetails;
import pl.myecommerce.sales.productdetails.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferCalculator {
    private ProductDetailsProvider productDetailsProvider;
    public OfferCalculator(ProductDetailsProvider productDetailsProvider) {

        this.productDetailsProvider = productDetailsProvider;
    }
    public Offer calculateOffer(List<CartItem> cartItems){
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());
        Offer offer = new Offer(offerItems, calculateTotal(offerItems));
        return offer;
    }

    private BigDecimal calculateTotal(List<OfferLine> offerItems) {
        return offerItems.stream()
                .map(offerLine -> offerLine.getLineTotal())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private OfferLine createOrderLine(CartItem cartItem) {
        ProductDetails details = productDetailsProvider.load(cartItem.getProductId()).get();

        BigDecimal lineTotal = details.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return new OfferLine(
                cartItem.getProductId(),
                details.getName(),
                details.getPrice(),
                cartItem.getQuantity(),
                lineTotal
        );
    }


    public Offer calculateOffer(List<CartItem> cartItems, TotalDiscountPolicy totalDiscount) {
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());
        Offer offer = new Offer(offerItems, calculateTotal(offerItems));
        offer = totalDiscount.apply(offer);
        return offer;

    }
    public Offer calculateOffer(List<CartItem> cartItems, TotalDiscountPolicy totalDiscount, EveryNthItemDiscountPolicy lineDiscount) {
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .map(lineDiscount::apply)
                .collect(Collectors.toList());
        Offer offer = new Offer(offerItems, calculateTotal(offerItems));
        offer = totalDiscount.apply(offer);
        return offer;

    }
}

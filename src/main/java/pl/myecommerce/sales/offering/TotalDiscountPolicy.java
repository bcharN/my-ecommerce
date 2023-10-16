package pl.myecommerce.sales.offering;

import java.math.BigDecimal;

public class TotalDiscountPolicy {

    private final BigDecimal totalTreshold;
    private final BigDecimal discountValue;

    public TotalDiscountPolicy(BigDecimal totalTreshold, BigDecimal discountValue) {
        if (totalTreshold.compareTo(BigDecimal.valueOf(0)) < 0 || discountValue.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new NoSuchDiscountValuesException();
        }
        this.totalTreshold = totalTreshold;
        this.discountValue = discountValue;


    }

    public Offer apply(Offer offer){
        if(!isApplicable(offer)){
            return offer;
        }
        return new Offer(offer.getOrderLines(),offer.getTotal().subtract(this.discountValue));
    }

    private boolean isApplicable(Offer offer) {
        return offer.getTotal().compareTo(this.totalTreshold) > -1;
    }

    public static TotalDiscountPolicy noDiscount() {
        return new TotalDiscountPolicy(BigDecimal.ZERO,BigDecimal.ZERO);
    }

    public BigDecimal getTotalTreshold() {
        return totalTreshold;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }
}

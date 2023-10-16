package pl.myecommerce.sales.offering;

import java.math.BigDecimal;

public class EveryNthItemDiscountPolicy {
    private final Integer quantityTreshold;
    private final Boolean noPolicy;
    public EveryNthItemDiscountPolicy(int quantityThreshold) {
        this.quantityTreshold = quantityThreshold;
        if(quantityThreshold==-1) {this.noPolicy = Boolean.TRUE;}
        else {this.noPolicy = Boolean.FALSE;}
    }

    public OfferLine apply(OfferLine line){
        if(line.getQuantity() <= 0 || this.noPolicy) {
            return line;
        }

        Integer discountedItems = ( line.getQuantity() - ( line.getQuantity() % quantityTreshold )) / quantityTreshold;

        BigDecimal lineTotal = line
                .getUnitPrice()
                .multiply(BigDecimal.valueOf(line.getQuantity()))
                .subtract(BigDecimal
                        .valueOf(discountedItems)
                        .multiply(line.getUnitPrice()));

        return new OfferLine(line.getProductId(), line.getName(), line.getUnitPrice(), line.getQuantity(), lineTotal);
    }


    public Integer getQuantityTreshold() {
        return quantityTreshold;
    }

    public EveryNthItemDiscountPolicy noPolicy(){
        return new EveryNthItemDiscountPolicy(-1);
    }
}

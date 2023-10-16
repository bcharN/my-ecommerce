package pl.myecommerce.sales.offering;

import java.math.BigDecimal;
import java.util.List;

public class Offer {

    private final List<OfferLine> lines;
    private final BigDecimal total;
    public Offer(List<OfferLine> lines, BigDecimal total) {
        this.lines = lines;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getProductCount(){
        return lines.size();
    }
    public List<OfferLine> getOrderLines() {
        return lines;
    }
}

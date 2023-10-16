package pl.myecommerce.sales.productdetails;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDetails {
    private String id;
    private  String name;
    private BigDecimal price;

    public ProductDetails(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

}

package pl.myecommerce.productcatalog;
import java.math.BigDecimal;
import java.util.UUID;

public class Product {


    private final UUID uuid;
    private final String name;
    private BigDecimal price;
    private boolean online;


    public Product(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;

    }
    public UUID getId(){
        return uuid;
    }

    public void changePrice(BigDecimal newPrice){
        this.price = newPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void publish(){
        this.online = true;
    }
    public boolean isOnline(){
        return this.online;
    }
    public String getName() {
        return name;
    }

}

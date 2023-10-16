package pl.myecommerce.productcatalog;
import java.math.BigDecimal;
import java.util.UUID;

public class Product {




    public String getUuid() {
        return uuid.toString();
    }

    private final UUID uuid;
    private final String name;
    private String desc;
    private BigDecimal price;
    private boolean online;
    private String image;


    public Product(UUID uuid, String name, String desc){
        this.uuid = uuid;
        this.name = name;
        this.desc = desc;
    }
    public String getId(){
        return String.valueOf(uuid);
    }

    public void changePrice(BigDecimal newPrice){
        this.price = newPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setOnline(boolean online){
        this.online = online;
    }
    public boolean isOnline(){
        return this.online;
    }
    public String getName() {
        return name;
    }

    public void setImage(String imageKey) {
        this.image = imageKey;
    }

    public String getImage() {
        return this.image;
    }

    public String getDesc() {
        return desc;
    }
}

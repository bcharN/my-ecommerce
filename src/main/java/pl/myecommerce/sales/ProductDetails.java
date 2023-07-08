package pl.myecommerce.sales;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDetails {
    public String Id;
    private  UUID uuid;
    private  String name;
    private String description;
    private BigDecimal price;
    private boolean online;
    private String image;

    public String getId() {
        return null;
    }

    public ProductDetails setId(String id) {
        Id = id;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDetails setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isOnline() {
        return online;
    }

    public ProductDetails setOnline(boolean online) {
        this.online = online;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductDetails setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductDetails setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public ProductDetails setName(String name) {
        this.name = name;
        return this;
    }
}

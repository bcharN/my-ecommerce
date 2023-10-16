package pl.myecommerce.sales.cart;

public class CartItem {
    private String productId;
    private Integer quantity;

    public CartItem(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }


}

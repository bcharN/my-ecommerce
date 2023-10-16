package pl.myecommerce.payu;

public class OrderCreateResponse {
    private String redirectUri;
    private String orderId;
    private String oxtOrderId;

    public OrderCreateResponse() {
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOxtOrderId() {
        return oxtOrderId;
    }

    public OrderCreateResponse setOxtOrderId(String oxtOrderId) {
        this.oxtOrderId = oxtOrderId;
        return this;
    }
}

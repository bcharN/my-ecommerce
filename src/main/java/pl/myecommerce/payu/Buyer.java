package pl.myecommerce.payu;

public class Buyer {
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String language;

    public Buyer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Buyer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Buyer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Buyer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Buyer setLanguage(String language) {
        this.language = language;
        return this;
    }
}

package pl.myecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mock.mock.StorageFakeDataFiller;
import pl.myecommerce.productcatalog.HashMapProductStorage;
import pl.myecommerce.productcatalog.ProductCatalog;
import pl.myecommerce.sales.cart.CartStorage;
import pl.myecommerce.sales.Sales;
import pl.myecommerce.sales.offering.OfferCalculator;
import pl.myecommerce.sales.productdetails.InMemoryProductDetailsProvider;

@SpringBootApplication
public class App{

    public static void main(String[] args){
        SpringApplication.run(App.class,args);

    }
    @Bean
    ProductCatalog createNewProductCatalog(){
        ProductCatalog productCatalog = new ProductCatalog(new HashMapProductStorage());

        return StorageFakeDataFiller.fillWithFakeData(productCatalog,8);
    }
    @Bean
    Sales createSales(){
        InMemoryProductDetailsProvider productDetails = new InMemoryProductDetailsProvider();
        return new Sales(new CartStorage(), productDetails,new OfferCalculator(productDetails));
    }
}
// title desc path price
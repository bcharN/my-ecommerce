package pl.myecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.myecommerce.mock.StorageFakeDataFiller;
import pl.myecommerce.productcatalog.HashMapProductStorage;
import pl.myecommerce.productcatalog.ProductCatalog;

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
}
// title desc path price
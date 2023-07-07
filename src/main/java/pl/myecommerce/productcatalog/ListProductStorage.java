package pl.myecommerce.productcatalog;

import java.util.List;
import java.util.Optional;

public class ListProductStorage implements ProductStorage{
    @Override
    public void save ( Product product){

    }
    @Override
    public List<Product> allAvailableProducts(){
        return null;
    }
    @Override
    public List<Product> allPublishedProducts(){
        return null;
    }
    @Override
    public Optional<Product> load(String productId){
        return Optional.empty();
    }
}

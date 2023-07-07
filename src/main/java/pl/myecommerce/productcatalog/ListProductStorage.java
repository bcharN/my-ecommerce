package pl.myecommerce.productcatalog;

import java.util.List;
import java.util.Optional;

public class ListProductStorage implements ProductStorage{
    @Override
    public void add(Product product){

    }
    @Override
    public List<Product> allProducts(){
        return null;
    }
    @Override
    public List<Product> allPublishedProducts(){
        return null;
    }
    @Override
    public Optional<Product> loadById(String productId){
        return Optional.empty();
    }
}

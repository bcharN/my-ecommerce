package pl.myecommerce.mock;



import pl.myecommerce.productcatalog.ProductCatalog;

import java.util.stream.IntStream;

public class StorageFakeDataFiller {
    public static ProductCatalog fillWithFakeData(ProductCatalog productCatalog, int numberOfBooks){
        IntStream.range(0,numberOfBooks).forEach(n->{
            String productId = productCatalog.addProduct(FakeBookFactory.getTitle(),FakeBookFactory.getDesc());
            productCatalog.assignImage(productId,FakeBookFactory.getImgPath());
            productCatalog.changePrice(productId,FakeBookFactory.getPrice());
            productCatalog.publishProduct(productId);
        });
        return productCatalog;
    }
}

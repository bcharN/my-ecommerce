package pl.mock.mock;



import pl.myecommerce.productcatalog.ProductCatalog;

import java.util.stream.IntStream;

public class StorageFakeDataFiller {
    public static ProductCatalog fillWithFakeData(ProductCatalog productCatalog, int numberOfBooks){
        IntStream.range(0,numberOfBooks).forEach(n->{
            String productId = productCatalog.addProduct(FakeDataFactory.getTitle(), FakeDataFactory.getDesc());
            productCatalog.assignImage(productId, FakeDataFactory.getImgPath());
            productCatalog.changePrice(productId, FakeDataFactory.getPrice());
            productCatalog.publishProduct(productId);
        });
        return productCatalog;
    }
}

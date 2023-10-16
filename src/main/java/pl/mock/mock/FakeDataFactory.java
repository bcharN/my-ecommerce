package pl.mock.mock;

import net.datafaker.Faker;

import java.math.BigDecimal;

public class FakeDataFactory {
    private static Faker faker = new Faker();
    public static String getTitle(){
        return faker.book().title();
    }
    public static String getDesc(){
        return faker.lorem().sentence(4,9);
        //return faker.marketing().buzzwords();
    }
    public static BigDecimal getPrice(){

        return BigDecimal.valueOf(faker.number().randomDouble(2,0,400));
    }
    public static String getImgPath(){
        return ("/images/"+faker.book().title()+".png").replaceAll(" ","_");
    }

    public static String getName() {
        return faker.name().name();
    }
}

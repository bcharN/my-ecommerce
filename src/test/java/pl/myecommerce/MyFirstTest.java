package pl.myecommerce;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class MyFirstTest {
    @Test
    void myFirstTest(){
        assert 2==2;
    }
    @Test
    void mySecondTest(){
        assert 1==1;
    }
    @Test
    void stdTest(){
        //Arange    given
        //Act       when
        //Assert    then

    }

    @Test
    void checkDouble(){
        double d1 = 0.3;
        double d2 = 0.2;
        double result = d1-d2;

    }
    @Test
    void checkFloat(){
        float f1 = 0.3f;
        float f2 = 0.2f;
        float result = f1-f2;

    }
}

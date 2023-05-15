package pl.myecommerce;
import org.junit.jupiter.api.Test;
public class GreeterTest {
    @Test
    void itGreetThePerson(){
        //Arrange Given
        String name = "Kuba";
        Greeter greeter = new Greeter();
        //Act when
        String result = greeter.greet(name);
        // Assert then
        assert result.equals("Hello Kuba");
    }
}

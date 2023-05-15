package pl.myecommerce.creditcard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
public class CreditCardTest {
    @Test
    void itAllowsToAssignInitialCredit(){
        //Arrange
        CreditCard creditCard = new CreditCard("1234-5678");
        // act
        creditCard.assignCredit(BigDecimal.valueOf(1000));
        //Assert
        assert creditCard.getBalance().equals(BigDecimal.valueOf(1000));

    }
    @Test
    void itAllowsToAssignInitialCreditV2(){
        //A
        CreditCard card = new CreditCard("1234-5678");
        //A
        card.assignCredit(BigDecimal.valueOf(5000));
        //A
        assert card.getBalance().equals(BigDecimal.valueOf(5000));
    }

    @Test
    void itDenyCreditLimitBelowThreshold(){
        CreditCard card = new CreditCard("1234-5678");

        try{
            card.assignCredit(BigDecimal.valueOf(30));
            fail("it should throw an exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }

    }
    @Test
    void itDenyCreditBelowThreshold(){
        CreditCard card = new CreditCard("1234-5678");
        assertThrows(CreditBelowThresholdException.class,()-> card.assignCredit(BigDecimal.valueOf(10)));
        assertThrows(CreditBelowThresholdException.class,()-> card.assignCredit(BigDecimal.valueOf(99)));
        assertDoesNotThrow(()->card.assignCredit(BigDecimal.valueOf(101)));

    }
    @Test
    void itDenyToAssignLimitTwice(){
        CreditCard card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(2000));
        assertThrows(CantReassignException.class,()->card.assignCredit(BigDecimal.valueOf(2001)));
    }








}

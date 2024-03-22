package pl.kumorek.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit() {
        //Arrange
        CreditCard card = testCreditCard();
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        //Assert
        assert BigDecimal.valueOf(1000)
                .equals(card.getCredit());
    }

    @Test
    void itAllowsToAssignCreditLimit2() {
        //Arrange
        CreditCard card = testCreditCard();
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(2000));
        //Assert
        assertEquals(BigDecimal.valueOf(2000), card.getCredit(), "Card credit doesn't match.");

    }

    @Test
    void itDenyToChangeAlreadyAssignedCredit() {
        CreditCard card = testCreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        try {
            card.assignCreditLimit(BigDecimal.valueOf(2000));
            fail("Exception shouldn't be thrown");
        } catch (CreditCantBeModifiedException e) {
            assertTrue(true);
        }
    }

    @Test
    void itDenyToChangeAlreadyAssignedCredit2() {
        CreditCard card = testCreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));//lambda
        // python // lambda x: x*2
        // java // (x) -> x*2

        assertThrows(CreditCantBeModifiedException.class,
        () -> card.assignCreditLimit(BigDecimal.valueOf(2000)));
    }

    @Test
    void  itAllowsToPayBills() {
        CreditCard card = testCreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(900));

        assertThrows(NotEnougntFoundsException.class,
        () -> card.withdraw(BigDecimal.valueOf(200)));
    }

   private CreditCard testCreditCard() {
        return new CreditCard("1234-56789");
    }
}

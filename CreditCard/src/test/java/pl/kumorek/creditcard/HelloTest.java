package pl.kumorek.creditcard;

import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    void myFirstTest() {
        //Arrange   // Given
        var a = 2;
        var b = 4;

        //Act       // When
        var result = a + b;

        //Assert    // Then
        assert 6 == result;
    }
}

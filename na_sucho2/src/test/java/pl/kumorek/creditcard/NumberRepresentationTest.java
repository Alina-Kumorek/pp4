package pl.kumorek.creditcard;

import org.junit.jupiter.api.Test;

public class NumberRepresentationTest {

    @Test
    void showDouble() {
        var a = 0.02;
        var b = 0.03;

        var result = b - a;

        System.out.println("Double representation: " + result);
    }
}

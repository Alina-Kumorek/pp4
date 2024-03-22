package pl.kumorek.creditcard;

import java.util.Collections;

public class App {
    public static  void main(String[] args) {
        var name = "Alina";
        var message = String.format("Hello %s", name);

        var names = Collections.singletonList("Alina Kumorek");

        System.out.println(message);
    }
}

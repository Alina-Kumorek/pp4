package pl.kumorek.creditcard;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

public class mvAssertJPlaygroundTest {

    @Test
    void playWithAssertJ() {
        assertThat("Hello World")
                .contains("Hello");

        assertThat(Collections.emptyList())
                .hasSize(0);

        assertThat(Instant.now())
                .isBefore("2023");

        assertThat(new HashMap<>())
                .containsKey("Alina");
    }

}

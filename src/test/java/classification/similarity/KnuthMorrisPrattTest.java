package classification.similarity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnuthMorrisPrattTest {

    private static KnuthMorrisPratt meter;

    @BeforeAll
    static void initialize() {
        meter = new KnuthMorrisPratt();
    }

    @Test
    void measure() {
        double result = meter.measure("test", "test");
        System.out.println(result);
    }

}
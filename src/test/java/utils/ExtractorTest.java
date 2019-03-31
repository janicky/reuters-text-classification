package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {
    private final String[] text = new String[] { "machine", "learning", "based", "systems" };
    private final String[] dictionary = new String[] { "learning", "artificial", "representation", "systems" };

    @Test
    void binaryExtraction() {
        boolean[] binary = Extractor.binaryExtraction(dictionary, text);
//        Assertions
        assertEquals(4, binary.length);
        assertTrue(binary[0]);
        assertFalse(binary[1]);
        assertFalse(binary[2]);
        assertTrue(binary[3]);
        System.out.println(Arrays.toString(binary));
    }

    @Test
    void densityExtraction() {
        double[] density = Extractor.densityExtraction(dictionary, text);
        System.out.println(Arrays.toString(density));
    }
}
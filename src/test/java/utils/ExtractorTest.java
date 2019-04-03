package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {
    private final String[] text = new String[] { "machine", "learning", "based", "systems" };
    private final String[] dictionary = new String[] { "learning", "artificial", "representation", "systems" };

    @Test
    void binaryExtraction() {
        Map<String, Integer> binary = Extractor.binaryExtraction(dictionary, text);
//        Assertions
        assertEquals(4, binary.size());
        assertEquals(1, binary.get("learning").intValue());
        assertEquals(0, binary.get("artificial").intValue());
        assertEquals(0, binary.get("representation").intValue());
        assertEquals(1, binary.get("systems").intValue());
    }

    @Test
    void densityExtraction() {
        double[] density = Extractor.densityExtraction(dictionary, text);
        System.out.println(Arrays.toString(density));
    }
}
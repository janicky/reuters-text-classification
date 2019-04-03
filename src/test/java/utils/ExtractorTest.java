package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {
    private final String[] text = new String[] { "machine", "learning", "based", "systems", "learning" };
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
    void occurrencesExtraction() {
        Map<String, Integer> occurrences = Extractor.occurrencesExtraction(dictionary, text);
//        Assertions
        assertEquals(2, occurrences.get("learning").intValue());
        assertEquals(0, occurrences.get("artificial").intValue());
        assertEquals(0, occurrences.get("representation").intValue());
        assertEquals(1, occurrences.get("systems").intValue());
    }

    @Test
    void densityExtraction() {
        Map<String, Integer> density = Extractor.densityExtraction(dictionary, text);
        assertEquals(40, density.get("learning").intValue());
        assertEquals(0, density.get("artificial").intValue());
        assertEquals(0, density.get("representation").intValue());
        assertEquals(20, density.get("systems").intValue());
    }

    @Test
    void distanceExtraction() {
        Map<String, Integer> distance = Extractor.distanceExtraction(dictionary, text);
        assertEquals(1, distance.get("learning").intValue());
        assertEquals(3, distance.get("systems").intValue());
    }
}
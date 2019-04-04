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
    void occurrencesCountExtraction() {
        double occurrences = Extractor.occurrencesCountExtraction(dictionary, text);
//        Assertions
        assertEquals(2, (int)occurrences);
    }

    @Test
    void occurrencesSumExtraction() {
        double occurrences = Extractor.occurrencesSumExtraction(dictionary, text);
//        Assertions
        assertEquals(3, (int)occurrences);
    }

    @Test
    void densityExtraction() {
        double density = Extractor.densityExtraction(dictionary, text);
        assertTrue(Math.abs(0.6 - density) < 0.0001);
    }

    @Test
    void averageDistanceExtraction() {
        double distance = Extractor.averageDistanceExtraction(dictionary, text);
        assertTrue(Math.abs(2.6666666666666665 - distance) < 0.0001);
    }

    @Test
    void wordsCountExtraction() {
        double count = Extractor.wordsCountExtraction(dictionary, text);
        assertTrue(Math.abs(5 - count) < 0.0001);
    }

    @Test
    void wordsDistractionExtraction() {
        double distraction = Extractor.wordsDistractionExtraction(dictionary, text);
        assertTrue(Math.abs(0.3 - distraction) < 0.0001);
    }
}
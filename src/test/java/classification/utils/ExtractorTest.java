package classification.utils;

import classification.features.Extractor;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ExtractorTest {
    private final String[] text = new String[] { "machine", "learning", "based", "systems", "learning" };
    private final String[] dictionary = new String[] { "learning", "artificial", "representation", "systems" };

    double temp = 0;
    @Test
    void occurrencesCountExtraction() {
        Map occurrences = Extractor.occurrencesCountExtraction(dictionary, text);
        assertEquals(2d, occurrences.get("learning"));
        assertEquals(0d, occurrences.get("artificial"));
        assertEquals(0d, occurrences.get("representation"));
        assertEquals(1d, occurrences.get("systems"));
    }

    @Test
    void occurrencesSumExtraction() {
        Map occurrences = Extractor.occurrencesSumExtraction(dictionary, text);
        assertEquals(3d, occurrences.get("occurrencesSum"));
    }

    @Test
    void densityExtraction() {
        Map<String, Double> density = Extractor.densityExtraction(dictionary, text);
        double temp = density.get("density");
        assertTrue(Math.abs(0.6 - temp) < 0.0001);
    }

    @Test
    void averageDistanceExtraction() {
        Map<String, Double> distance = Extractor.averageDistanceExtraction(dictionary, text);
        double temp = distance.get("distance");
        assertTrue(Math.abs(2.6666666666666665 - temp) < 0.0001);
    }

    @Test
    void wordsCountExtraction() {
        Map<String, Double> wordsCount = Extractor.wordsCountExtraction(dictionary, text);
        double temp = wordsCount.get("wordsCount");
        assertTrue(Math.abs(5 - temp) < 0.0001);
    }

    @Test
    void wordsDistractionExtraction() {
        Map<String, Double> distraction = Extractor.wordsDistractionExtraction(dictionary, text);
        double temp = distraction.get("wordsDistraction");
        assertTrue(Math.abs(0.3 - temp) < 0.0001);
    }
}
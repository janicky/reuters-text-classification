package utils;

import data_models.Article;
import interfaces.IClassificationObject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void normalizeText() {
        String text = "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.";
        String cleanedText = Operations.normalizeText(text);
        assertEquals("two things are infinite the universe and human stupidity and im not sure about the universe", cleanedText);
    }

    @Test
    void splitText() {
        String text = "With increasing distance, our knowledge fades, and fades rapidly.";
        String[] vectorizedText = Operations.splitText(text);
        String[] expected = new String[] { "With", "increasing", "distance,", "our", "knowledge", "fades,", "and", "fades", "rapidly." };

        for (int i = 0; i < vectorizedText.length; i++) {
            assertEquals(expected[i], vectorizedText[i]);
        }
    }

    @Test
    void wordsOccurrences() {
        String[] dictionary = new String[] { "neil", "armstrong", "astronaut", "engineer", "first", "moon" };
        String text = "Neil Alden Armstrong was an American astronaut and aeronautical engineer who was the first person to walk on the Moon. Armstrong prepared his famous epigram on his own.";
        String[] prepared = Operations.splitText(Operations.normalizeText(text));

        Map<String, Integer> map = Operations.wordsOccurrences(dictionary, prepared);
        assertEquals(1, map.get("astronaut").intValue());
        assertEquals(2, map.get("armstrong").intValue());
    }

    @Test
    void selectObjects() {
        SortedMap<IClassificationObject, Double> map = new TreeMap<>(Collections.reverseOrder());
        map.put(new Article("TT1", new String[] { "P1", "P2" }, "TX1" ), 0.5);
        map.put(new Article("TT2", new String[] { "P3" }, "TX2" ), 15.2);
        map.put(new Article("TT3", new String[] { "P4" }, "TX3" ), 1.5);
        map.put(new Article("TT3", new String[] { "P4" }, "TX3" ), 0.4);

        System.out.println(map.toString());
    }
}
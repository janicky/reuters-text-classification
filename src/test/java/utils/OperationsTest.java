package utils;

import data_models.Article;
import interfaces.IClassificationObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    private IClassificationObject object_1;
    private IClassificationObject object_2;
    private IClassificationObject object_3;
    private IClassificationObject object_4;

    @BeforeEach
    void initialize() {
        object_1 = new Article("TT1", new String[] { "P1", "P2" }, "TX1" );
        object_2 = new Article("TT2", new String[] { "P3" }, "TX2" );
        object_3 = new Article("TT3", new String[] { "P4" }, "TX3" );
        object_4 = new Article("TT3", new String[] { "P4" }, "TX3" );
    }

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
        Map<IClassificationObject, Double> map = new HashMap<>();
        map.put(object_1, 0.5);
        map.put(object_2, 15.2);
        map.put(object_3, 1.5);
        map.put(object_4, 0.4);

        IClassificationObject[] selectedObjects = Operations.selectObjects(map, 2);
        assertEquals(object_4, selectedObjects[0]);
        assertEquals(object_1, selectedObjects[1]);
    }

    @Test
    void selectLabel() {

    }
}
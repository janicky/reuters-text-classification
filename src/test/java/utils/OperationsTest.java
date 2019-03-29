package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
}